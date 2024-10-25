package org.example.shareit.user;

import lombok.RequiredArgsConstructor;
import org.example.shareit.exception.UserAlreadyExistException;
import org.example.shareit.user.dto.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User create(User user) {
        Optional<User> optUser = userRepository.findByEmail(user.getEmail());
        if (optUser.isPresent()) {
            throw new UserAlreadyExistException("Пользователь с такой почтой уже существует");
        }

        return userRepository.create(user);
    }

    public User update(User updatedUser, int userId) {
        Optional<User> optionalUser = userRepository.findByEmail(updatedUser.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getId() != userId) {
                throw new UserAlreadyExistException("Пользователь с такой почтой уже существует");
            }
        }

        User existingUser = userRepository.findById(userId).orElseThrow();
        userMapper.merge(existingUser, updatedUser);

        return userRepository.update(existingUser, userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }
}
