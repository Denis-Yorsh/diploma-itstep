package org.itstep.diploma.image.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.image.entity.Image;
import org.itstep.diploma.image.repository.ImageRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
	private final ImageRepository imageRepository;
	@GetMapping("/")
	public String home(){
		return "image/homeImagePage";
	}

	@PostMapping("/save")
	public String saveImage(@RequestParam("imageFile")MultipartFile file) throws IOException {
		Image image = new Image();
		image.setName(file.getName());
		image.setOriginalFilename(file.getOriginalFilename());
		image.setContentType(file.getContentType());
		image.setSize(file.getSize());
		image.setBytes(file.getBytes());
		imageRepository.save(image);
		return "redirect:/image/";
	}

	@GetMapping("get")
	public String getImagePage() {
		return "image/getImages";
	}
	@GetMapping(value = "/getImage/{id}", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseBody
	public byte[] getImageFromDb(@PathVariable("id") Long id){
		Optional<Image> optionalImage = imageRepository.findById(id);
		Image image = null;
		if (optionalImage.isPresent()) {
			image = optionalImage.get();
		}
		return image != null ? image.getBytes() : new byte[0];
	}
}
