package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.UserAlreadyExistsException;
import lk.slt.marketplacer.exceptions.UserNotFoundException;
import lk.slt.marketplacer.exceptions.UsernameInvalidException;
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
        if (isUserNameAlreadyExists(user.getId(), user.getUsername())) {
            throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS_MSG, user.getUsername()));
        } else if (isEmailAlreadyExists(user.getId(), user.getEmail())) {
            throw new UserAlreadyExistsException(String.format(Constants.EMAIL_ALREADY_EXISTS_MSG, user.getEmail()));
        } else {
            UserRepresentation userRepresentation = keycloakService.searchByUsername(user.getUsername());
            if (null != userRepresentation) {
                user.setSub(userRepresentation.getId());
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
            } else {
                throw new UsernameInvalidException(String.format(Constants.USERNAME_INVALID_MSG, user.getUsername()));
            }
        }
    }

    @Override
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
                UserRepresentation userRepresentation = keycloakService.searchByUsername(user.getUsername());
                if (null != userRepresentation) {
                    user.setSub(userRepresentation.getId());
                } else {
                    throw new UsernameInvalidException(String.format(Constants.USERNAME_INVALID_MSG, user.getUsername()));
                }
            }
            user.setId(id);
            List<Store> uniqueFollowedStores = user.getFollowedStores().stream().distinct().collect(Collectors.toList());
            user.setFollowedStores(uniqueFollowedStores);
            User updatedUser = userRepository.save(user);
            // update user data in keycloak
            Map<String, List<String>> attributes = new HashMap<>();
            if(null != updatedUser.getFirstName()) {
                attributes.put("firstName", List.of(updatedUser.getFirstName()));
            }
            if(null!=updatedUser.getLastName()) {
                attributes.put("lastName", List.of(updatedUser.getLastName()));
            }
            if(null!=updatedUser.getPhone()) {
                attributes.put("phone", List.of(updatedUser.getPhone()));
            }
            if(null!=updatedUser.getBirthDay()) {
                attributes.put("birthDay", List.of(updatedUser.getBirthDay()));
            }
            if (!attributes.isEmpty()) {
                keycloakService.updateUser(updatedUser.getSub(), updatedUser.getEmail(), attributes);
            }
            // update user status in keycloak
            keycloakService.setUserStatus(updatedUser.getSub(), updatedUser.getUserStatus()==UserStatus.ACTIVE);
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
