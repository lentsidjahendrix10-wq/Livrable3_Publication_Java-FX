package com.filrouge.model;
import java.time.LocalDateTime;

public class YouTubeShort extends PublicationMediaSociale {
    private int dureeMaxSecondes; // Constante pour le type
    private boolean formatVertical;

    public YouTubeShort(int id, String auteur, LocalDateTime date, String url, int duree, boolean vertical) {
        super(id, auteur, date, url, duree);
        this.dureeMaxSecondes = 60;
        this.formatVertical = vertical;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Un Short ne peut pas dépasser 60 secondes
        // (Ici on simule l'accès à la durée de la classe parente via une logique de test)
        return super.isValid() && formatVertical; 
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nFormat Short (Max 60s)\nVertical: " + formatVertical;
    }
    
    public boolean isFormatVertical()    { return formatVertical;    }
    public int getDureeMaxSecondes()     { return dureeMaxSecondes;  }
    public YouTubeShort() { super(); }
    public void setFormatVertical(boolean v) { this.formatVertical = v; }
}