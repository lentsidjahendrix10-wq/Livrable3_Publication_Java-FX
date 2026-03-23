package com.filrouge.model;
import java.time.LocalDateTime;

public class YouTubeVideo extends PublicationMediaSociale {
    private String qualite;
    private boolean monetisee;

    public YouTubeVideo(int id, String auteur, LocalDateTime date, String url, int duree, String qualite, boolean monetisee) {
        super(id, auteur, date, url, duree);
        this.qualite = qualite;
        this.monetisee = monetisee;
    }

    public YouTubeVideo() { super(); }

    @Override
    public boolean isValid() {
        return super.isValid() && getDureeSecondes() >= 30;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nQualité: " + qualite + "\nMonétisée: " + monetisee;
    }

    public String getQualite()          { return qualite;    }
    public boolean isMonetisee()        { return monetisee;  }
    public void setQualite(String q)    { this.qualite = q;  }
    public void setMonetisee(boolean m) { this.monetisee = m; }
}