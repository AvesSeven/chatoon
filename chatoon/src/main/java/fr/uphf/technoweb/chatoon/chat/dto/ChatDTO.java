package fr.uphf.technoweb.chatoon.chat.dto;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;

public class ChatDTO {
    private Long id;
    private String nom;
    private String photo;

    public ChatDTO() {
        super();
    }

    public ChatDTO(Chat chat) {
        this.id = chat.getId();
        this.nom = chat.getNom();
        this.photo = chat.getPhoto();
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPhoto() {
        return photo;
    }
}
