package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.UserDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author harindu.sul@gmail.com
 */
@Tag(name = "user-services")
@RequestMapping("/api/v1")
public interface UserController {
    @GetMapping(value = "/users", produces = {"application/json"})
    public ListResponseDto<UserDto> getUsers(@ParameterObject Pageable pageable);
}
