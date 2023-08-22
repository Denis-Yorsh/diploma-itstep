package org.itstep.diploma.post.service;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.image.entity.Image;
import org.itstep.diploma.post.dto.PostDto;
import org.itstep.diploma.post.entity.Post;
import org.itstep.diploma.post.repository.PostRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService {
	private final PostRepository postRepository;

	@Transactional
	@Override
	public String addPost(MultipartFile multipartFile,
						  PostDto postDto,
						  Post post,
						  Image image,
						  Authentication authentication){
		String response;
		try {
			post.setPostTitle(postDto.getPostTitle());
			post.setAuthor(authentication.getName());
			post.setTextOfPost(postDto.getTextOfPost());
			post.setDateOfPost(LocalDate.now());

			postRepository.save(post);

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
}
