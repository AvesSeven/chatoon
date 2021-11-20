package fr.uphf.technoweb.chatoon.chat.bdd;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository  extends CrudRepository<Chat, Long> {
}
