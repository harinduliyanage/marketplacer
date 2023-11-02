package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.UserAlreadyExistsException;
import lk.slt.marketplacer.exceptions.UserNotFoundException;
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

    @Override
    public User createUser(User user) {
        if (isUserNameAlreadyExists(user.getUsername())) {
            throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS, user.getUsername()));
        } else if (isEmailAlreadyExists(user.getEmail())) {
            throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS, user.getEmail()));
        } else {
            User savedUser = userRepository.save(user);
            //
            log.info("user has been successfully created {}", savedUser);
            //
            return savedUser;
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
    public Page<User> getUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User updateUser(String id, User user) {
        User userResponse = getUserById(id);
        String username = user.getUsername();
        String email = user.getEmail();
        if (!userResponse.getUsername().equals(username) && isUserNameAlreadyExists(username)) {
            throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS, username));
        } else if (!userResponse.getEmail().equals(email) && isEmailAlreadyExists(email)) {
            throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS, email));
        } else {
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

    private Boolean isUserNameAlreadyExists(String username) {
        QUser qUser = QUser.user;
        BooleanExpression expression = qUser.username.eq(username);
        return userRepository.exists(expression);
    }

    private Boolean isEmailAlreadyExists(String email) {
        QUser qUser = QUser.user;
        BooleanExpression expression = qUser.email.eq(email);
        return userRepository.exists(expression);
    }
}
