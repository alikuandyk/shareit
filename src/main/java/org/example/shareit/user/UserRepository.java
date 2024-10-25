package org.example.shareit.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User create(User user);

    // Существующий пользователь
    // {
    //     "name": igor",
    //     "email": igor@mail.com"
    // }

    // Часть обновленной информаций
    // {
    //     "email": igorUpdated@mail.com"
    // }
    User update(User updatedUser, int userId);

    List<User> findAll();

    Optional<User> findById(int userId);

    void deleteById(int userId);

    Optional<User> findByEmail(String email);
}
