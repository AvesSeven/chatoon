package fr.uphf.technoweb.chatoon.personne.bdd;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Personne implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idPersonne;
    private String pseudoPersonne;
//    private String passwordPersonne;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="personneChat")
    private List<Chat> chatsPersonne;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="personneCommentaire")
    private List<Commentaire> commentairesPersonne;

    public Personne() {
        super();
        this.chatsPersonne = new ArrayList<>();
        this.commentairesPersonne = new ArrayList<>();
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getPseudoPersonne() {
        return pseudoPersonne;
    }

    public void setPseudoPersonne(String pseudoPersonne) {
        this.pseudoPersonne = pseudoPersonne;
    }

//    public String getPasswordPersonne() {
//        return passwordPersonne;
//    }
//
//    public void setPasswordPersonne(String passwordPersonne) {
//        this.passwordPersonne = passwordPersonne;
//    }

    public List<Chat> getChatsPersonne() {
        return chatsPersonne;
    }

    public void setChatsPersonne(List<Chat> chatsPersonne) {
        this.chatsPersonne = chatsPersonne;
    }

    public List<Commentaire> getCommentairesPersonne() {
        return commentairesPersonne;
    }

    public void setCommentairesPersonne(List<Commentaire> commentairesPersonne) {
        this.commentairesPersonne = commentairesPersonne;
    }
}
