package ru.kata.course.dao;

import org.springframework.stereotype.Component;
import ru.kata.course.Entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int USER_COUNT;
    private final List<User> user;

    {
        user = new ArrayList<>();

        user.add(new User(++USER_COUNT, "vasya", "petrov"));
        user.add(new User(++USER_COUNT, "andrew", "sidorov"));
        user.add(new User(++USER_COUNT, "gena", "tazeev"));
        user.add(new User(++USER_COUNT, "ivan", "ivanov"));
    }

    public List<User> index() {
        return user;
    }

    public User showUser(int id) {
        return user.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User userAdd) {
        userAdd.setId(++USER_COUNT);
        user.add(userAdd);
    }

    public void update(int id, User updatedUser) {
        User userToUpdate = showUser(id);
        userToUpdate.setName(updatedUser.getName());
        userToUpdate.setLastname(updatedUser.getLastname());

    }

    public void delete(int id) {
        user.removeIf(u -> u.getId() == id);

    }
}
