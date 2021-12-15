package fr.uphf.technoweb.chatoon.commentaire.resource;

import fr.uphf.technoweb.chatoon.commentaire.service.CommentaireService;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.exceptions.ChatException;
import fr.uphf.technoweb.chatoon.exceptions.CommentaireException;
import fr.uphf.technoweb.chatoon.exceptions.PersonneException;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@Path("commentaires")
public class CommentaireResource {

    private final CommentaireService commentaireService;

    public CommentaireResource(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @PUT
    @Path("{idCommentaire}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCommentaire(@PathParam("idCommentaire") long id, Commentaire commentaire) {
        commentaire.setId(id);
        if (commentaire.getMessage() != null && commentaire.getChat() != null && commentaire.getPersonne() != null) {
            try {
                return Response.ok(commentaireService.update(commentaire)).build();
            } catch (PersonneException | CommentaireException | ChatException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idCommentaire}")
    public Response updateCommentaire(@PathParam("idCommentaire") Long id, Commentaire commentaire) {
        commentaire.setId(id);
        try {
            return Response.ok(commentaireService.updatePartial(commentaire)).build();
        } catch (PersonneException | CommentaireException | ChatException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{idCommentaire}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCommentaire(@PathParam("idCommentaire") Long id) {
        try {
            commentaireService.delete(id);
            return Response.noContent().build();
        } catch (CommentaireException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
