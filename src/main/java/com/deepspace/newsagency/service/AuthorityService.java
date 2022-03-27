package com.deepspace.newsagency.service;

import com.deepspace.newsagency.entity.Authority;
import com.deepspace.newsagency.entity.enumeration.Role;
import com.deepspace.newsagency.exception.EntityNotFoundException;
import com.deepspace.newsagency.repository.AuthorityRepository;
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
