package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.UserAlreadyExistsException;
import lk.slt.marketplacer.exceptions.UserNotFoundException;
import lk.slt.marketplacer.exceptions.UsernameInvalidException;
import lk.slt.marketplacer.model.QUser;
import lk.slt.marketplacer.model.User;
import lk.slt.marketplacer.repository.UserRepository;
import lk.slt.marketplacer.service.UserService;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public User createUser(User user) {
        String id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        //
        if (isUserNameAlreadyExists(id, username)) {
            throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS_MSG, username));
        } else if (isEmailAlreadyExists(id, email)) {
            throw new UserAlreadyExistsException(String.format(Constants.EMAIL_ALREADY_EXISTS_MSG, email));
        } else {
            try {
                String sub = keycloakService.searchByUsername(username).getId();
                user.setSub(sub);
                User savedUser = userRepository.save(user);
                //
                log.info("user has been successfully created {}", savedUser);
                //
                return savedUser;
            } catch (NullPointerException exception) {
                throw new UsernameInvalidException(String.format(Constants.USERNAME_INVALID_MSG, username));
            }
        }
    }

    @Override
    public User getUserById(String id) {
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
    public User updateUser(String id, User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        if (isUserNameAlreadyExists(id, username)) {
            throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS_MSG, username));
        } else if (isEmailAlreadyExists(id, email)) {
            throw new UserAlreadyExistsException(String.format(Constants.EMAIL_ALREADY_EXISTS_MSG, email));
        } else {
            user.setId(id);
            User updatedUser = userRepository.save(user);
            log.info("user has been successfully updated {}", updatedUser);
            return updatedUser;
        }
    }

    @Override
    public User removeUser(String id) {
        User user = getUserById(id);
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
