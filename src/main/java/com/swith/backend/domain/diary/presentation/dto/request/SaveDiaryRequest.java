package com.swith.backend.domain.diary.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.swith.backend.domain.diary.domain.type.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveDiaryRequest {

	@NotBlank
	@Size(min = 1, max = 20)
	private String title;

	@NotBlank
	@Size(max = 2000)
	private String content;

	private Status status;

	@NotBlank
	private String path;

}
