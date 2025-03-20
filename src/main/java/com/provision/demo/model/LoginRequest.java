package com.provision.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Запрос на получение токена")
public class LoginRequest {

    @Schema(description = "Логин", example = "my_login")
    @Size(min = 3, max = 20, message = "Логин должен содержать от 3 до 20 символов")
    @NotBlank(message = "Не заполнено обязательное поле")
    private String username;

    @Schema(description = "Пароль", example = "pa$$word1")
    @Size(max = 30, message = "Длина пароля должна быть не более 255 символов")
    @NotBlank(message = "Не заполнено обязательное поле")
    private String password;
}