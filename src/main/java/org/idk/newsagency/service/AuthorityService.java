package org.idk.newsagency.service;

import org.idk.newsagency.entity.Authority;
import org.idk.newsagency.entity.enumeration.Role;
import org.idk.newsagency.exception.EntityNotFoundException;
import org.idk.newsagency.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record AuthorityService(AuthorityRepository repository) {

    public Authority findByRole(Role role) {
        return repository.findByRole(role)
                .orElseThrow(EntityNotFoundException.supplierOf(role.name()));
    }

    public List<Authority> findAll() {
        return repository.findAll();
    }

    public List<Authority> saveAll(List<Authority> authorities) {
        return repository.saveAll(authorities);
    }
}
