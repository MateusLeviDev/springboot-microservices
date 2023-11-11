package com.levi.java.backend.mapper;

import com.levi.java.backend.domain.User;
import com.levi.java.backend.requests.UserPostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toMapper(UserPostRequest userPostRequest);
}
