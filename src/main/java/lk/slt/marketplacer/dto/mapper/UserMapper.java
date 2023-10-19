package lk.slt.marketplacer.dto.mapper;


import lk.slt.marketplacer.dto.CreateUserDto;
import lk.slt.marketplacer.dto.UpdateUserDto;
import lk.slt.marketplacer.dto.UserDto;
import lk.slt.marketplacer.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    User createUserDtoToUser(CreateUserDto createUserDto);
    User updateUserDtoToUser(UpdateUserDto updateUserDto);
    List<UserDto> userListToUserDtoList(List<User> storeList);
}
