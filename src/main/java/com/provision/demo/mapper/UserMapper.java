package com.provision.demo.mapper;

import com.provision.demo.model.dto.UserDto;
import com.provision.demo.model.dto.UserInfo;
import com.provision.demo.model.dto.UserResponseDto;
import com.provision.demo.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper implements BaseMapper<UserDto, User, UserResponseDto> {

    @Override
    public abstract UserResponseDto toResponse(User entity);

    public abstract UserInfo toUserInfo(User user);
}
