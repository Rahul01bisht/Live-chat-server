package com.chat.work.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.chat.work.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import com.chat.work.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ChatController{
  @Autowired
  private ChatRepository repo;
  
  @GetMapping("/")
  public String home(){
    return "chat";
  }
  @MessageMapping("/chat")
  @SendTo("/topic/messages")
  public ChatMessage receiveMessage(ChatMessage message){
    repo.save(message);
    System.out.println("Message Receive: "+ message.getContent());
    return message;
  }
}