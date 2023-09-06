package org.itstep.diploma.blog.post.service;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.blog.post.dto.PostDto;
import org.itstep.diploma.blog.post.entity.Post;
import org.itstep.diploma.blog.post.repository.PostRepository;
import org.itstep.diploma.configs.security.entity.User;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.image.entity.Image;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService {
	private final UserRepository userRepository;
	private final PostRepository postRepository;

	@Transactional
	@Override
	public String addPost(MultipartFile multipartFile,
						  PostDto postDto,
						  Post post,
						  Image image,
						  Authentication authentication) {
		String response;
		try {
			Optional<User> optionalUserByUsername = userRepository.findByUsername(authentication.getName());
			if (optionalUserByUsername.isEmpty()) {
				response = "YOU MUST RESTART YOUR ACCOUNT PLEASE";
				return response;
			}
			User user = optionalUserByUsername.get();
			post.setPostTitle(postDto.getPostTitle());
			post.setAuthor(user.getUsername());
			post.setTextOfPost(postDto.getTextOfPost());
			post.setDateOfPost(LocalDate.now());

			user.addPost(post);
			userRepository.flush();

			image.setName(multipartFile.getName());
			image.setOriginalFilename(multipartFile.getOriginalFilename());
			image.setContentType(multipartFile.getContentType());
			image.setSize(multipartFile.getSize());
			image.setBytes(multipartFile.getBytes());
			image.setFindImage(post.getId());

			post.addImage(image);

			response = "post was be added";
		} catch (Exception e) {
			response = e.getMessage();
		}
		return response;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<List<Post>> getAllPost() {
		List<Post> all = postRepository.findAll();
		if (all.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(all);
	}

	@Transactional
	@Override
	public void deletePost(Long id) {
		Optional<Post> optionalById = postRepository.findById(id);
		optionalById.ifPresent(postRepository::delete);
	}
}
