package org.example.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ItemCreateDto {
    @NotBlank(message = "Название не может быть пустым")
    String name;

    @NotBlank
    String description;

    @NotNull
    Boolean available;
}
