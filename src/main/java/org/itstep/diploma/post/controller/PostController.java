package org.itstep.diploma.post.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.image.entity.Image;
import org.itstep.diploma.post.dto.PostDto;
import org.itstep.diploma.post.entity.Post;
import org.itstep.diploma.post.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
}
