package fr.uphf.technoweb.chatoon.chat.bdd;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository  extends CrudRepository<Chat, Long> {
    Chat findById(long idChat);
    Chat deleteById(long idChat);
    List<Chat> findAll();
}
