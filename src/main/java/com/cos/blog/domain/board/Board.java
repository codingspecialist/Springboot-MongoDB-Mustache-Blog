package com.cos.blog.domain.board;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cos.blog.domain.user.User;

import lombok.Data;

@Data
@Document(collection = "boards")
public class Board implements Cloneable{
	@Id
	private String _id;
	
	private String title;
	private String content;
	
	private User user;
	
	private LocalDateTime createdate;
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
