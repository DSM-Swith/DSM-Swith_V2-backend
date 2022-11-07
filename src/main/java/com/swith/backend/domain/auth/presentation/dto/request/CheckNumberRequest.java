package com.swith.backend.domain.auth.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class CheckNumberRequest {

	@NotBlank
	@Size(max = 13)
	private String phoneNumber;

	@NotBlank
	@Size(max = 5)
	private String number;

}
