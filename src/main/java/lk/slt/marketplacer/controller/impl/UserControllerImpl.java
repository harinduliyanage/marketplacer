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
    public ListResponseDto<UserDto> getUsers(Pageable pageable) {
        Page<User> page = userService.getUsers(pageable);
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
        return userMapper.userToUserDto(userService.getUserById(userId));
    }

    @Override
    public UserDto updateUser(String userId, UpdateUserDto updateUserDto) {
        User user = userMapper.updateUserDtoToUser(updateUserDto);
        user.setId(userId);
        return userMapper.userToUserDto(userService.updateUser(userId, user));
    }

    @Override
    public UserDto removeUser(String userId) {
        return userMapper.userToUserDto(userService.removeUser(userId));
    }
}
