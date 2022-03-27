package com.deepspace.newsagency.repository;

import com.deepspace.newsagency.entity.Like;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeRepository extends AbstractRepository<Like> {

    List<Like> findAllByAnnouncement_Id(UUID id);
}
