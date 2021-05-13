package com.cos.blog.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardRepository;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;
import com.cos.blog.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {
	
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	private final HttpSession session;
	
	@PostMapping("/board")
	public CMRespDto<?> save(@RequestBody Board board) throws CloneNotSupportedException{ 
		// 1. 세션 가져오기
		User principal = (User) session.getAttribute("principal");

		// 2. principal에 board 배열 추가해서 save 할 것이기 때문에 안전하게 깊은 복사하기
		User userDocumentCopy = (User) principal.clone();
		
		// 3. 깊은 복사한 값으로 안전하게 데이터 변경하여 추가하기
		userDocumentCopy.setBoards(null); // 깊은 복사 데이터 망가짐
		board.setUser(userDocumentCopy);
		board.setCreatedate(LocalDateTime.now().plusHours(9));
		Board boardDocument = boardRepository.save(board);
		
		// 4. DB 값 들고오면 항상 깊은 복사하기
		Board boardDocumentCopy = (Board) boardDocument.clone();
		
		// 5. boardDocument 나중에 return시에 사용할 것이기 때문에 안전하게 깊은 복사하기
		boardDocumentCopy.setUser(null); // 깊은 복사 데이터 망가짐
		principal.getBoards().add(boardDocumentCopy);
		userRepository.save(principal);
		
		return new CMRespDto<>(1, "저장성공", boardDocument);
	}
}
