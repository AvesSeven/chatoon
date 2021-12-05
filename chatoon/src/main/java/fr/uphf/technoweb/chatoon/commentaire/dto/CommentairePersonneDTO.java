package fr.uphf.technoweb.chatoon.commentaire.dto;

import fr.uphf.technoweb.chatoon.chat.dto.ChatDTO;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;

import java.time.LocalDate;

public class CommentairePersonneDTO {
    private Long id;
    private LocalDate date;
    private String commentaire;
    private ChatDTO chat;

    public CommentairePersonneDTO() {
        super();
    }

    public CommentairePersonneDTO(Commentaire commentaire) {
        this.id = commentaire.getIdCommentaire();
        this.date = commentaire.getDateCommentaire();
        this.commentaire = commentaire.getCommentaire();
        this.chat = new ChatDTO(commentaire.getChatCommentaire());
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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public ChatDTO getChat() {
        return this.chat;
    }

    public void setChat(ChatDTO chat) {
        this.chat = chat;
    }
}
