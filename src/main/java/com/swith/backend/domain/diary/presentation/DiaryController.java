package com.swith.backend.domain.diary.presentation;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.swith.backend.domain.diary.presentation.dto.request.SaveDiaryRequest;
import com.swith.backend.domain.diary.presentation.dto.request.UpdateDiaryRequest;
import com.swith.backend.domain.diary.presentation.dto.response.DiaryResponse;
import com.swith.backend.domain.diary.presentation.dto.response.DiaryResponses;
import com.swith.backend.domain.diary.service.DiaryListService;
import com.swith.backend.domain.diary.service.DiaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

	private final DiaryService diaryService;
	private final DiaryListService diaryListService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveDiary(@RequestBody @Valid SaveDiaryRequest request,
		@RequestParam(value = "file") MultipartFile file) {
		return diaryService.saveDiary(request, file);
	}

	@PutMapping("/{id}")
	public Long updateDiary(@PathVariable("id") Long diaryId, @RequestBody @Valid UpdateDiaryRequest request) {
		return diaryService.updateDiary(diaryId, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDiary(@PathVariable("id") Long diaryId) {
		diaryService.deleteDiary(diaryId);
	}

	@GetMapping
	public DiaryResponses getDiaryList(@PageableDefault Pageable page) {
		return diaryListService.getDiary(page);
	}

	@GetMapping("/my")
	public DiaryResponses getMyDiaryList(@PageableDefault Pageable page) {
		return diaryListService.getMyDiary(page);
	}

	@GetMapping("/{id}")
	public DiaryResponse getDiary(@PathVariable("id") Long diaryId) {
		return diaryService.getDiary(diaryId);
	}

}
