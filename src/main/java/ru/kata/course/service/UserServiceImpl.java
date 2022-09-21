package ru.kata.course.service;

import org.springframework.stereotype.Service;
import ru.kata.course.dao.UserDAO;
import ru.kata.course.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Transactional
    @Override
    public void save(User user) {
        userDAO.save(user);
    }
    @Transactional
    @Override
    public void updateUser(User update) {
        userDAO.updateUser(update);
    }
    @Transactional
    @Override
    public void removeUserById(int id) {
        userDAO.removeUserById(id);
    }
    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    @Transactional
    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
}
