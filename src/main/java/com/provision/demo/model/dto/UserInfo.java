package com.provision.demo.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "нформация о пользователе")
@Getter
@Setter
public class UserInfo {
    @Schema(description = "Идентификатор пользователя")
    private Long id;

    @Schema(description = "ФИО")
    private String fullName;

    @Schema(description = "Логин")
    private String username;

    @Schema(description = "Почта")
    private String email;
}
