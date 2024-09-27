package com.kodbook.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entities.Post;
import com.kodbook.entities.PostResponse;
import com.kodbook.entities.User;
import com.kodbook.entities.UserPosted;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;


@CrossOrigin("*")
@RestController
public class PostController {
	@Autowired
	PostService service;
	@Autowired
	UserService userService;

	@PostMapping("/createPost")
	public String createPost(@RequestParam("caption") String caption,
			@RequestParam("photo") MultipartFile photo,
			@RequestParam("username") String username) {

		User user = userService.getUser(username);

		Post post = new Post();
		//updating post object
		post.setUser(user);

		post.setCaption(caption);
		try {						
			post.setPhoto(photo.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.createPost(post);
		//updating user object
		List<Post> posts = user.getPosts();
		if(posts == null) {
			posts = new ArrayList<Post>();
		}
		posts.add(post);
		user.setPosts(posts);
		userService.updateUser(user);

		return "post created";
	}

	@GetMapping("/getAllPosts")
	public List<PostResponse> getAllPosts() {
		List<Post> posts = service.fetchAllPosts();
		List<PostResponse> postsResponses = new ArrayList<PostResponse>();
		for(Post p:posts) {
			
			PostResponse pr = new PostResponse(p.getId(), p.getCaption(), p.getLikes(), p.getComments(), p.getPhoto(), new UserPosted(p.getUser().getUsername(), p.getUser().getProfilePic()));
			postsResponses.add(pr);
		}
		return postsResponses;
	}

	@PostMapping("/likePost")
	public int likePost(@RequestParam Long id, Model model) {
		Post post= service.getPost(id);
		post.setLikes(post.getLikes() + 1);
		service.updatePost(post);

		
		return post.getLikes();
	}

	@PostMapping("/addComment")
	public Post addComment(@RequestParam Long id, 
			@RequestParam String comment, Model model) {
		
		Post post= service.getPost(id);
		List<String> comments = post.getComments();
		if(comments == null) {
			comments = new ArrayList<String>();
		}
		comments.add(comment);
		post.setComments(comments);
		service.updatePost(post);

		return post;
	}
}
