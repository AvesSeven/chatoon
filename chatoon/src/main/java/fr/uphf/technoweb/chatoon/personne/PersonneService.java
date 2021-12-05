package fr.uphf.technoweb.chatoon.personne;

import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDetailDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PersonneService {

    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @Transactional
    public PersonneDetailDTO getPersonne(long id) throws Exception {
        Optional<Personne> oPersonne = personneRepository.findById(id);
        if (oPersonne.isPresent()) {
            return new PersonneDetailDTO(oPersonne.get());
        }
        throw new Exception("Personne not found.");
    }
}
