package org.example.shareit.item.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ItemResponseDto {
    int id;
    String name;
    String description;
    Boolean available;
}
