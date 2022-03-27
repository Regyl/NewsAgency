package com.deepspace.newsagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, UUID> {
}
