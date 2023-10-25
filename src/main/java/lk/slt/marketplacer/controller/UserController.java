package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lk.slt.marketplacer.dto.CreateUserDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.UpdateUserDto;
import lk.slt.marketplacer.dto.UserDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author harindu.sul@gmail.com
 */
@Tag(name = "user-services")
@RequestMapping("/api/v1")
public interface UserController {
    @PostMapping(value = "/users", consumes = {"application/json"}, produces = {"application/json"})
    public UserDto createUser(@RequestBody CreateUserDto createUserDto);
    @GetMapping(value = "/users", produces = {"application/json"})
    public ListResponseDto<UserDto> getUsers(@ParameterObject Pageable pageable);
    @GetMapping(value = "/users/{userId}", produces = {"application/json"})
    public UserDto getUser(@PathVariable("userId") String userId);
    @PatchMapping(value = "/users/{userId}", consumes = {"application/json"}, produces = {"application/json"})
    public UserDto updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto);
    @DeleteMapping(value = "/users/{userId}", produces = {"application/json"})
    public UserDto removeUser(@PathVariable("userId") String userId);
}
