package com.filrouge.model;

import java.time.LocalDateTime;

public abstract class PublicationTexteSociale extends Publication {
    private String contenu;
    private String langue;

    public PublicationTexteSociale(int id, String auteur, LocalDateTime date, String contenu, String langue) {
        super(id, auteur, date);
        this.contenu = contenu;
        this.langue = langue;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 2 : Le texte doit faire au moins 2 caractères
        return super.isValid() && contenu != null && contenu.length() >= 2;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nContenu: " + contenu + "\nLangue: " + langue;
    }
    
    public PublicationTexteSociale() {
        super();
    }
    
    public String getContenu() { return contenu; }
    public String getLangue()  { return langue;  }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public void setLangue(String langue) { this.langue = langue; }
}