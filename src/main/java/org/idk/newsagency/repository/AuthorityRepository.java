package org.idk.newsagency.repository;

import org.idk.newsagency.entity.Authority;
import org.idk.newsagency.entity.enumeration.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends AbstractRepository<Authority> {

    Optional<Authority> findByRole(Role role);
}
