package com.deepspace.newsagency.repository;

import com.deepspace.newsagency.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends AbstractRepository<Comment> {

    List<Comment> findAllByAnnouncement_Id(UUID id);
}
