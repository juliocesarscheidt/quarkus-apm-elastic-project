package com.github.juliocesarscheidt.application.service;

import java.util.List;

import com.github.juliocesarscheidt.application.dto.MessageCreateRequestDto;
import com.github.juliocesarscheidt.application.dto.MessageCreateResponseDto;
import com.github.juliocesarscheidt.application.dto.MessageDto;

public interface MessageService {

	public MessageCreateResponseDto createMessage(MessageCreateRequestDto dto);

	public MessageDto findMessageById(Long id);

	public List<MessageDto> findMessageByUserId(Long userId);

	public List<MessageDto> listMessage();
}
