package fr.uphf.technoweb.chatoon.utils;

import fr.uphf.technoweb.chatoon.commentaire.bdd.Commentaire;
import fr.uphf.technoweb.chatoon.commentaire.dto.CommentairePersonneDTO;

import java.util.ArrayList;
import java.util.List;

public class CommentairePersonneUtils {
    public static List<CommentairePersonneDTO> commentairePersonneToCommentairePersonneDTO(Iterable<Commentaire> commentaires) {
        List<CommentairePersonneDTO> commentairesTemp = new ArrayList<>();

        commentaires.forEach((commentaire) -> {
            commentairesTemp.add(new CommentairePersonneDTO(commentaire));
        });

        return commentairesTemp;
    }
}
