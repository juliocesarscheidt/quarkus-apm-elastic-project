package com.github.juliocesarscheidt.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.github.juliocesarscheidt.application.dto.MessageCreateRequestDto;
import com.github.juliocesarscheidt.application.dto.MessageCreateResponseDto;
import com.github.juliocesarscheidt.application.dto.MessageDto;
import com.github.juliocesarscheidt.application.service.MessageService;
import com.github.juliocesarscheidt.domain.entity.Message;
import com.github.juliocesarscheidt.domain.repository.MessageRepository;

@ApplicationScoped
public class MessageServiceImpl implements MessageService {

	private final MessageRepository messageRepository;
	private final Mapper mapper;

	@Inject
	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
		mapper = new DozerBeanMapper();
	}

	@Transactional
	@Override
	public MessageCreateResponseDto createMessage(MessageCreateRequestDto dto) {
		Message message = mapper.map(dto, Message.class);
		if (message == null) return null;
		messageRepository.createMessage(message);
		return new MessageCreateResponseDto(message.getId());
	}

	@Override
	public MessageDto findMessageById(Long id) {
		Message message = messageRepository.findMessageById(id);
		if (message == null) return null;
		return mapper.map(message, MessageDto.class);
	}

	@Override
	public List<MessageDto> findMessagesByUserId(Long userId) {
		List<Message> messages = messageRepository.findMessagesByUserId(userId);
		if (messages == null) return null;
		return messages.stream()
			.map(message -> mapper.map(message, MessageDto.class))
			.collect(Collectors.toList());
	}

	@Override
	public List<MessageDto> listMessage() {
		List<Message> messages = messageRepository.listMessage();
		if (messages == null) return null;
		return messages.stream()
			.map(message -> mapper.map(message, MessageDto.class))
			.collect(Collectors.toList());
	}
}
