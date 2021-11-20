package fr.uphf.technoweb.chatoon.personne.bdd;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;

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
    @OneToMany
    private List<Chat> chatsPersonne;

    public Personne() {
        super();
        this.chatsPersonne = new ArrayList<>();
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
}
