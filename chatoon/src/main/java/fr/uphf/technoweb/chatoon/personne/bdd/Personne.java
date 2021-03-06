package fr.uphf.technoweb.chatoon.personne.bdd;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Personne implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String pseudo;
    @OneToMany(mappedBy= "personne", cascade = CascadeType.REMOVE)
    private List<Chat> chats;
    @OneToMany(fetch = FetchType.EAGER, mappedBy= "personne", cascade = CascadeType.REMOVE)
    private List<Commentaire> commentaires;

    public Personne() {
        super();
        this.chats = new ArrayList<>();
        this.commentaires = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idPersonne) {
        this.id = idPersonne;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudoPersonne) {
        this.pseudo = pseudoPersonne;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chatsPersonne) {
        this.chats = chatsPersonne;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentairesPersonne) {
        this.commentaires = commentairesPersonne;
    }
}
