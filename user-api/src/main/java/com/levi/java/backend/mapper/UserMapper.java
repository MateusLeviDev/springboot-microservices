package com.levi.java.backend.mapper;

import com.levi.java.backend.domain.User;
import com.levi.java.backend.mapper.requests.UserPostRequest;
import com.levi.java.backend.mapper.requests.UserPutRequest;
import com.levi.java.backend.mapper.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toMapper(UserPostRequest userPostRequest);
    public abstract User toMapper(UserPutRequest userPutRequest);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telephone", target = "telephone")
    public abstract UserResponse toMapperUserResponse(User user);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telephone", target = "telephone")
    public abstract List<UserResponse> toMapperUserResponse(List<User> users);

    //TODO: could use wildcard. turning dto into user extensions || public abstract <T extends User> T toMapperUserResponse(T t); ?
}
