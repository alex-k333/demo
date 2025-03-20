package com.provision.demo.model.dto;

import com.provision.demo.model.Id;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MeterDeviceResponseDto implements Id<Long> {

    @Schema(description = "Идентификатор проибора учёта")
    private Long id;

    @Schema(description = "Серийный номер", example = "123456")
    private String serialNumber;

    @Schema(description = "Инвертарный номер", example = "АБВ-123")
    private String inventoryNumber;

    @Schema(description = "Год производства", example = "2025")
    private Short manufactureYear;

    @Schema(description = "Коофицент трансформации")
    private Integer transformationCoefficient;

    @Schema(description = "Дата установки")
    private LocalDate installationDate;

    @Schema(description = "Номер пломбы", example = "ВА-123123")
    private String sealNumber;

    @Schema(description = "Мэджик намбер", example = "DD-5555")
    private String antiMagneticSealNumber;

    @Schema(description = "Место установки", example = "Москва, ул. Ленина, 45-89")
    private String installationPlace;

    @Schema(description = "Заметки")
    private String note;

    @Schema(description = "Идентификатор ГИС ЖКХ")
    private UUID gisHousingId;

    @Schema(description = "Пользователь")
    private UserInfo user;

    @Schema(description = "Показания по зонам")
    private List<ZoneValueInfo> zoneValues;

}
