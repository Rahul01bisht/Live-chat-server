package com.chat.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chat.work.model.ChatMessage;
public interface ChatRepository extends JpaRepository<ChatMessage , Integer>{
  
}