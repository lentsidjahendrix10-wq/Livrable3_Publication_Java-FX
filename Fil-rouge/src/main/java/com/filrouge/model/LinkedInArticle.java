package com.filrouge.model;
import java.time.LocalDateTime;

public class LinkedInArticle extends PublicationTexteSociale {
    private String titre;
    private int tempsLecture; // en minutes

    public LinkedInArticle(int id, String auteur, LocalDateTime date, String contenu, String langue, String titre, int temps) {
        super(id, auteur, date, contenu, langue);
        this.titre = titre;
        this.tempsLecture = temps;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Un article doit avoir un titre d'au moins 5 caractères
        return super.isValid() && titre != null && titre.length() >= 5;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nTitre: " + titre + "\nTemps de lecture estimé: " + tempsLecture + " min";
    }
    
    public String getTitre()      { return titre;        }
    public int getTempsLecture()  { return tempsLecture; }
    public LinkedInArticle() { super(); }
    public void setTitre(String t)       { this.titre = t;        }
    public void setTempsLecture(int tps) { this.tempsLecture = tps; }
}