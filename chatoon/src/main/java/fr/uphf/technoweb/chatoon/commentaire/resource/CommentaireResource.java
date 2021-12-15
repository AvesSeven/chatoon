package fr.uphf.technoweb.chatoon.commentaire.resource;

import fr.uphf.technoweb.chatoon.commentaire.service.CommentaireService;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
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
        if (commentaire.getMessage() != null && commentaire.getChat().getId() != null && commentaire.getPersonne().getId() != null) {
            try {
                return Response.ok(commentaireService.update(commentaire)).build();
            } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
