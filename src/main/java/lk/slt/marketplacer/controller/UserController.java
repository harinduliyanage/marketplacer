package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author harindu.sul@gmail.com
 */
@Tag(name = "user-services")
public interface UserController {
    @GetMapping(value = "/users", consumes = {"application/json"}, produces = {"application/json"})
    public void getUsers();
}
