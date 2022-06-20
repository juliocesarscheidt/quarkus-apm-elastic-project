package com.github.juliocesarscheidt.infra.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.github.juliocesarscheidt.domain.entity.Message;
import com.github.juliocesarscheidt.domain.repository.MessageRepository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MessageRepositoryImpl implements MessageRepository, PanacheRepository<Message> {

	@Override
	public Message createMessage(Message message) {
		persistAndFlush(message);
		return message;
	}

	@Override
	public Message findMessageById(Long id) {
		return findById(id);
	}

	@Override
	public List<Message> findMessageByUserId(Long userId) {
		return find("user_id", userId).list();
	}

	@Override
	public List<Message> listMessage() {
		return listAll();
	}	
}
