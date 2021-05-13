package com.cos.blog.domain.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cos.blog.domain.board.Board;

import lombok.Data;

@Data
@Document(collection = "users")
public class User implements Cloneable{
	
	@Id
	private String _id;
	
	private String username;
	private String password;
	
	private List<Board> boards = new ArrayList<>();
	
	private LocalDateTime createdate;
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
