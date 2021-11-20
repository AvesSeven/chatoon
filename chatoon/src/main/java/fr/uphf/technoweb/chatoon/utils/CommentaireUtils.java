package fr.uphf.technoweb.chatoon.utils;

import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentaireDTO;

import java.util.ArrayList;
import java.util.List;

public class CommentaireUtils {
    public static List<CommentaireDTO> commentaireToCommentaireDTO(Iterable<Commentaire> commentaires) {
        List<CommentaireDTO> commentairesTemp = new ArrayList<>();

        commentaires.forEach((commentaire) -> {
            commentairesTemp.add(new CommentaireDTO(commentaire));
        });

        return commentairesTemp;
    }
}
