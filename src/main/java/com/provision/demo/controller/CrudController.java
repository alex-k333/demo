package com.provision.demo.controller;

import com.provision.demo.model.Id;
import com.provision.demo.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
public abstract class CrudController<D, E extends Id<ID>, R, ID> {

    protected abstract CrudService<D, E, R, ID> getService();

    @Operation(
            summary = "Получение списка всех сущностей",
            description = "Возвращает список всех сущностей.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос"),
                    @ApiResponse(responseCode = "404", description = "Ресурс не найден", content = @Content)
            })
    @GetMapping
    public List<R> getAll() {
        return getService().getAll();
    }

    @Operation(
            summary = "Создание новой сущности",
            description = "Создает новую сущность.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Сущность успешно создана", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные сущности", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Сущность ужу существует", content = @Content)
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R create(@Valid @RequestBody D dto) {
        return getService().create(dto);
    }

    @Operation(
            summary = "Обновление существующей сущности",
            description = "Обновляет сущность по указанному идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные ущности успешно обновлены", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные сущности", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Ресурс не найден", content = @Content)
            },
            parameters = {
                    @Parameter(name = "id", required = true, description = "Идентификатор сущности", example = "1")
            }
    )
    @PutMapping("/{id}")
    public R update(@PathVariable("id") ID id, @Valid @RequestBody D dto) {
        return getService().update(id, dto);
    }

    @Operation(
            summary = "Удаление сущности",
            description = "Удаляет сущность по указанному идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Сущность удалена"),
                    @ApiResponse(responseCode = "404", description = "Ресурс не найден", content = @Content)
            },
            parameters = {
                    @Parameter(name = "id", required = true, description = "Идентификатор удаляемой сущности", example = "1")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") ID id) {
        getService().delete(id);
    }

    @Operation(
            summary = "Получение сущности по идентификатору",
            description = "Возвращает сущность по указанному идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Запрошенная сущность", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "404", description = "Ресурс не найден", content = @Content)
            },
            parameters = {
                    @Parameter(name = "id", required = true, description = "Идентификатор сущности", example = "1")
            }
    )
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") ID id) {
        return getService().getById(id);
    }

}
