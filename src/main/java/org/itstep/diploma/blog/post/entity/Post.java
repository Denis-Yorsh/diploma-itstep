package org.itstep.diploma.blog.post.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.itstep.diploma.configs.security.entity.User;
import org.itstep.diploma.image.entity.Image;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Table(name = "post_table")
@Component
@Data
@EqualsAndHashCode(exclude = {"id", "image", "user"})
public final class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;
	@Column(name = "post_title", length = 100, nullable = false)
	private String postTitle;
	@Column(length = 50, nullable = false)
	private String author;
	@Column(length = 10_000, name = "text_of_post", nullable = false)
	private String textOfPost;
	@Column(name = "date_of_post", nullable = false)
	private LocalDate dateOfPost;
	@OneToOne(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Image image;
	@ManyToOne
	@JoinColumn(name = "fk_user_id", nullable = false)
	private User user;

	public void addImage(Image image) {
		image.setPost(this);
		this.image = image;
	}

	private void removeImage() {
		if (image != null) {
			image.setPost(null);
			this.image = null;
		}
	}
}
