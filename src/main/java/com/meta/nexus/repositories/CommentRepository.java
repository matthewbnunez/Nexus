package com.meta.nexus.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meta.nexus.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
	List<Comment> findAll();
	
	List<Comment> findAllByOrderByCreatedAtDesc();

}
