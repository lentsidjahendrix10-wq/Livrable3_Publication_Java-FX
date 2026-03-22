package com.filrouge.model;
import java.time.LocalDateTime;

public class YouTubeVideo extends PublicationMediaSociale {
    private String qualite; // ex: "1080p", "4K"
    private boolean monetisee;

    public YouTubeVideo(int id, String auteur, LocalDateTime date, String url, int duree, String qualite, boolean monetisee) {
        super(id, auteur, date, url, duree);
        this.qualite = qualite;
        this.monetisee = monetisee;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Une vidéo YouTube doit durer au moins 30 secondes
        return super.isValid() && getDureeSecondes() >= 30;
    }
    
    public YouTubeVideo() {
        super(); // Appelle le constructeur vide de Publication
    }
    
    public String getQualite()    { return qualite;    }
    public boolean isMonetisee()  { return monetisee;  }
        }
    
    // Pour corriger proprement, assure-toi d'ajouter un getter public int getDureeSecondes() dans PublicationMediaSociale.