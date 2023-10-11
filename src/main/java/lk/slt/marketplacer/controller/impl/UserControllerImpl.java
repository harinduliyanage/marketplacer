package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.UserController;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.UserDto;
import lk.slt.marketplacer.model.User;
import lk.slt.marketplacer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author harindu.sul@gmail.com
 */
@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ListResponseDto<UserDto> getUsers(Pageable pageable) {
        Page<User> page = userService.getUsers(pageable);
        return ListResponseDto.<UserDto>builder()
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
