package fr.uphf.technoweb.chatoon.chat.resource;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.chat.bdd.ChatRepository;
import fr.uphf.technoweb.chatoon.chat.dto.ChatDTO;
import fr.uphf.technoweb.chatoon.chat.dto.ChatDetailDTO;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.commentaire.bdd.CommentaireRepository;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentaireDTO;
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
        return new ChatDetailDTO(chatRepository.save(chatInput));
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
    public Response creerCommentaire(@PathParam("idChat") Long idChat, CommentaireDTO commentaireDTO) {
        Commentaire commentaire = new Commentaire();
        commentaire.setCommentaire(commentaireDTO.getCommentaire());
        Optional<Chat> chat = chatRepository.findById(idChat);
        if (chat.isPresent()) {
            commentaire.setChatCommentaire(chat.get());
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        commentaire.setDateCommentaire(LocalDate.now());

        Optional<Personne> personne = personneRepository.findById(commentaireDTO.getPersonne().getId());
        if(personne.isPresent()){
            commentaire.setPersonneCommentaire(personne.get());
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(new CommentaireDTO(commentaireRepository.save(commentaire))).build();
    }

    @PUT
    @Path("{idChat}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Chat updateChat(@PathParam("idChat") long id, Chat chat) {
        chat.setIdChat(id);
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
}
