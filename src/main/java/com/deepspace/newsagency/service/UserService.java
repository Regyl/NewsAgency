package com.deepspace.newsagency.service;

import com.deepspace.newsagency.entity.User;
import com.deepspace.newsagency.exception.EntityNotFoundException;
import com.deepspace.newsagency.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepository repository) {

    public User findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(EntityNotFoundException.supplierOf(login));
    }

    public boolean isExists(String login) {
        return repository.existsByLogin(login);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User findByTemporaryKey(String key) {
        return repository.findByTemporaryKey(key)
                .orElseThrow(RuntimeException::new); //fixme
    }
}
