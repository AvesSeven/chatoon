package fr.uphf.technoweb.chatoon.commentaire.dto;

import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDTO;

import java.time.LocalDate;

public class CommentaireDTO {
    private Long id;
    private LocalDate date;
    private String commentaire;
    private PersonneDTO personne;

    public CommentaireDTO() {
        super();
    }

    public CommentaireDTO(Commentaire commentaire) {
        this.id = commentaire.getIdCommentaire();
        this.date = commentaire.getDateCommentaire();
        this.commentaire = commentaire.getCommentaire();
        this.personne = new PersonneDTO(commentaire.getPersonneCommentaire());
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

    public PersonneDTO getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDTO personne) {
        this.personne = personne;
    }
}