package edu.nikitazubov.shortli.service;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.entity.User;

import java.util.List;

public interface AdminService {
    List<Url> getAllUrls();
    List<User> getAllUsers();
}
