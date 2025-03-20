package com.provision.demo.service;

import com.provision.demo.mapper.MeterDeviceMapper;
import com.provision.demo.model.dto.MeterDeviceDto;
import com.provision.demo.model.dto.MeterDeviceResponseDto;
import com.provision.demo.model.entity.MeterDevice;
import com.provision.demo.repository.MeterDeviceRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Getter
@Transactional
public class MeterDeviceService extends CrudService<MeterDeviceDto, MeterDevice, MeterDeviceResponseDto, Long> {

    private final MeterDeviceRepository repository;
    private final MeterDeviceMapper mapper;

}
