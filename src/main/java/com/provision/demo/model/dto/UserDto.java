package com.provision.demo.model.dto;

import com.provision.demo.model.enumiration.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Пользователь")
public class UserDto {

    @Schema(description = "Почта", example = "myemail@eamil.com")
    @Email(message = "Почта имеет не верный формат")
    @NotBlank(message = "Почта обязательна для заполнения")
    private String email;

    @Schema(description = "ФИО", example = "Иванов Иван Иванович")
    @NotBlank(message = "ФИО обязательно для заполнения")
    private String fullName;

    @Schema(description = "Логин", example = "user")
    @NotBlank(message = "Логин обязателен для заполнения")
    private String username;

    @Schema(description = "Пароль", example = "user")
    @NotBlank(message = "Пароль обязателен для заполнения")
    private String password;

    @Schema(description = "Роль", example = "MANAGER")
    @NotNull(message = "Роль обязательна для заполнения")
    private Role role;

}
