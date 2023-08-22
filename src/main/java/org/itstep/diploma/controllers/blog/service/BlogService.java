package org.itstep.diploma.controllers.blog.service;

import org.itstep.diploma.post.entity.Post;

import java.util.List;
import java.util.Optional;

public interface BlogService {

	Optional<List<Post>> getAllPosts();
}
