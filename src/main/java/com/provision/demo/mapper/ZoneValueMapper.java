package com.provision.demo.mapper;

import com.provision.demo.model.dto.ZoneValueDto;
import com.provision.demo.model.entity.MeterDevice;
import com.provision.demo.model.entity.ZoneValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ZoneValueMapper implements BaseMapper<ZoneValueDto, ZoneValue, ZoneValueDto> {

    @Override
    @Mapping(target = "meterDevice", source = "deviceId", qualifiedByName = "createDevice")
    public abstract ZoneValue toEntity(ZoneValueDto dto);

    @Override
    @Mapping(target = "deviceId", expression = "java(entity.getId())")
    public abstract ZoneValueDto toResponse(ZoneValue entity);

    @Named("createDevice")
    protected MeterDevice toUser(Long id) {
        return MeterDevice.builder()
                .id(id)
                .build();
    }
}
