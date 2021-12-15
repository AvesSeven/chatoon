package fr.uphf.technoweb.chatoon.chat.service;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.chat.bdd.ChatRepository;
import fr.uphf.technoweb.chatoon.chat.dto.ChatDTO;
import fr.uphf.technoweb.chatoon.chat.dto.ChatDetailDTO;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.commentaire.bdd.CommentaireRepository;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentaireChatDTO;
import fr.uphf.technoweb.chatoon.exceptions.ChatException;
import fr.uphf.technoweb.chatoon.exceptions.CommentaireException;
import fr.uphf.technoweb.chatoon.exceptions.PersonneException;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final CommentaireRepository commentaireRepository;
    private final PersonneRepository personneRepository;

    public ChatService(ChatRepository chatRepository, CommentaireRepository commentaireRepository, PersonneRepository personneRepository) {
        this.commentaireRepository = commentaireRepository;
        this.chatRepository = chatRepository;
        this.personneRepository = personneRepository;
    }

    @Transactional
    public ChatDetailDTO createChat(Chat chat) throws PersonneException {
        Optional<Personne> personne = personneRepository.findById(chat.getPersonne().getId());

        if (personne.isPresent()) {
            chat.setPersonne(personne.get());
            chatRepository.save(chat);
            return new ChatDetailDTO(chat);
        }
        throw new PersonneException("Personne not found");
    }

    @Transactional
    public List<ChatDTO> get() {
        List<ChatDTO> chats = new ArrayList<>();
        Iterable<Chat> chatsDB = chatRepository.findAll();
        chatsDB.forEach(chatDB -> chats.add(new ChatDTO(chatDB)));
        return chats;
    }

    @Transactional
    public ChatDetailDTO getChat(long id) throws ChatException {
        Optional<Chat> oChatDB = chatRepository.findById(id);
        if (oChatDB.isPresent()) {
            return new ChatDetailDTO(oChatDB.get());
        }
        throw new ChatException("Chat not found.");
    }

    @Transactional
    public CommentaireChatDTO createCommentaire(Long idChat, Commentaire commentaire) throws ChatException, PersonneException {
        Optional<Chat> optionalChat = chatRepository.findById(idChat);
        Optional<Personne> optionalPersonne = personneRepository.findById(commentaire.getPersonne().getId());

        if(optionalChat.isEmpty()) {
            throw new ChatException("Chat not found");
        }

        if(optionalPersonne.isEmpty()) {
            throw new PersonneException("Personne not found");
        }

        commentaire.setChat(optionalChat.get());
        commentaire.setPersonne(optionalPersonne.get());
        commentaire.setDate(LocalDate.now());

        commentaireRepository.save(commentaire);
        return new CommentaireChatDTO(commentaire);
    }

    @Transactional
    public ChatDetailDTO update(Chat chat) throws ChatException, PersonneException {
        Optional<Chat> optionalChat = chatRepository.findById(chat.getId());
        Optional<Personne> optionalPersonne = personneRepository.findById(chat.getPersonne().getId());

        if(optionalChat.isEmpty()) {
            throw new ChatException("Chat not found");
        }

        if(optionalPersonne.isEmpty()) {
            throw new PersonneException("Personne not found");
        }

        chat.setPersonne(optionalPersonne.get());
        chatRepository.save(chat);
        return new ChatDetailDTO(chat);
    }

    @Transactional
    public ChatDetailDTO updatePartial(Chat chat) throws ChatException, PersonneException, CommentaireException {
        Optional<Chat> oChatDB = chatRepository.findById(chat.getId());

        if (oChatDB.isPresent()) {
            Chat chatDB = oChatDB.get();

            if (chat.getNom() != null) {
                chatDB.setNom(chat.getNom());
            }

            if (chat.getPersonne() != null) {
                Optional<Personne> oPersonneDB = personneRepository.findById(chatDB.getPersonne().getId());
                if (oPersonneDB.isPresent()) {
                    chatDB.setPersonne(oPersonneDB.get());
                } else {
                    throw new PersonneException("Personne not found");
                }
            }

            if (chat.getCommentaires() != null) {
                List<Commentaire> commentaires = new ArrayList<>();
                for (Commentaire commentaire : chat.getCommentaires()) {
                    Optional<Commentaire> oCommentaireDB = commentaireRepository.findById(commentaire.getId());
                    if (oCommentaireDB.isPresent()) {
                        commentaires.add(oCommentaireDB.get());
                    } else {
                        throw new CommentaireException("Commentaire not found");
                    }
                }
                chatDB.setCommentaires(commentaires);
            }

            if (chat.getDescription() != null) {
                chatDB.setDescription(chat.getDescription());
            }

            if (chat.getPhoto() != null) {
                chatDB.setPhoto(chat.getPhoto());
            }

            chatRepository.save(chatDB);
            return new ChatDetailDTO(chatDB);
        }

        throw new ChatException("Chat not found");
    }

    @Transactional
    public void delete(long id) throws ChatException {
        Optional<Chat> oChatDB = chatRepository.findById(id);
        if (oChatDB.isEmpty()) {
            throw new ChatException("Chat not found");
        }
        chatRepository.delete(oChatDB.get());
    }
}
