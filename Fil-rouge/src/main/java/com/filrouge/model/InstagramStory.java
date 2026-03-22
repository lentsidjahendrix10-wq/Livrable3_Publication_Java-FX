package com.filrouge.model;
import java.time.LocalDateTime;

public class InstagramStory extends PublicationMediaSociale {
    private int expireDansHeures;
    private String musique;

    public InstagramStory(int id, String auteur, LocalDateTime date, String url, int duree, int expire, String musique) {
        super(id, auteur, date, url, duree);
        this.expireDansHeures = expire;
        this.musique = musique;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Une story expire en max 24h
        return super.isValid() && expireDansHeures > 0 && expireDansHeures <= 24;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nExpire dans: " + expireDansHeures + "h\nMusique: " + musique;
    }
    
    public int getExpireDansHeures() { return expireDansHeures; }
    public String getMusique()       { return musique;          }
    public InstagramStory() { super(); }
    public void setExpireDansHeures(int e) { this.expireDansHeures = e; }
    public void setMusique(String m)       { this.musique = m;          }
}