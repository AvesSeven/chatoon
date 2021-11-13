package fr.uphf.technoweb.chatoon.chat.resource;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.chat.bdd.ChatRepository;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("chats")
public class ChatResource {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private PersonneRepository personneRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Chat creerChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Chat> listerChat() {
        List<Chat> chats = new ArrayList<>();
        chatRepository.findAll().forEach(chats::add);
        return chats;
    }

    @GET
    @Path("{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatById(@PathParam("idChat") Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isPresent()) {
            return Response.ok(chat.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{idChat}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Chat updateChat(@PathParam("idChat") long id, Chat chat) {
        chat.setIdChat((int)id);
        return chatRepository.save(chat);
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idChat}")
    public Response updateNom(@PathParam("idChat") Long id, Chat chat) {
        String description = chat.getDescriptionChat();

        Optional<Chat> optional = chatRepository.findById(id);

        if (optional.isPresent()) {
            Chat chatBDD = optional.get();
            chatBDD.setDescriptionChat(description);
            chatRepository.save(chatBDD);
            return Response.ok(chatBDD).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteChat(@PathParam("idChat") Long id) {
        if (chatRepository.findById(id).isPresent()) {
            chatRepository.deleteById(id);
        }
        return Response.noContent().build();
    }

//    @GET
//    @Path("{idChat}/commentaires")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Commentaire> listerCommentaire(@PathParam("idChat") Long id) {
//        return chatRepository.findById(id).get().getCommentaireChat();
//    }

    @GET
    @Path("{idChat}/personne")
    @Produces(MediaType.APPLICATION_JSON)
    public Personne afficherPersonne(@PathParam("idChat") Long id) {
        return chatRepository.findById(id).get().getPersonneChat();
    }
}
