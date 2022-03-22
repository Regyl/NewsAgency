package org.idk.newsagency.service;

import org.idk.newsagency.entity.User;
import org.idk.newsagency.exception.EntityNotFoundException;
import org.idk.newsagency.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(EntityNotFoundException.supplierOf(login));
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User findByTemporaryKey(String key) {
        return repository.findByTemporaryKey(key)
                .orElseThrow(RuntimeException::new); //fixme
    }
}
