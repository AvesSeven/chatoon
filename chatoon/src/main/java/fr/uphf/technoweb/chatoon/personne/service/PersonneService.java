package fr.uphf.technoweb.chatoon.personne.service;

import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDTO;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDetailDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {

    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @Transactional
    public List<PersonneDTO> get() {
        List<PersonneDTO> personnes = new ArrayList<>();
        Iterable<Personne> personnesDB = personneRepository.findAll();
        personnesDB.forEach(personneDB -> personnes.add(new PersonneDTO(personneDB)));
        return personnes;
    }

    @Transactional
    public PersonneDetailDTO getPersonne(long id) throws Exception {
        Optional<Personne> oPersonneDB = personneRepository.findById(id);
        if (oPersonneDB.isPresent()) {
            return new PersonneDetailDTO(oPersonneDB.get());
        }
        throw new Exception("Personne not found.");
    }

    @Transactional
    public PersonneDetailDTO createPersonne(String pseudo) {
        Personne personneDB = new Personne();
        personneDB.setPseudo(pseudo);
        personneRepository.save(personneDB);
        return new PersonneDetailDTO(personneDB);
    }

    @Transactional
    public PersonneDetailDTO update(Personne personne) throws Exception {
        if (personneRepository.findById(personne.getId()).isPresent()) {
            personneRepository.save(personne);
            return new PersonneDetailDTO(personne);
        }
        throw new Exception("Personne not found");
    }

    @Transactional
    public PersonneDetailDTO updatePartial(Personne personne) throws Exception {
        Optional<Personne> oPersonneDB = personneRepository.findById(personne.getId());
        if (oPersonneDB.isPresent()) {
            Personne personneDB = oPersonneDB.get();
            if (personne.getPseudo() != null) {
                personneDB.setPseudo(personne.getPseudo());
            }
            if (personne.getChats() != null) {
                personneDB.setChats(personne.getChats());
            }
            if (personne.getCommentaires() != null) {
                personneDB.setCommentaires(personne.getCommentaires());
            }
            personneRepository.save(personneDB);
            return new PersonneDetailDTO(personne);
        }
        throw new Exception("Personne not found");
    }

    @Transactional
    public void delete(long id) throws Exception {
        Optional<Personne> oPersonneDB = personneRepository.findById(id);
        if (oPersonneDB.isEmpty()) {
            throw new Exception("Personne not found");
        }
        personneRepository.delete(oPersonneDB.get());
    }
}
