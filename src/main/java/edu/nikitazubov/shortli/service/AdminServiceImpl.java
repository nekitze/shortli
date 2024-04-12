package edu.nikitazubov.shortli.service;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.entity.User;
import edu.nikitazubov.shortli.repository.UrlRepository;
import edu.nikitazubov.shortli.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }
}
