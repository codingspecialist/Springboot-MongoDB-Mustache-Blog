package com.cos.blog.web;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;
import com.cos.blog.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;
	
	@PostMapping("/user")
	public CMRespDto<?> save(@RequestBody User user){ 
		user.setCreatedate(LocalDateTime.now().plusHours(9)); // UTC 타임존 변경 불가능으로 인해!!
		User userDocument = userRepository.save(user);
		return new CMRespDto<>(1, "저장성공", userDocument);
	}
	
	@PostMapping("/login")
	public CMRespDto<?> login(@RequestBody User user){ 
		User userDocument = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		if(userDocument != null) {
			session.setAttribute("principal", userDocument);
			return new CMRespDto<>(1, "로그인성공", userDocument);
		}else {
			return new CMRespDto<>(-1, "로그인실패", userDocument);
		}
		
	}
}
