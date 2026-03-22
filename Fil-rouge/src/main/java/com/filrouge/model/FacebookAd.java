package com.filrouge.model;
import java.time.LocalDateTime;

public class FacebookAd extends PublicationMediaSociale {
    private double budget;
    private String annonceur;

    public FacebookAd(int id, String auteur, LocalDateTime date, String url, int duree, double budget, String annonceur) {
        super(id, auteur, date, url, duree);
        this.budget = budget;
        this.annonceur = annonceur;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Le budget publicitaire doit être d'au moins 1.0
        return super.isValid() && budget >= 1.0 && annonceur != null;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nAnnonceur: " + annonceur + "\nBudget: " + budget + "€";
    }
    
    public double getBudget()     { return budget;     }
    public String getAnnonceur()  { return annonceur;  }
    public void setBudget(double b)    { this.budget = b;    }
    public void setAnnonceur(String a) { this.annonceur = a; }
}