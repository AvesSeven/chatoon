package fr.uphf.technoweb.chatoon.commentaire.service;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.chat.bdd.ChatRepository;
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
import java.util.Optional;

@Service
public class CommentaireService {
    private final CommentaireRepository commentaireRepository;
    private final PersonneRepository personneRepository;
    private final ChatRepository chatRepository;

    public CommentaireService(CommentaireRepository commentaireRepository, PersonneRepository personneRepository, ChatRepository chatRepository) {
        this.commentaireRepository = commentaireRepository;
        this.chatRepository = chatRepository;
        this.personneRepository = personneRepository;
    }

    @Transactional
    public CommentaireChatDTO update(Commentaire commentaire) throws PersonneException, CommentaireException, ChatException {
        Optional<Commentaire> optionalCommentaire = commentaireRepository.findById(commentaire.getId());
        Optional<Personne> optionalPersonne = personneRepository.findById(commentaire.getPersonne().getId());
        Optional<Chat> optionalChat = chatRepository.findById(commentaire.getChat().getId());

        if(optionalCommentaire.isEmpty()) {
            throw new CommentaireException("Commentaire not found");
        }

        if(optionalPersonne.isEmpty()) {
            throw new PersonneException("Personne not found");
        }

        if(optionalChat.isEmpty()) {
            throw new ChatException("Chat not found");
        }

        commentaire.setDate(LocalDate.now());
        commentaire.setPersonne(optionalPersonne.get());
        commentaire.setChat(optionalChat.get());
        commentaireRepository.save(commentaire);
        return new CommentaireChatDTO(commentaire);
    }

    @Transactional
    public CommentaireChatDTO updatePartial(Commentaire commentaire) throws PersonneException, CommentaireException, ChatException {
        Optional<Commentaire> oCommentaireDB = commentaireRepository.findById(commentaire.getId());

        if (oCommentaireDB.isPresent()) {
            Commentaire commentaireDB = oCommentaireDB.get();

            if (commentaire.getMessage() != null) {
                commentaireDB.setMessage(commentaire.getMessage());
            }

            if(commentaire.getPersonne() != null) {
                Optional<Personne> oPersonneDB = personneRepository.findById(commentaire.getPersonne().getId());
                oPersonneDB.ifPresent(commentaireDB::setPersonne);
            }
            else {
                throw new PersonneException("Personne not found");
            }

            if(commentaire.getChat() != null) {
                Optional<Chat> oChatDB = chatRepository.findById(commentaire.getChat().getId());
                if(oChatDB.isPresent()) {
                    oChatDB.ifPresent(commentaireDB::setChat);
                }
                else {
                    throw new ChatException("Chat not found");
                }
            }

            commentaireRepository.save(commentaireDB);
            return new CommentaireChatDTO(commentaireDB);
        }

        throw new CommentaireException("Commentaire not found");
    }

    @Transactional
    public void delete(long id) throws CommentaireException {
        Optional<Commentaire> oCommentaireDB = commentaireRepository.findById(id);
        if (oCommentaireDB.isEmpty()) {
            throw new CommentaireException("Commentaire not found");
        }
        commentaireRepository.delete(oCommentaireDB.get());
    }
}
