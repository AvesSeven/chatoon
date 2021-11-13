package fr.uphf.technoweb.chatoon.commentaire.resource;

import fr.uphf.technoweb.chatoon.chat.bdd.ChatRepository;
import fr.uphf.technoweb.chatoon.commentaire.bdd.CommentaireRepository;
import fr.uphf.technoweb.chatoon.personne.bdd.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentaireResource {
    @Autowired
    private CommentaireRepository commentaireRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private PersonneRepository personneRepository;


}
