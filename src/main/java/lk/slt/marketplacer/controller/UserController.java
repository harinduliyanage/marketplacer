package lk.slt.marketplacer.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author harindu.sul@gmail.com
 */
public interface UserController {
    @GetMapping(value = "/users", consumes = {"application/json"}, produces = {"application/json"})
    public void getUsers();
}
