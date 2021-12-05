package fr.uphf.technoweb.chatoon.personne.resource;

import fr.uphf.technoweb.chatoon.personne.PersonneService;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDTO;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDetailDTO;
import fr.uphf.technoweb.chatoon.utils.PersonneUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RestController
@Path("personnes")
public class PersonneResource {
    //@Autowired
    private PersonneRepository personneRepository;

    private PersonneService personneService;

    public PersonneResource(PersonneService personneService, PersonneRepository personneRepository) {
        this.personneService = personneService;
        this.personneRepository = personneRepository;
    }

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
        try {
            PersonneDetailDTO personne = this.personneService.getPersonne(id);
            return Response.ok(personne).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{idPersonne}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersonne(@PathParam("idPersonne") long id, Personne personne) {
        if (personneRepository.findById(id).isPresent()) {
            personne.setId(id);
            personneRepository.save(personne);
            return Response.ok(new PersonneDTO(personne)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idPersonne}")
    public Response updatePersonne(@PathParam("idPersonne") Long id, Personne personne) {
        Optional<Personne> optional = personneRepository.findById(id);

        if (optional.isPresent()) {
            Personne personneBDD = optional.get();
            if (personne.getPseudo() != null) {
                personneBDD.setPseudo(personne.getPseudo());
            }

            if (personne.getChats() != null) {
                personneBDD.setChats(personne.getChats());
            }

            if (personne.getCommentaires() != null) {
                personneBDD.setCommentaires(personne.getCommentaires());
            }

            personneRepository.save(personneBDD);
            return Response.ok(new PersonneDetailDTO(personneBDD)).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{idPersonne}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePersonne(@PathParam("idPersonne") Long idPersonne) {
        if (personneRepository.findById(idPersonne).isPresent()) {
            personneRepository.deleteById(idPersonne);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
