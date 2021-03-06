package fr.uphf.technoweb.chatoon.chat.resource;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.chat.service.ChatService;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.exceptions.ChatException;
import fr.uphf.technoweb.chatoon.exceptions.CommentaireException;
import fr.uphf.technoweb.chatoon.exceptions.PersonneException;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@Path("chats")
public class ChatResource {

    private final ChatService chatService;

    public ChatResource(ChatService chatService) {
        this.chatService = chatService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerChat(Chat chat) {
        if (chat.getNom() != null) {
            try {
                return Response.ok(chatService.createChat(chat)).build();
            } catch (PersonneException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listerChat() {
        return Response.ok(chatService.get()).build();
    }

    @GET
    @Path("{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatById(@PathParam("idChat") Long id) {
        try {
            return Response.ok(chatService.getChat(id)).build();
        } catch (ChatException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("{idChat}/commentaires")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerCommentaire(@PathParam("idChat") Long idChat, Commentaire commentaire) {
        if (commentaire.getMessage() != null && commentaire.getPersonne() != null) {
            try {
                return Response.ok(chatService.createCommentaire(idChat, commentaire)).build();
            } catch (ChatException | PersonneException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{idChat}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateChat(@PathParam("idChat") long id, Chat chat) {
        chat.setId(id);
        if (chat.getNom() != null && chat.getPersonne() != null) {
            try {
                return Response.ok(chatService.update(chat)).build();
            } catch (ChatException | PersonneException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idChat}")
    public Response updateChat(@PathParam("idChat") Long id, Chat chat) {
        chat.setId(id);
        try {
            return Response.ok(chatService.updatePartial(chat)).build();
        } catch (ChatException | PersonneException | CommentaireException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteChat(@PathParam("idChat") Long id) {
        try {
            chatService.delete(id);
            return Response.noContent().build();
        } catch (ChatException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
