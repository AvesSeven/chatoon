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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String photo;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="chat", cascade = CascadeType.REMOVE)
    private List<Commentaire> commentaires;
    @ManyToOne
    private Personne personne;

    public Chat() {
        super();
        this.commentaires = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idChat) {
        this.id = idChat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomChat) {
        this.nom = nomChat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photoChat) {
        this.photo = photoChat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptionChat) {
        this.description = descriptionChat;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaireChat) {
        this.commentaires = commentaireChat;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personneChat) {
        this.personne = personneChat;
    }
}
