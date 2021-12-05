package fr.uphf.technoweb.chatoon.chat.resource;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.chat.bdd.ChatRepository;
import fr.uphf.technoweb.chatoon.chat.dto.ChatDTO;
import fr.uphf.technoweb.chatoon.chat.dto.ChatDetailDTO;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.commentaire.bdd.CommentaireRepository;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentaireChatDTO;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import fr.uphf.technoweb.chatoon.utils.ChatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Path("chats")
public class ChatResource {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;
    @Autowired
    private PersonneRepository personneRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ChatDetailDTO creerChat(Chat chatInput) {
        chatRepository.save(chatInput);
        return new ChatDetailDTO(chatInput);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ChatDTO> listerChat() {
        Iterable<Chat> chats = chatRepository.findAll();
        return ChatUtils.chatToChatDTO(chats);
    }

    @GET
    @Path("{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatById(@PathParam("idChat") Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isPresent()) {
            ChatDetailDTO chatDetailDTO = new ChatDetailDTO(chat.get());
            return Response.ok(chatDetailDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("{idChat}/commentaires")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerCommentaire(@PathParam("idChat") Long idChat, CommentaireChatDTO commentaireChatDTO) {
        Optional<Chat> chat = chatRepository.findById(idChat);
        Optional<Personne> personne = personneRepository.findById(commentaireChatDTO.getPersonne().getId());

        if (chat.isPresent() && personne.isPresent()) {
            Commentaire commentaire = new Commentaire();
            commentaire.setMessage(commentaireChatDTO.getMessage());
            commentaire.setChat(chat.get());
            commentaire.setPersonne(personne.get());
            commentaire.setDate(LocalDate.now());

            commentaireRepository.save(commentaire);

            return Response.ok(new CommentaireChatDTO(commentaire)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{idChat}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateChat(@PathParam("idChat") long id, Chat chat) {
        if (chatRepository.findById(id).isPresent() && personneRepository.findById(chat.getPersonne().getId()).isPresent()) {
            chat.setId(id);
            chatRepository.save(chat);
            return Response.ok(new ChatDTO(chat)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idChat}")
    public Response updateChat(@PathParam("idChat") Long id, Chat chat) {
        Optional<Chat> optional = chatRepository.findById(id);

        if (optional.isPresent()) {
            Chat chatBDD = optional.get();
            if (chat.getNom() != null) {
                chatBDD.setNom(chat.getNom());
            }

            if (chat.getDescription() != null) {
                chatBDD.setDescription(chat.getDescription());
            }

            if (chat.getPhoto() != null) {
                chatBDD.setPhoto(chat.getPhoto());
            }

            if (chat.getPersonne() != null) {
                chatBDD.setPersonne(chat.getPersonne());
            }

            chatRepository.save(chatBDD);
            return Response.ok(new ChatDetailDTO(chatBDD)).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteChat(@PathParam("idChat") Long id) {
        if (chatRepository.findById(id).isPresent()) {
            chatRepository.deleteById(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
