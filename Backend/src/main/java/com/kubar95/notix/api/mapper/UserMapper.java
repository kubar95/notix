package com.kubar95.notix.api.mapper;

import com.kubar95.notix.api.model.BaseUserDTO;
import com.kubar95.notix.api.model.UserDTO;
import com.kubar95.notix.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userDTO.username", target = "username")
    @Mapping(source = "userDTO.email", target = "email")
    @Mapping(source = "password", target = "password")
    User userDtoToUser(UserDTO userDTO, String password);

    BaseUserDTO userToBaseUserDto (User user);

}
