package fr.uphf.technoweb.chatoon.commentaire.resource;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.chat.bdd.ChatRepository;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.commentaire.bdd.CommentaireRepository;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentaireDTO;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Optional;

@Path("commentaires")
public class CommentaireResource {
    @Autowired
    private CommentaireRepository commentaireRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private ChatRepository chatRepository;

    @PUT
    @Path("{idCommentaire}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCommentaire(@PathParam("idCommentaire") long id, Commentaire commentaire) {
        Optional<Commentaire> optionalCommentaire = commentaireRepository.findById(id);
        Optional<Personne> optionalPersonne = personneRepository.findById(commentaire.getPersonne().getId());
        Optional<Chat> optionalChat = chatRepository.findById(commentaire.getChat().getId());

        if (optionalCommentaire.isPresent() && optionalPersonne.isPresent() && optionalChat.isPresent()) {
            commentaire.setId(id);
            commentaire.setDate(LocalDate.now());
            commentaire.setPersonne(optionalPersonne.get());
            commentaire.setChat(optionalChat.get());
            commentaireRepository.save(commentaire);
            return Response.ok(new CommentaireDTO(commentaire)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idCommentaire}")
    public Response updateCommentaire(@PathParam("idCommentaire") Long id, Commentaire commentaire) {
        Optional<Commentaire> optional = commentaireRepository.findById(id);

        if (optional.isPresent()) {
            Commentaire commentaireBDD = optional.get();
            if (commentaire.getMessage() != null) {
                commentaireBDD.setMessage(commentaire.getMessage());
            }

            commentaireRepository.save(commentaireBDD);
            return Response.ok(new CommentaireDTO(commentaireBDD)).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{idCommentaire}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCommentaire(@PathParam("idCommentaire") Long idCommentaire) {
        if (commentaireRepository.findById(idCommentaire).isPresent()) {
            commentaireRepository.deleteById(idCommentaire);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
