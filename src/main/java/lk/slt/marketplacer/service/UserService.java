package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author harindu.sul@gmail.com
 */
public interface UserService {

    public User createUser(User user);
    public User getUserById(String id);
    public Page<User> getUsers(Pageable pageable);
}
