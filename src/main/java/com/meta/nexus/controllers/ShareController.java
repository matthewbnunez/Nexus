package com.meta.nexus.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.meta.nexus.models.Comment;
import com.meta.nexus.models.Share;
import com.meta.nexus.services.CommentService;
import com.meta.nexus.services.ShareService;
import com.meta.nexus.services.UserService;

@Controller
public class ShareController {
@Autowired
private UserService userService;
@Autowired
private ShareService shareService;

@Autowired
private CommentService commentService;

@GetMapping("/home")
public String renderHome(HttpSession session, Model model,  Share share, Comment comment) {
	if(session.getAttribute("userId")==null) {
		return "redirect:/logout";
	}
	model.addAttribute("newShare", new Share());
	Long userId = (Long) session.getAttribute("userId");
	model.addAttribute("loggedInUser", userService.oneUser(userId));
	model.addAttribute("newcom", new Comment());
	model.addAttribute("newReply", new Comment());
	
	List<Share> allShares = shareService.queryTest();
	model.addAttribute("thePosts", allShares);
	
	List<Comment> allComs = shareService.allCommentsDesc();
	model.addAttribute("theComments", allComs);
	
	Comment oneComment = shareService.oneCom(userId);
	model.addAttribute("singleCom", oneComment);

	return "dashboard.jsp";
	
}

@PostMapping("/share/post/process")
public String newPost(@Valid @ModelAttribute("newShare") Share share, Comment comment,
		BindingResult result, Model model) {
//	Share aShare = new Share();
	if(result.hasErrors()) {
		return "dashboard.jsp";
//		model.addAttribute("newShare", aShare);
	} else {
		shareService.createPost(share);
		return "redirect:/home";
	}
}

//=================creating a comment=========================
@PostMapping("/share/comment/process")
public String newComment(@Valid @ModelAttribute("newcom") Comment comment,
		BindingResult result, Model model, HttpSession session) {
	
	if(result.hasErrors()) {
		return "redirect:/home";
	} else {
		commentService.createComment(comment);
		return "redirect:/home";
	}
}
//====================reply==========================
//@PostMapping("/share/comment/reply")
//public String replyComment(@Valid @ModelAttribute("newReply") Comment comment,
//		BindingResult result, Model model, HttpSession session) {
//	
//	if(result.hasErrors()) {
//		return "redirect:/home";
//	} else {
//
//		commentService.createComment(comment);
//		return "redirect:/home";
//	}
//}



//====================Many to Many Add like==============================
@PutMapping("/shares/{id}/receive")
public String receiveVote(@PathVariable("id")Long id, HttpSession session) {
	Long userId = (Long) session.getAttribute("userId");
	shareService.receiveVote(id, userId);
	return "redirect:/home";
}

//@PutMapping("/shares/{id}/commentcount")
//public String receiveComCount(@PathVariable("id")Long id, HttpSession session) {
//	Long userId = (Long) session.getAttribute("userId");
//	shareService.receiveCommentCount(id, userId);
//	return "redirect:/home";
//}

//====================Many to Many Remove like ==============================
@PutMapping("/shares/{id}/unlike")
public String unlikeVote(@PathVariable("id")Long id, HttpSession session) {
	Long userId = (Long) session.getAttribute("userId");
	shareService.unlike(id, userId);
	return "redirect:/home";
}

//======================== user Details page ==============================
@GetMapping("/shares/{id}/detail")
public String showAllUserPosts(@PathVariable("id") Long id, Model model, HttpSession session) {
	if(session.getAttribute("userId")==null) {
		return "redirect:/logout";
	} else {
		
	List<Share> getShares = shareService.queryTest();
	model.addAttribute("userShare", getShares);
	List<Comment> getUserCom = shareService.allCommentsDesc();
	model.addAttribute("userCom", getUserCom);
	
	return "details.jsp";
	}
}

@DeleteMapping("/shares/{id}/delete")
public String deleteShare(@PathVariable("id") Long id) {
	shareService.deletePost(id);
	return "redirect:/home";
}

	}


