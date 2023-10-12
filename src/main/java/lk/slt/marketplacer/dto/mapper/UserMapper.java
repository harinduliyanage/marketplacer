package lk.slt.marketplacer.dto.mapper;


import lk.slt.marketplacer.dto.UserDto;
import lk.slt.marketplacer.model.User;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    List<UserDto> userListToUserDtoList(List<User> storeList);
}
