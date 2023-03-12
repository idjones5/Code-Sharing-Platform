package com.company.platform.codesharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.platform.codesharing.models.Comment;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // finding all the comments for a tutorial
    List<Comment> findByCodeId(UUID id);
}
