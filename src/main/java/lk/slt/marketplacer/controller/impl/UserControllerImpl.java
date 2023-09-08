package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.UserController;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author harindu.sul@gmail.com
 */
@RestController
public class UserControllerImpl implements UserController {
    @Override
    public void getUsers() {
        System.out.println("hi");
    }
}
