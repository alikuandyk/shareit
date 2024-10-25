package org.example.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.shareit.user.dto.UserCreateDto;
import org.example.shareit.user.dto.UserMapper;
import org.example.shareit.user.dto.UserResponseDto;
import org.example.shareit.user.dto.UserUpdateDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    //    POST    /users - создание пользователя
    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserCreateDto userCreate) {
        User user = userMapper.fromCreate(userCreate);
        return userMapper.toResponse(userService.create(user));
    }

    //    PATCH   /users/{id} - обновление пользователя по id
    @PatchMapping("/{id}")
    public UserResponseDto update(
            @PathVariable int id,
            @Valid @RequestBody UserUpdateDto userUpdate
    ) {
        User user = userMapper.fromUpdate(userUpdate);
        return userMapper.toResponse(userService.update(user, id));
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        List<UserResponseDto> userResponseDtos =
                userService.findAll().stream()
                        .map(userMapper::toResponse)
                        .toList();

        return userResponseDtos;
    }

    @GetMapping("/{userId}")
    public UserResponseDto findById(@PathVariable int userId) {
        User user = userService.findById(userId);

        return userMapper.toResponse(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable int userId) {
        userService.deleteById(userId);
    }
    //    GET     /users - получения всех пользователей
    //    GET     /users/{id} - получения пользователя по id
    //    DELETE  /users/{id} - удаления пользователя по id
}
