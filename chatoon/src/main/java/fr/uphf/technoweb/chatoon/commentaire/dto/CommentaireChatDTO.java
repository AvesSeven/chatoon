package fr.uphf.technoweb.chatoon.commentaire.dto;

import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDTO;

import java.time.LocalDate;

public class CommentaireChatDTO {
    private Long id;
    private LocalDate date;
    private String message;
    private PersonneDTO personne;

    public CommentaireChatDTO() {
        super();
    }

    public CommentaireChatDTO(Commentaire commentaire) {
        this.id = commentaire.getId();
        this.date = commentaire.getDate();
        this.message = commentaire.getMessage();
        this.personne = new PersonneDTO(commentaire.getPersonne());
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

    public PersonneDTO getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDTO personne) {
        this.personne = personne;
    }
}