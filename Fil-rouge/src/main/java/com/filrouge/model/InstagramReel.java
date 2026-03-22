package com.filrouge.model;
import java.time.LocalDateTime;

public class InstagramReel extends PublicationMediaSociale {
    private boolean orientationVerticale;
    private int nombreVues;

    public InstagramReel(int id, String auteur, LocalDateTime date, String url, int duree, boolean vertical, int vues) {
        super(id, auteur, date, url, duree);
        this.orientationVerticale = vertical;
        this.nombreVues = vues;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Un Reel doit impérativement être vertical
        return super.isValid() && orientationVerticale == true;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nVertical: " + orientationVerticale + "\nVues: " + nombreVues;
    }
    
    public boolean isOrientationVerticale() { return orientationVerticale; }
    public int getNombreVues()              { return nombreVues;           }
    public InstagramReel() { super(); }
    public void setOrientationVerticale(boolean v) { this.orientationVerticale = v; }
    public void setNombreVues(int n)               { this.nombreVues = n;           }
}