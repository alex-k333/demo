package com.provision.demo.controller;

import com.provision.demo.model.dto.MeterDeviceDto;
import com.provision.demo.model.dto.MeterDeviceResponseDto;
import com.provision.demo.model.entity.MeterDevice;
import com.provision.demo.service.MeterDeviceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meter-device")
@RequiredArgsConstructor
@Getter
@Tag(name = "Приборы Учёта", description = "Операции с приборами учёта")
public class MeterDeviceController extends CrudController<MeterDeviceDto, MeterDevice, MeterDeviceResponseDto, Long> {

    private final MeterDeviceService service;

}
