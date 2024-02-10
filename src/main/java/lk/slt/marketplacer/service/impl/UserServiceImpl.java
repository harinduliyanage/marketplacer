package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.KeycloakUserNotFoundException;
import lk.slt.marketplacer.exceptions.UserAlreadyExistsException;
import lk.slt.marketplacer.exceptions.UserNotFoundException;
import lk.slt.marketplacer.model.*;
import lk.slt.marketplacer.repository.UserRepository;
import lk.slt.marketplacer.service.CartService;
import lk.slt.marketplacer.service.UserService;
import lk.slt.marketplacer.service.WishlistService;
import lk.slt.marketplacer.util.Constants;
import lk.slt.marketplacer.util.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author harindu.sul@gmail.com
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KeycloakServiceImpl keycloakService;

    @Autowired
    private CartService cartService;
    @Autowired
    private WishlistService wishlistService;

    @Override
    public User createUser(User user) {

        if (isEmailAlreadyExists(user.getId(), user.getEmail())) {
            throw new UserAlreadyExistsException(String.format(Constants.EMAIL_ALREADY_EXISTS_MSG, user.getEmail()));
        } else {
            UserRepresentation userRepresentation = keycloakService.searchByEmail(user.getEmail());
            if (null == userRepresentation) {
                throw new KeycloakUserNotFoundException(String.format(Constants.KEYCLOAK_USER_EMAIL_NOT_FOUND_MSG, user.getEmail()));
            } else if (isUserNameAlreadyExists(user.getId(), userRepresentation.getUsername())) {
                throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS_MSG, user.getUsername()));
            } else {
                // set user data from keycloak
                user.setSub(userRepresentation.getId());
                user.setUsername(userRepresentation.getUsername());
                if (null != userRepresentation.getAttributes()) {
                    if(null != userRepresentation.getAttributes().get("phone")) {
                        user.setPhone(userRepresentation.getAttributes().get("phone").get(0));
                    }
                    if(null != userRepresentation.getAttributes().get("birthDay")) {
                        user.setBirthDay(userRepresentation.getAttributes().get("birthDay").get(0));
                    }
                }
                // create new cart to user
                Cart cart = new Cart();
                cart.setCartItems(new ArrayList<>());
                Cart createdCart = cartService.createCart(cart);
                user.setCart(createdCart);
                // create new wishlist to user
                Wishlist wishlist = new Wishlist();
                wishlist.setProducts(new ArrayList<>());
                Wishlist createdWishlist = wishlistService.createWishlist(wishlist);
                user.setWishlist(createdWishlist);
                // set default followed store
                user.setFollowedStores(new ArrayList<>());
                user.setUserStatus(UserStatus.ACTIVE);
                User savedUser = userRepository.save(user);
                //
                log.info("user has been successfully created {}", savedUser);
                //
                return savedUser;
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User getUser(String id) {
        QUser qUser = QUser.user;
        BooleanExpression expression = qUser.id.eq(id);
        Optional<User> found = userRepository.findOne(expression);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new UserNotFoundException(String.format(Constants.USER_NOT_FOUND_MSG, id));
        }
    }

    @Override
    public Page<User> getUsers(String sub, String email, Pageable pageable) {
        if (email != null && sub != null) {
            QUser qUser = QUser.user;
            BooleanExpression expression = qUser.email.eq(email).and(qUser.sub.eq(sub));
            return this.userRepository.findAll(expression, pageable);
        } else if (email != null) {
            QUser qUser = QUser.user;
            BooleanExpression expression = qUser.email.eq(email);
            return this.userRepository.findAll(expression, pageable);
        } else if (sub != null) {
            QUser qUser = QUser.user;
            BooleanExpression expression = qUser.sub.eq(sub);
            return this.userRepository.findAll(expression, pageable);
        } else {
            return this.userRepository.findAll(pageable);
        }

    }

    @Override
    public User updateUser(String id, String username, User user) {
        if (isUserNameAlreadyExists(id, user.getUsername())) {
            throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS_MSG, user.getUsername()));
        } else if (isEmailAlreadyExists(id, user.getEmail())) {
            throw new UserAlreadyExistsException(String.format(Constants.EMAIL_ALREADY_EXISTS_MSG, user.getEmail()));
        } else {
            if (!username.equalsIgnoreCase(user.getUsername())) {
                UserRepresentation userRepresentation = keycloakService.searchByEmail(user.getEmail());
                if (null != userRepresentation) {
                    user.setSub(userRepresentation.getId());
                } else {
                    throw new KeycloakUserNotFoundException(String.format(Constants.KEYCLOAK_USER_EMAIL_NOT_FOUND_MSG, user.getEmail()));
                }
            }
            //
            user.setId(id);
            List<Store> uniqueFollowedStores = user.getFollowedStores().stream().distinct().collect(Collectors.toList());
            user.setFollowedStores(uniqueFollowedStores);
            // update user data in keycloak
            Map<String, List<String>> attributes = new HashMap<>();
            if (null != user.getFirstName()) {
                attributes.put("firstName", List.of(user.getFirstName()));
            }
            if (null != user.getLastName()) {
                attributes.put("lastName", List.of(user.getLastName()));
            }
            if (null != user.getPhone()) {
                attributes.put("phone", List.of(user.getPhone()));
            }
            if (null != user.getBirthDay()) {
                attributes.put("birthDay", List.of(user.getBirthDay()));
            }
            keycloakService.updateUser(user.getSub(), user.getUsername(), user.getEmail(), attributes);
            // update user status in keycloak
            keycloakService.setUserStatus(user.getSub(), user.getUserStatus() == UserStatus.ACTIVE);
            // update user in db
            User updatedUser = userRepository.save(user);
            //
            log.info("user has been successfully updated {}", updatedUser);
            return updatedUser;
        }
    }

    @Override
    public User removeUser(String id) {
        User user = getUser(id);
        userRepository.deleteById(id);
        log.info("user has been successfully deleted {}", user);
        return user;
    }

    private Boolean isUserNameAlreadyExists(String id, String username) {
        QUser qUser = QUser.user;
        BooleanExpression expression = id == null ? qUser.username.eq(username)
                : qUser.username.eq(username).and(qUser.id.ne(id));
        return userRepository.exists(expression);
    }

    private Boolean isEmailAlreadyExists(String id, String email) {
        QUser qUser = QUser.user;
        BooleanExpression expression = id == null ? qUser.username.eq(email)
                : qUser.email.eq(email).and(qUser.id.ne(id));
        return userRepository.exists(expression);
    }
}
