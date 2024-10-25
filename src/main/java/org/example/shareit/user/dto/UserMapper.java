package org.example.shareit.user.dto;

import org.example.shareit.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User fromCreate(UserCreateDto userCreate) {
        User user = new User();
        user.setName(userCreate.getName());
        user.setEmail(userCreate.getEmail());
        return user;
    }

    public User fromUpdate(UserUpdateDto userUpdate) {
        User user = new User();
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        return user;
    }

    public UserResponseDto toResponse(User user) {
        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }


    public void merge(User existingUser, User updatedUser) {
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
    }
}
