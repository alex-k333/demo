package com.provision.demo.controller;

import com.provision.demo.model.dto.UserDto;
import com.provision.demo.model.dto.UserResponseDto;
import com.provision.demo.model.entity.User;
import com.provision.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Getter
@Tag(name = "Пользователи", description = "Операции с пользователями")
public class UserController extends CrudController <UserDto, User, UserResponseDto, Long> {

    private final UserService service;

}
