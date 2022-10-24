package com.meta.nexus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meta.nexus.models.Comment;
import com.meta.nexus.repositories.CommentRepository;
import com.meta.nexus.repositories.ShareRepository;
import com.meta.nexus.repositories.UserRepository;

@Service
public class CommentService {
	

	
	@Autowired
	private CommentRepository commentRepo;
	
	
//	==========all comments============
public List<Comment> allComments(){
	return commentRepo.findAll();
}


//==========create post============

public Comment createComment(Comment comment) {
	return commentRepo.save(comment);
}

}
