package com.filrouge.model;

import java.time.LocalDateTime;

public abstract class PublicationMediaSociale extends Publication {
    private String urlMedia;
    private int dureeSecondes;

    public PublicationMediaSociale(int id, String auteur, LocalDateTime date, String url, int duree) {
        super(id, auteur, date);
        this.urlMedia = url;
        this.dureeSecondes = duree;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 2 : L'URL doit commencer par http et la durée doit être positive
        return super.isValid() && urlMedia != null && urlMedia.startsWith("http") && dureeSecondes > 0;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nURL: " + urlMedia + "\nDurée: " + dureeSecondes + "s";
    }
    
    public int getDureeSecondes() {
        return dureeSecondes;
    }
    
    public PublicationMediaSociale() {
        super();
    }
    
    public String getUrlMedia() { return urlMedia; }
    public void setUrlMedia(String urlMedia) { this.urlMedia = urlMedia; }
    public void setDureeSecondes(int dureeSecondes) { this.dureeSecondes = dureeSecondes; }
}