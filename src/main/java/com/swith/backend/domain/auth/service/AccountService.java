package com.swith.backend.domain.auth.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swith.backend.domain.auth.presentation.dto.request.ChangePasswordRequest;
import com.swith.backend.domain.auth.presentation.dto.response.AccountIdResponse;
import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.domain.user.domain.User;
import com.swith.backend.domain.user.domain.repository.UserRepository;
import com.swith.backend.global.exception.IdConflictException;
import com.swith.backend.global.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthUtil authUtil;

	public void isIdOverlap(String userId) {
		if(userRepository.findByUserId(userId).isPresent()) {
			throw IdConflictException.EXCEPTION;
		}
	}

	public AccountIdResponse findAccountId(String email) {
		return new AccountIdResponse(userRepository.findByPhoneNumber(email)
			.orElseThrow(() -> UserNotFoundException.EXCEPTION).getUserId());
	}

	@Transactional
	public void changePassword(ChangePasswordRequest request) {
		User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
			.orElseThrow(() -> UserNotFoundException.EXCEPTION);
		user.changePassword(passwordEncoder.encode(request.getPassword()));
		userRepository.save(user);
	}

	@Transactional
	public void changePhoneNumber(String phoneNumber) {
		userRepository.save(authUtil.getUser().changePhoneNumber(phoneNumber));
	}

}

