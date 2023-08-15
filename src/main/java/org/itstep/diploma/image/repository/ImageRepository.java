package org.itstep.diploma.image.repository;

import org.itstep.diploma.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
