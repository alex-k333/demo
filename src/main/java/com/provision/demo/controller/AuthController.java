package com.provision.demo.controller;

import com.provision.demo.model.LoginRequest;
import com.provision.demo.model.LoginResponse;
import com.provision.demo.service.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Getter
@Tag(name = "Безопасность", description = "методы аутентификации")
public class AuthController {

    private final SecurityService service;

    @Operation(
            summary = "Получение токена",
            description = "Метод получения токена",
            responses = {
                    @ApiResponse(responseCode = "200", description = "успешный овет", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content)
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }

    public ResponseEntity<?> logout() {
        throw new UnsupportedOperationException();
    }

}
