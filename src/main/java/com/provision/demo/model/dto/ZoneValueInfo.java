package com.provision.demo.model.dto;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Значеня счетчика по зонам")
public class ZoneValueInfo {

    @Schema(description = "Значения по зонам", example = """
            { "a" : 123, "b" : 456}
            """)
    @NotNull(message = "Значения обязательны для заполнения")
    private JsonNode valuesPerZone;

}
