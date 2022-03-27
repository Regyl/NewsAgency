package com.deepspace.newsagency.repository;

import com.deepspace.newsagency.entity.Authority;
import com.deepspace.newsagency.entity.enumeration.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends AbstractRepository<Authority> {

    Optional<Authority> findByRole(Role role);
}
