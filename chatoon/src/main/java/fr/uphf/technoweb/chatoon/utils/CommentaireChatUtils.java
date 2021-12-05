package fr.uphf.technoweb.chatoon.utils;

import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentaireChatDTO;

import java.util.ArrayList;
import java.util.List;

public class CommentaireChatUtils {
    public static List<CommentaireChatDTO> commentairesToCommentairesChatDTO(Iterable<Commentaire> commentaires) {
        List<CommentaireChatDTO> commentairesTemp = new ArrayList<>();

        commentaires.forEach((commentaire) -> {
            commentairesTemp.add(new CommentaireChatDTO(commentaire));
        });

        return commentairesTemp;
    }
}
