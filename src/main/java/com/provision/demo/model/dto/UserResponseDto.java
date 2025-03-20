package com.provision.demo.model.dto;

import com.provision.demo.model.Id;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Пользователь без чувствительных данных")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto implements Id<Long> {

    @Schema(description = "Идентификатор пользователя")
    private Long id;

    @Schema(description = "ФИО")
    private String fullName;

    @Schema(description = "Логин")
    private String username;

    @Schema(description = "Почта")
    private String email;

    @Schema(description = "Приборы учета")
    private List<MeterDeviceInfo> meterDevices;

}
