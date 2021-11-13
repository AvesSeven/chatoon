package fr.uphf.technoweb.chatoon.commentaire.bdd;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idCommentaire;
    private LocalDate dateCommentaire;
    @ManyToOne
    private Chat chatCommentaire;
    @ManyToOne
    private Personne personneCommentaire;

    public Commentaire() {
        super();
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public LocalDate getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(LocalDate dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public Chat getChatCommentaire() {
        return chatCommentaire;
    }

    public void setChatCommentaire(Chat chatCommentaire) {
        this.chatCommentaire = chatCommentaire;
    }

    public Personne getPersonneCommentaire() {
        return personneCommentaire;
    }

    public void setPersonneCommentaire(Personne personneCommentaire) {
        this.personneCommentaire = personneCommentaire;
    }
}
