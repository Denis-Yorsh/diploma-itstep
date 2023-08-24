package org.itstep.diploma.post.service;

import org.itstep.diploma.image.entity.Image;
import org.itstep.diploma.post.dto.PostDto;
import org.itstep.diploma.post.entity.Post;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PostService {
	String addPost(MultipartFile multipartFile,
				   PostDto postDto,
				   Post post,
				   Image image,
				   Authentication authentication);
	Optional<List<Post>> getAllPost();
	void deletePost(Long id);
}
