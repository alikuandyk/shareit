package org.example.shareit.user.dto;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDto {
    String name;
    @Email(message = "Почта пользователя должна быть в формате \"user@mail.com\"")
    String email;
}
