package org.idk.newsagency.repository;

import org.idk.newsagency.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User> {

    Optional<User> findByLogin(String login);

    Optional<User> findByTemporaryKey(String key);

    boolean existsByLogin(String login);

}
