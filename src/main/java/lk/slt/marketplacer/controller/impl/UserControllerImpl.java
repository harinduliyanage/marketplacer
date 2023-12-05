package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.UserController;
import lk.slt.marketplacer.dto.CreateUserDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.UpdateUserDto;
import lk.slt.marketplacer.dto.UserDto;
import lk.slt.marketplacer.dto.mapper.UserMapper;
import lk.slt.marketplacer.model.User;
import lk.slt.marketplacer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author harindu.sul@gmail.com
 */
@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        User createdUser = userService.createUser(userMapper.createUserDtoToUser(createUserDto));
        return userMapper.userToUserDto(createdUser);
    }

    @Override
    public ListResponseDto<UserDto> getUsers(String sub, String email, Pageable pageable) {
        Page<User> page = userService.getUsers(sub, email, pageable);
        return ListResponseDto.<UserDto>builder()
                .data(userMapper.userListToUserDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public UserDto getUser(String userId) {
        return userMapper.userToUserDto(userService.getUser(userId));
    }

    @Override
    public UserDto updateUser(String userId, UpdateUserDto updateUserDto) {
        return userMapper
                .userToUserDto(userService
                        .updateUser(userId, userMapper
                                .updateUserDtoToUser(updateUserDto, userService.getUser(userId)
                                )
                        )
                );
    }

    @Override
    public UserDto removeUser(String userId) {
        return userMapper.userToUserDto(userService.removeUser(userId));
    }
}

