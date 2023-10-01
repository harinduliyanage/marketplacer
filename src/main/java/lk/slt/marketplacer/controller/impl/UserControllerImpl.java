package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.UserController;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author harindu.sul@gmail.com
 */
@RestController
public class UserControllerImpl implements UserController {
    @Override
    public ListResponseDto<UserDto> getUsers(Pageable pageable) {
        return ListResponseDto.<UserDto>builder()
                .build();
    }
}
