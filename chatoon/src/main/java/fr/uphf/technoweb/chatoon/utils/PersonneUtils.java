package fr.uphf.technoweb.chatoon.utils;

import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDTO;

import java.util.ArrayList;
import java.util.List;

public class PersonneUtils {
    public static List<PersonneDTO> personneToPersonneDTO(Iterable<Personne> personnes) {
        List<PersonneDTO> personneTemp = new ArrayList<>();

        personnes.forEach((personne) -> {
            personneTemp.add(new PersonneDTO(personne));
        });

        return personneTemp;
    }
}
