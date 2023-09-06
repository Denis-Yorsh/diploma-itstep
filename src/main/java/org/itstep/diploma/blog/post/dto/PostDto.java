package org.itstep.diploma.blog.post.dto;

import lombok.Data;

@Data
public final class PostDto {
	private Long id;
	private String postTitle;
	private String textOfPost;
}
