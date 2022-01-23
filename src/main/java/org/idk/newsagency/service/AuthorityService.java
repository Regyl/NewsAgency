package org.idk.newsagency.service;

import org.idk.newsagency.entity.Authority;
import org.idk.newsagency.entity.enumeration.Role;
import org.idk.newsagency.exception.EntityNotFoundException;
import org.idk.newsagency.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    private final AuthorityRepository repository;

    public AuthorityService(AuthorityRepository repository) {
        this.repository = repository;
    }

    public Authority findByRole(Role role) {
        return repository.findByRole(role)
                .orElseThrow(EntityNotFoundException.supplierOf(role.name()));
    }
}
