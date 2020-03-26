package com.project.decrypt.mapper;

import com.project.decrypt.dto.UserDTO;
import com.project.decrypt.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class UserMapper {
    public abstract User toEntity(UserDTO dto);

    public abstract List<User> toEntityList(List<UserDTO> dtoList);

    public abstract UserDTO toDto(User entity);

    public abstract List<UserDTO> toDtoList(List<User> entityList);
}
