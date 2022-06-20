package com.github.juliocesarscheidt.domain.repository;

import java.util.List;

import com.github.juliocesarscheidt.domain.entity.Message;

public interface MessageRepository {

	public Message createMessage(Message message);

	public Message findMessageById(Long id);

	public List<Message> findMessageByUserId(Long userId);
	
	public List<Message> listMessage();
}
