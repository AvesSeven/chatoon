package fr.uphf.technoweb.chatoon.chat.bdd;

import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idChat;
    private String nomChat;
    //private String photoChat;
    private String descriptionChat;
    //private int cptLikeChat;
    @OneToMany
    private List<Commentaire> commentaireChat;

    @ManyToOne
    private Personne personneChat;

    public Chat() {
        super();
        this.commentaireChat = new ArrayList<>();
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public String getNomChat() {
        return nomChat;
    }

    public void setNomChat(String nomChat) {
        this.nomChat = nomChat;
    }

//    public String getPhotoChat() {
//        return photoChat;
//    }
//
//    public void setPhotoChat(String photoChat) {
//        this.photoChat = photoChat;
//    }

    public String getDescriptionChat() {
        return descriptionChat;
    }

    public void setDescriptionChat(String descriptionChat) {
        this.descriptionChat = descriptionChat;
    }

//    public int getCptLikeChat() {
//        return cptLikeChat;
//    }
//
//    public void setCptLikeChat(int cptLikeChat) {
//        this.cptLikeChat = cptLikeChat;
//    }

    public List<Commentaire> getCommentaireChat() {
        return commentaireChat;
    }

    public void setCommentaireChat(List<Commentaire> commentaireChat) {
        this.commentaireChat = commentaireChat;
    }

    public Personne getPersonneChat() {
        return personneChat;
    }

    public void setPersonneChat(Personne personneChat) {
        this.personneChat = personneChat;
    }
}
