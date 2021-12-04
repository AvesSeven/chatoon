package fr.uphf.technoweb.chatoon.personne.resource;

import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDTO;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDetailDTO;
import fr.uphf.technoweb.chatoon.utils.PersonneUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("personnes")
public class PersonneResource {
    @Autowired
    private PersonneRepository personneRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonneDetailDTO creerPersonne(Personne personne) {
        return new PersonneDetailDTO(personneRepository.save(personne));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonneDTO> listerPersonne() {
        Iterable<Personne> personnes = personneRepository.findAll();
        return PersonneUtils.personneToPersonneDTO(personnes);
    }

    @GET
    @Path("{idPersonne}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonneById(@PathParam("idPersonne") Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        if (personne.isPresent()) {
            PersonneDetailDTO personneDetailDTO = new PersonneDetailDTO(personne.get());
            return Response.ok(personneDetailDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
