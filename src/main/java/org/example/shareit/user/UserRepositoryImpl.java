package org.example.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private static int id = 1;

    @Override
    public User create(User user) {
        user.setId(id++);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User updatedUser, int userId) {
        users.put(userId, updatedUser);

        return updatedUser;
    }

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public Optional<User> findById(int userId) {
        return users.values().stream()
                .filter(user -> user.getId() == userId)
                .findFirst();
    }

    @Override
    public void deleteById(int userId) {
        users.remove(userId);
    }

    @Override
    public Optional<User> findByEmail(String email) {
         return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}
