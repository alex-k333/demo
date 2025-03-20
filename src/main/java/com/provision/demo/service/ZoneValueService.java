package com.provision.demo.service;

import com.provision.demo.mapper.ZoneValueMapper;
import com.provision.demo.model.dto.ZoneValueDto;
import com.provision.demo.model.entity.ZoneValue;
import com.provision.demo.repository.ZoneValueRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Getter
@Transactional
public class ZoneValueService extends CrudService<ZoneValueDto, ZoneValue, ZoneValueDto, Long> {

    private final ZoneValueRepository repository;
    private final ZoneValueMapper mapper;

}
