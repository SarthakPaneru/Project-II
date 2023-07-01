package com.example.hamro_barber.mapper;

import com.example.hamro_barber.entity.User;
import com.example.hamro_barber.entity.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserDto userToDto(User user);
    User dtoToUser(UserDto userDto);
    List<UserDto> listUserToDto(List<User> users);
    List<User> listDtoToUser(List<UserDto> userDtos);
}
