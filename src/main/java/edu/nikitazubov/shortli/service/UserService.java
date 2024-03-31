package edu.nikitazubov.shortli.service;

import edu.nikitazubov.shortli.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    User addNewUser(User user);

    User updateUser(User user);

    String deleteUser(Long id);
}
