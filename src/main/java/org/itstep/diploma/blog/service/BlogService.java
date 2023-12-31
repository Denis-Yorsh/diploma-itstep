package org.itstep.diploma.blog.service;

import org.itstep.diploma.blog.post.dto.PostDto;
import org.itstep.diploma.blog.post.entity.Post;

import java.util.List;
import java.util.Optional;

public interface BlogService {

	Optional<List<Post>> getAllPosts();
	Optional<Post> getPostById(PostDto postDto);
}
