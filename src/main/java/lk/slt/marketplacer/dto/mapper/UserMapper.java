package lk.slt.marketplacer.dto.mapper;


import lk.slt.marketplacer.dto.CreateUserDto;
import lk.slt.marketplacer.dto.UpdateUserDto;
import lk.slt.marketplacer.dto.UserDto;
import lk.slt.marketplacer.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sub", ignore = true)
    User createUserDtoToUser(CreateUserDto createUserDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sub", ignore = true)
    @Mapping(target = "firstName", source = "firstName",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lastName", source = "lastName",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", source = "email",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "phone", source = "phone",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserDtoToUser(UpdateUserDto updateUserDto, @MappingTarget User user);

    List<UserDto> userListToUserDtoList(List<User> storeList);
}
