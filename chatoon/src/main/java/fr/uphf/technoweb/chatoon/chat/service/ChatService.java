package fr.uphf.technoweb.chatoon.chat.service;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.chat.bdd.ChatRepository;
import fr.uphf.technoweb.chatoon.chat.dto.ChatDTO;
import fr.uphf.technoweb.chatoon.chat.dto.ChatDetailDTO;
import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.commentaire.bdd.CommentaireRepository;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentaireChatDTO;
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
    public ChatDetailDTO createChat(Chat chat) throws Exception {
        Optional<Personne> personne = personneRepository.findById(chat.getPersonne().getId());

        if (personne.isPresent()) {
            chat.setPersonne(personne.get());
            chatRepository.save(chat);
            return new ChatDetailDTO(chat);
        }
        throw new Exception("Personne not found");
    }

    @Transactional
    public List<ChatDTO> get() {
        List<ChatDTO> chats = new ArrayList<>();
        Iterable<Chat> chatsDB = chatRepository.findAll();
        chatsDB.forEach(chatDB -> chats.add(new ChatDTO(chatDB)));
        return chats;
    }

    @Transactional
    public ChatDetailDTO getChat(long id) throws Exception {
        Optional<Chat> oChatDB = chatRepository.findById(id);
        if (oChatDB.isPresent()) {
            return new ChatDetailDTO(oChatDB.get());
        }
        throw new Exception("Chat not found.");
    }

    @Transactional
    public CommentaireChatDTO createCommentaire(Long idChat, CommentaireChatDTO commentaireChatDTO) throws Exception {
        Optional<Chat> chat = chatRepository.findById(idChat);
        Optional<Personne> personne = personneRepository.findById(commentaireChatDTO.getPersonne().getId());

        if (chat.isPresent() && personne.isPresent()) {
            Commentaire commentaire = new Commentaire();
            commentaire.setMessage(commentaireChatDTO.getMessage());
            commentaire.setChat(chat.get());
            commentaire.setPersonne(personne.get());
            commentaire.setDate(LocalDate.now());

            commentaireRepository.save(commentaire);
            return new CommentaireChatDTO(commentaire);
        }
        throw new Exception("Commentaire not found");
    }

    @Transactional
    public ChatDetailDTO update(Chat chat) throws Exception {
        Optional<Chat> optionalChat = chatRepository.findById(chat.getId());
        Optional<Personne> optionalPersonne = personneRepository.findById(chat.getPersonne().getId());

        if (optionalChat.isPresent() && optionalPersonne.isPresent()) {
            chat.setPersonne(optionalPersonne.get());
            chatRepository.save(chat);
            return new ChatDetailDTO(chat);
        }
        throw new Exception("Chat not found");
    }

    @Transactional
    public ChatDetailDTO updatePartial(Chat chat) throws Exception {
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
                    throw new Exception("Personne not found");
                }
            }

            if (chat.getCommentaires() != null) {
                List<Commentaire> commentaires = new ArrayList<>();
                for (Commentaire commentaire : chat.getCommentaires()) {
                    Optional<Commentaire> oCommentaireDB = commentaireRepository.findById(commentaire.getId());
                    if (oCommentaireDB.isPresent()) {
                        commentaires.add(oCommentaireDB.get());
                    } else {
                        throw new Exception("Commentaire not found");
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

        throw new Exception("Chat not found");
    }

    @Transactional
    public void delete(long id) throws Exception {
        Optional<Chat> oChatDB = chatRepository.findById(id);
        if (oChatDB.isEmpty()) {
            throw new Exception("Chat not found");
        }
        chatRepository.delete(oChatDB.get());
    }
}
