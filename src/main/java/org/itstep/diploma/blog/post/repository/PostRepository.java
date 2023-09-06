package org.itstep.diploma.blog.post.repository;

import org.itstep.diploma.blog.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
