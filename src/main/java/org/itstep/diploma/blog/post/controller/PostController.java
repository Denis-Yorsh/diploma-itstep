package org.itstep.diploma.blog.post.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.image.entity.Image;
import org.itstep.diploma.blog.post.dto.PostDto;
import org.itstep.diploma.blog.post.entity.Post;
import org.itstep.diploma.blog.post.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@PostMapping("/homePost")
	public String homePost() {
		return "blog/addPost";
	}

	@PostMapping("/addPost")
	@ResponseBody
	public String addPost(
			@RequestParam("imageFile") MultipartFile imageFile,
			PostDto postDto,
			Post post,
			Image image,
			Authentication authentication) {
		return postService.addPost(imageFile, postDto, post, image, authentication);
	}

	@PostMapping("/allPosts")
	public String getAllPosts(Model model) {
		Optional<List<Post>> allPost = postService.getAllPost();
		allPost.ifPresent(posts -> model.addAttribute("allPosts", posts));
		return "blog/allPosts";
	}

	@DeleteMapping("/deletePost")
	public String deletePost(PostDto postDto, Model model) {
		postService.deletePost(postDto.getId());
		Optional<List<Post>> allPost = postService.getAllPost();
		allPost.ifPresent(posts -> model.addAttribute("allPosts", posts));
		return "blog/allPosts";
	}
}
