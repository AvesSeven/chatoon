package fr.uphf.technoweb.chatoon.personne.dto;

import fr.uphf.technoweb.chatoon.personne.bdd.Personne;

public class PersonneDTO {
    private Long id;
    private String pseudo;

    public PersonneDTO() {
        super();
    }

    public PersonneDTO(Personne personne) {
        this.id = personne.getIdPersonne();
        this.pseudo = personne.getPseudoPersonne();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

}
