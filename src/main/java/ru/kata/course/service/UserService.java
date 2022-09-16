package ru.kata.course.service;

import ru.kata.course.model.User;
import java.util.List;



public interface UserService {
    void save(User user);
    void updateUser(User update);
    void removeUserById(int id);
    List<User> getAllUsers();
    User getUserById(int id);
}
