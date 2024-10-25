package org.example.shareit.item;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.shareit.user.User;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Item {
    int id;
    String name;
    String description;
    Boolean available;
    User owner;
}
