package com.swith.backend.domain.like.pressentation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swith.backend.domain.like.service.LikeService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/diary/like")
@RequiredArgsConstructor
@RestController
public class LikeController {

	private final LikeService likeService;

	@PostMapping("/{id}")
	public void saveOrDeleteLike(@PathVariable("id") Long diaryId) {
		likeService.saveOrDeleteLike(diaryId);
	}

}
