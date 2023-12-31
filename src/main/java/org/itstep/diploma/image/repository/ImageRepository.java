package org.itstep.diploma.image.repository;

import org.itstep.diploma.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findImageByFindImage(Long id);
}
