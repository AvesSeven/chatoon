package fr.uphf.technoweb.chatoon.personne.dto;

import fr.uphf.technoweb.chatoon.chat.dto.ChatDTO;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentairePersonneDTO;
import fr.uphf.technoweb.chatoon.personne.bdd.Personne;
import fr.uphf.technoweb.chatoon.utils.ChatUtils;
import fr.uphf.technoweb.chatoon.utils.CommentairePersonneUtils;

import java.util.List;

public class PersonneDetailDTO extends PersonneDTO {
    private List<ChatDTO> chats;
    private List<CommentairePersonneDTO> commentaires;

    public PersonneDetailDTO() {
        super();
    }

    public PersonneDetailDTO(Personne personne) {
        super(personne);
        this.chats = ChatUtils.chatToChatDTO(personne.getChats());
        this.commentaires = CommentairePersonneUtils.commentaireToCommentairePersonneDTO(personne.getCommentaires());
    }

    public List<ChatDTO> getChats() {
        return chats;
    }

    public void setChats(List<ChatDTO> chats) {
        this.chats = chats;
    }

    public List<CommentairePersonneDTO> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<CommentairePersonneDTO> commentaires) {
        this.commentaires = commentaires;
    }
}
