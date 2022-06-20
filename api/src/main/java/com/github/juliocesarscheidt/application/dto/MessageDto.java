package com.github.juliocesarscheidt.application.dto;

import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

public class MessageDto {

	 private Long id;

	 public MessageDto() {	
	 }
 
	 @SerializedName("userId")
	 private Long userId;

	 @SerializedName("content")
	 private String content;
	 
	 @SerializedName("createdAt")
	 private Timestamp createdAt;
	 
	 @SerializedName("updatedAt")
	 private Timestamp updatedAt;

	 @SerializedName("deletedAt")
	 private Timestamp deletedAt;

	 public Long getId() {
		return id;
	 }

	public void setId(Long id) {
		this.id = id;
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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "MessageDto [id=" + id + ", userId=" + userId + ", content=" + content + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}
}
