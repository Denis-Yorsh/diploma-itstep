package org.itstep.diploma.post.repository;

import org.itstep.diploma.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
