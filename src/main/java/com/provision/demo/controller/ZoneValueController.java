package com.provision.demo.controller;

import com.provision.demo.model.dto.ZoneValueDto;
import com.provision.demo.model.entity.ZoneValue;
import com.provision.demo.service.ZoneValueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zone-value")
@RequiredArgsConstructor
@Getter
@Tag(name = "Показание прибора учета", description = "Операции с показаниями прибора учета")
public class ZoneValueController extends CrudController<ZoneValueDto, ZoneValue, ZoneValueDto, Long> {

    private final ZoneValueService service;

}
