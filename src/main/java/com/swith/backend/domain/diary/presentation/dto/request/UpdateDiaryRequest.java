package com.swith.backend.domain.diary.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateDiaryRequest {

	@NotBlank
	@Size(min = 1, max = 20)
	private String title;

	@NotBlank
	@Size(max = 2000)
	private String content;

	private Boolean isStatus;

}
