package com.github.juliocesarscheidt.application.dto;

import com.google.gson.annotations.SerializedName;

public class MessageCreateResponseDto {

	@SerializedName("id")
	private Long id;

	public MessageCreateResponseDto() {	
	}

	public MessageCreateResponseDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MessageCreatedDto [id=" + id + "]";
	}
}
