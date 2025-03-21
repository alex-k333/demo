package com.provision.demo.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Прибор учета")
public class MeterDeviceDto {

    @Schema(description = "Серийный номер", example = "123456")
    @NotBlank(message = "Серийный номер обязателен для заполнения")
    private String serialNumber;

    @Schema(description = "Инвертарный номер", example = "АБВ-123")
    private String inventoryNumber;

    @Schema(description = "Год производства", example = "2025")
    private Short manufactureYear;

    @Schema(description = "Коофицент трансыормации")
    private Integer transformationCoefficient;

    @Schema(description = "Дата установки")
    private LocalDate installationDate;

    @Schema(description = "Номер пломбы", example = "ВА-123123")
    @NotBlank(message = "Номер пломбы обязателен для заполнения")
    private String sealNumber;

    @Schema(description = "Мэджик намбер", example = "DD-5555")
    private String antiMagneticSealNumber;

    @Schema(description = "Место установки", example = "Москва, ул. Ленина, 45-89")
    private String installationPlace;

    @Schema(description = "Заметки")
    private String note;

    @Schema(description = "Идентификатор ГИС ЖКХ")
    private UUID gisHousingId;

    @Schema(description = "Идентификатор пользователя", example = "1")
    @NotNull(message = "Идентификатор пользователя обязателен для заполнения")
    private Long userId;

}
