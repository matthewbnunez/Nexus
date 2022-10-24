package com.meta.nexus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meta.nexus.models.Comment;
import com.meta.nexus.models.Share;
import com.meta.nexus.models.User;
import com.meta.nexus.repositories.CommentRepository;
import com.meta.nexus.repositories.ShareRepository;
import com.meta.nexus.repositories.UserRepository;

@Service
public class ShareService {
	@Autowired
	private ShareRepository shareRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private CommentRepository commentRepo;
	
//		==========all posts============
	public List<Share> allPosts(){
		return shareRepo.findAll();
	}

//	=============get all users================
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	
//	==========create post============
	
	public Share createPost(Share share) {
//		share.setCommentCount(0);
		share.setVote(0);
		return shareRepo.save(share);
	}
	
//	===========get one share================================
	
	public Share oneShare(Long Id) {
		Optional<Share> singleShare = shareRepo.findById(Id);
		if(singleShare.isPresent()) {
			return singleShare.get();
		} else {
			return null;
		}
	}
	
//	==============get like=================
	public void receiveVote(Long shareId, Long userId) {
		Share share = this.oneShare(shareId);	
		User user = userService.oneUser(userId);
	
		share.getReceivers().add(user);
		share.setVote(share.getVote()+1);
		
	
		
		shareRepo.save(share);
		}
	
//	==============get comment count=================
//	public void receiveCommentCount(Long shareId) {
//		Share share = this.oneShare(shareId);	
////		User user = userService.oneUser(userId);
////		share.getReceivers().add(user);
//		share.setCommentCount(share.getCommentCount()+1);
//		shareRepo.save(share);
//		}
	
//	==============unlike==============
	public void unlike(Long shareId, Long userId) {
		Share share = this.oneShare(shareId);	
		User user = userService.oneUser(userId);
		share.getReceivers().remove(user);
		share.setVote(share.getVote()-1);
		shareRepo.save(share);
	}
	

	
//	==========create comment============
	
	public Comment createComment(Long shareId, Comment comment) {	
		Share share = oneShare(shareId);
		share.setCommentCount(share.getCommentCount()+1);
		return commentRepo.save(comment);
	}
	
//	==========comments posts============
public List<Comment> allComments(){
	return commentRepo.findAll();
}

//===========get one share================================

public Comment oneCom(Long Id) {
	Optional<Comment> singleComment = commentRepo.findById(Id);
	if(singleComment.isPresent()) {
		return singleComment.get();
	} else {
		return null;
	}
}

//============== delete ====================

public void deletePost(Long id) {
	shareRepo.deleteById(id);
}

// ================ get all user posts in Descending order============

public List<Share> queryTest(){
	return shareRepo.findAllByOrderByCreatedAtDesc();
}
// ================get all comments in Descending=====================
public List<Comment> allCommentsDesc(){
	return commentRepo.findAllByOrderByCreatedAtDesc();
}


}
