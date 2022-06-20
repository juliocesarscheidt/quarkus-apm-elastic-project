package com.github.juliocesarscheidt.application.dto;

import com.google.gson.annotations.SerializedName;

public class MessageCreateRequestDto {
	
	@SerializedName("userId")
	private Long userId;

	@SerializedName("content")
	private String content;

	public MessageCreateRequestDto() {	
	}
	
	public MessageCreateRequestDto(Long userId, String content) {
		this.userId = userId;
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MessageCreateRequestDto [userId=" + userId + ", content=" + content + "]";
	}
}
