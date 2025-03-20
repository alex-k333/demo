package com.provision.demo.model.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.provision.demo.model.Id;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Значеня счетчика по зонам")
public class ZoneValueDto implements Id<Long> {

    @Schema(description = "Идентификатор значения", example = "1")
    @Hidden
    private Long id;

    @Schema(description = "Идентификатор прибора учета")
    @NotNull(message = "Идентификатор прибора обязателен для заполнения")
    private Long deviceId;

    @Schema(description = "Значения по зонам", example = """
            { "a" : 123, "b" : 456}
            """)
    @NotNull(message = "Значения обязательны для заполнения")
    private JsonNode valuesPerZone;
}
