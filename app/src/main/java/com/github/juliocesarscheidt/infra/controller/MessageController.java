package com.github.juliocesarscheidt.infra.controller;

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

import com.github.juliocesarscheidt.application.dto.HttpResponseDto;
import com.github.juliocesarscheidt.application.dto.HttpResponseDtoBuilder;
import com.github.juliocesarscheidt.application.dto.MessageCreateRequestDto;
import com.github.juliocesarscheidt.application.service.MessageService;

@Path("/v1/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageController {

    private final MessageService messageService;

    @Inject
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    @Status
    public Response createMessage(@Valid MessageCreateRequestDto dto) {
      HttpResponseDto response = new HttpResponseDtoBuilder()
        .setData(messageService.createMessage(dto))
        .setStatusCode(201).build();
      return Response.status(201).entity(response).build();
    }

    @GET
    @Path("/{id}")
    public Response findMessageById(@PathParam("id") long id) {
      HttpResponseDto response = new HttpResponseDtoBuilder()
        .setData(messageService.findMessageById(id))
        .setStatusCode(200).build();
      return Response.status(200).entity(response).build();
    }

    @GET
    @Path("/user/{user_id}")
    public Response findMessageByUserId(@PathParam("user_id") long userId) {
      HttpResponseDto response = new HttpResponseDtoBuilder()
        .setData(messageService.findMessageByUserId(userId))
        .setStatusCode(200).build();
      return Response.status(200).entity(response).build();
    }

    @GET
    public Response listMessage() {
      HttpResponseDto response = new HttpResponseDtoBuilder()
        .setData(messageService.listMessage())
        .setStatusCode(200).build();
      return Response.status(200).entity(response).build();
    }
}
