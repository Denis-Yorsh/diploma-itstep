package org.itstep.diploma.controllers.blog.service;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.post.entity.Post;
import org.itstep.diploma.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogServiceImp implements BlogService {
	private final PostRepository postRepository;
	@Transactional(readOnly = true)
	@Override
	public Optional<List<Post>> getAllPosts() {
		List<Post> allPosts = postRepository.findAll();
		if (allPosts.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(allPosts);
	}
}
