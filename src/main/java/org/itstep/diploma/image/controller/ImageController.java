package org.itstep.diploma.image.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.image.entity.Image;
import org.itstep.diploma.image.repository.ImageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
	private final ImageRepository imageRepository;

	@GetMapping(value = "/getImage/{id}")
	@ResponseBody
	public byte[] getImageFromDb(@PathVariable("id") Long id) {
		Optional<Image> optionalFindImage = imageRepository.findImageByFindImage(id);
		Image image = null;
		if (optionalFindImage.isPresent()) {
			image = optionalFindImage.get();
		}
		return image != null ? image.getBytes() : new byte[0];
	}
}
