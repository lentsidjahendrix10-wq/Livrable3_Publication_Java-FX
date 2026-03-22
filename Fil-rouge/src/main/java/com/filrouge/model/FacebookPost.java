package com.filrouge.model;
import java.time.LocalDateTime;

public class FacebookPost extends PublicationTexteSociale {
    private int nombreLikes;
    private boolean partageAutorise;

    public FacebookPost(int id, String auteur, LocalDateTime date, String contenu, String langue, int likes, boolean partage) {
        super(id, auteur, date, contenu, langue);
        this.nombreLikes = likes;
        this.partageAutorise = partage;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Les likes ne peuvent pas être négatifs
        return super.isValid() && nombreLikes >= 0;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nLikes: " + nombreLikes + "\nPartageable: " + partageAutorise;
    }
    
    public int getNombreLikes()      { return nombreLikes;     }
    public boolean isPartageAutorise() { return partageAutorise; }
    public FacebookPost() { super(); }
    public void setNombreLikes(int n)         { this.nombreLikes = n;       }
    public void setPartageAutorise(boolean p) { this.partageAutorise = p;   }
}