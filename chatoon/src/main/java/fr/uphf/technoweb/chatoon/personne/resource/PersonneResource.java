package fr.uphf.technoweb.chatoon.personne.resource;

import fr.uphf.technoweb.chatoon.personne.service.PersonneService;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@Path("personnes")
public class PersonneResource {

    private final PersonneService personneService;

    public PersonneResource(PersonneService personneService) {
        this.personneService = personneService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerPersonne(Personne personne) {
        if (personne.getPseudo() != null) {
            return Response.ok(personneService.createPersonne(personne.getPseudo())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listerPersonne() {
        return Response.ok(personneService.get()).build();
    }

    @GET
    @Path("{idPersonne}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonneById(@PathParam("idPersonne") Long id) {
        try {
            return Response.ok(personneService.getPersonne(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{idPersonne}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersonne(@PathParam("idPersonne") long id, Personne personne) {
        personne.setId(id);
        if (personne.getPseudo() != null) {
            try {
                return Response.ok(personneService.update(personne)).build();
            } catch (Exception e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idPersonne}")
    public Response updatePersonne(@PathParam("idPersonne") Long id, Personne personne) {
        personne.setId(id);
        try {
            return Response.ok(personneService.updatePartial(personne)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{idPersonne}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePersonne(@PathParam("idPersonne") Long id) {
        try {
            personneService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
