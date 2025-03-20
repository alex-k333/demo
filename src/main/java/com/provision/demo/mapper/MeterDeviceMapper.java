package com.provision.demo.mapper;

import com.provision.demo.model.dto.MeterDeviceDto;
import com.provision.demo.model.dto.MeterDeviceResponseDto;
import com.provision.demo.model.dto.UserInfo;
import com.provision.demo.model.entity.MeterDevice;
import com.provision.demo.model.entity.User;
import com.provision.demo.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MeterDeviceMapper implements BaseMapper<MeterDeviceDto, MeterDevice, MeterDeviceResponseDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Mapping(source = "userId", target = "user", qualifiedByName = "createUser")
    public abstract MeterDevice toEntity(MeterDeviceDto dto);

    public UserInfo toResponse(User user) {
        return userMapper.toUserInfo(userRepository.findById(user.getId()).orElseThrow());
    }

    @Named("createUser")
    protected User toUser(Long id) {
        return User.builder()
                .id(id)
                .build();
    }
}
