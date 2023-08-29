package org.itstep.diploma.image.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.itstep.diploma.post.entity.Post;
import org.springframework.stereotype.Component;

@Entity
@Data
@Component
@Table(name = "image_table")
@EqualsAndHashCode(exclude = {"id", "post"})
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(name = "original_filename", nullable = false)
	private String originalFilename;
	@Column(name = "content_type", nullable = false)
	private String contentType;
	@Column(nullable = false)
	private Long size;
	@Lob
	@Column(nullable = false)
	private byte[] bytes;
	@Column(name = "find_image", nullable = false)
	private Long findImage;
	@OneToOne
	@JoinColumn(name = "fk_post_id", nullable = false)
	private Post post;
}
