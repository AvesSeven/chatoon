package fr.uphf.technoweb.chatoon.commentaire.bdd;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Commentaire implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idCommentaire;
    private LocalDate dateCommentaire;
    private String commentaire;
    @ManyToOne
    private Chat chatCommentaire;
    @ManyToOne
    private Personne personneCommentaire;

    public Commentaire() {
        super();
    }

    public Long getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(Long idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public LocalDate getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(LocalDate dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public fr.uphf.technoweb.chatoon.chat.bdd.Chat getChatCommentaire() {
        return chatCommentaire;
    }

    public void setChatCommentaire(fr.uphf.technoweb.chatoon.chat.bdd.Chat chatCommentaire) {
        this.chatCommentaire = chatCommentaire;
    }

    public Personne getPersonneCommentaire() {
        return personneCommentaire;
    }

    public void setPersonneCommentaire(Personne personneCommentaire) {
        this.personneCommentaire = personneCommentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
