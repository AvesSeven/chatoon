package fr.uphf.technoweb.chatoon.chat.bdd;

import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idChat;
    private String nomChat;
    private String photoChat;
    private String descriptionChat;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="chatCommentaire")
    private List<Commentaire> commentaireChat;
    @ManyToOne
    private Personne personneChat;

    public Chat() {
        super();
        this.commentaireChat = new ArrayList<>();
    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public String getNomChat() {
        return nomChat;
    }

    public void setNomChat(String nomChat) {
        this.nomChat = nomChat;
    }

    public String getPhotoChat() {
        return photoChat;
    }

    public void setPhotoChat(String photoChat) {
        this.photoChat = photoChat;
    }

    public String getDescriptionChat() {
        return descriptionChat;
    }

    public void setDescriptionChat(String descriptionChat) {
        this.descriptionChat = descriptionChat;
    }

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
