package org.example.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    String name;

    @NotNull(message = "Почта пользователя не может быть пустой")
    @Email(message = "Почта пользователя должна быть в формате \"user@mail.com\"")
    String email;
}
