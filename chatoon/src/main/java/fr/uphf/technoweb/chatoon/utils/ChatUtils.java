package fr.uphf.technoweb.chatoon.utils;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.chat.dto.ChatDTO;

import java.util.ArrayList;
import java.util.List;

public class ChatUtils {
    public static List<ChatDTO> chatToChatDTO(Iterable<Chat> chats) {
        List<ChatDTO> chatTemp = new ArrayList<>();

        chats.forEach((chat) -> {
            chatTemp.add(new ChatDTO(chat));
        });

        return chatTemp;
    }
}
