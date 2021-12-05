package fr.uphf.technoweb.chatoon.commentaire.dto;

import fr.uphf.technoweb.chatoon.chat.dto.ChatDTO;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;

import java.time.LocalDate;

public class CommentairePersonneDTO {
    private Long id;
    private LocalDate date;
    private String message;
    private ChatDTO chat;

    public CommentairePersonneDTO() {
        super();
    }

    public CommentairePersonneDTO(Commentaire commentaire) {
        this.id = commentaire.getId();
        this.date = commentaire.getDate();
        this.message = commentaire.getMessage();
        this.chat = new ChatDTO(commentaire.getChat());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatDTO getChat() {
        return this.chat;
    }

    public void setChat(ChatDTO chat) {
        this.chat = chat;
    }
}
