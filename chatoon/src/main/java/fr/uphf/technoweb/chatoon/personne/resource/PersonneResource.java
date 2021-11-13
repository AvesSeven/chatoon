package fr.uphf.technoweb.chatoon.personne.resource;

import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonneResource {
    @Autowired
    private PersonneRepository personneRepository;

}
