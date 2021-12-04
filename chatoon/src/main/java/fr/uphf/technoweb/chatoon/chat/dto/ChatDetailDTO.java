package fr.uphf.technoweb.chatoon.chat.dto;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentaireDTO;
import fr.uphf.technoweb.chatoon.personne.dto.PersonneDTO;
import fr.uphf.technoweb.chatoon.utils.CommentaireUtils;

import java.util.List;

public class ChatDetailDTO extends ChatDTO {
    private String description;
    private List<CommentaireDTO> commentaires;
    private PersonneDTO personne;

    public ChatDetailDTO() {
        super();
    }

    public ChatDetailDTO(Chat chat) {
        super(chat);
        this.description = chat.getDescriptionChat();
        this.commentaires = CommentaireUtils.commentaireToCommentaireDTO(chat.getCommentaireChat());
        this.personne = new PersonneDTO(chat.getPersonneChat());
    }

    public String getDescription() {
        return description;
    }

    public List<CommentaireDTO> getCommentaires() {
        return commentaires;
    }

    public PersonneDTO getPersonne() {
        return personne;
    }
}
