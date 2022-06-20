package com.github.juliocesarscheidt.infra.controller;

import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Status;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

import com.github.juliocesarscheidt.application.dto.MessageDto;
import com.github.juliocesarscheidt.application.dto.HttpResponseDto;
import com.github.juliocesarscheidt.application.dto.HttpResponseDtoBuilder;
import com.github.juliocesarscheidt.application.dto.MessageCreateRequestDto;
import com.github.juliocesarscheidt.application.service.MessageService;

import co.elastic.apm.api.CaptureTransaction;

@Path("/v1/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageController {

    private final MessageService messageService;
    private final MeterRegistry registry;

    @Inject
    public MessageController(MessageService messageService, MeterRegistry registry) {
        this.messageService = messageService;
        this.registry = registry;
    }

    @POST
    @Status
    @CaptureTransaction
    public Response createMessage(@Valid MessageCreateRequestDto dto) {
      HttpResponseDto response = new HttpResponseDtoBuilder()
        .setData(messageService.createMessage(dto))
        .setStatusCode(201).build();
      this.registry.counter("create_message_counter", Tags.of("userId", String.valueOf(dto.getUserId()))).increment();
      return Response.status(201).entity(response).build();
    }

    @GET
    @Path("/{id}")
    @CaptureTransaction
    public Response findMessageById(@PathParam("id") long id) {
      MessageDto message = messageService.findMessageById(id);
      HttpResponseDto response = new HttpResponseDtoBuilder()
        .setData(message)
        .setStatusCode(200).build();
      this.registry.counter("find_message_by_id_counter", Tags.of("userId", String.valueOf(message.getUserId()))).increment();
      return Response.status(200).entity(response).build();
    }

    @GET
    @Path("/user/{user_id}")
    @CaptureTransaction
    public Response findMessagesByUserId(@PathParam("user_id") long userId) {
      List<MessageDto> messages = messageService.findMessagesByUserId(userId);
      HttpResponseDto response = new HttpResponseDtoBuilder()
        .setData(messages)
        .setStatusCode(200).build();
      this.registry.counter("find_messages_by_user_id_counter", Tags.of("userId", String.valueOf(userId))).increment();
      return Response.status(200).entity(response).build();
    }

    @GET
    @CaptureTransaction
    public Response listMessage() {
      HttpResponseDto response = new HttpResponseDtoBuilder()
        .setData(messageService.listMessage())
        .setStatusCode(200).build();
      this.registry.counter("list_message_counter").increment();
      return Response.status(200).entity(response).build();
    }
}
