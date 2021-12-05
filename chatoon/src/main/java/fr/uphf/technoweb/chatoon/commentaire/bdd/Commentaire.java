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
    private Long id;
    private LocalDate date;
    private String message;
    @ManyToOne
    private Chat chat;
    @ManyToOne
    private Personne personne;

    public Commentaire() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idCommentaire) {
        this.id = idCommentaire;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateCommentaire) {
        this.date = dateCommentaire;
    }

    public fr.uphf.technoweb.chatoon.chat.bdd.Chat getChat() {
        return chat;
    }

    public void setChat(fr.uphf.technoweb.chatoon.chat.bdd.Chat chatCommentaire) {
        this.chat = chatCommentaire;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personneCommentaire) {
        this.personne = personneCommentaire;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String commentaire) {
        this.message = commentaire;
    }
}
