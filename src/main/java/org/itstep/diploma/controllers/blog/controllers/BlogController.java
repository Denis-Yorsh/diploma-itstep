package org.itstep.diploma.controllers.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.controllers.blog.service.BlogService;
import org.itstep.diploma.post.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blog/")
@RequiredArgsConstructor
public class BlogController {
	private final BlogService blogService;

	@GetMapping("/homePage")
	public String homePage(Model model) {
		Optional<List<Post>> optionalPosts = blogService.getAllPosts();
		optionalPosts.ifPresent(posts -> model.addAttribute("allPost", posts));
		return "blog/blogHomePage";
	}
}
