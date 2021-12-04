package fr.uphf.technoweb.chatoon.chat.dto;

import fr.uphf.technoweb.chatoon.chat.bdd.Chat;

import java.io.Serializable;

public class ChatDTO {
    private Long id;
    private String nom;
    private String photo;

    public ChatDTO() {
        super();
    }

    public ChatDTO(Chat chat) {
        this.id = chat.getIdChat();
        this.nom = chat.getNomChat();
        this.photo = chat.getPhotoChat();
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
