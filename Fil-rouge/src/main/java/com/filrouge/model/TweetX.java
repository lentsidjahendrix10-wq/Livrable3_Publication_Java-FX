package com.filrouge.model;
import java.time.LocalDateTime;

public class TweetX extends PublicationTexteSociale {
    private int nombreHashtags;
    private int nombreRetweets;

    public TweetX(int id, String auteur, LocalDateTime date, String contenu, String langue, int hashtags, int retweets) {
        super(id, auteur, date, contenu, langue);
        this.nombreHashtags = hashtags;
        this.nombreRetweets = retweets;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Pas plus de 10 hashtags (politique anti-spam)
        return super.isValid() && nombreHashtags >= 0 && nombreHashtags <= 10;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nHashtags: " + nombreHashtags + "\nRetweets: " + nombreRetweets;
    }
    
    public int getNombreHashtags()  { return nombreHashtags;  }
    public int getNombreRetweets()  { return nombreRetweets;  }
    public TweetX() { super(); }
    public void setNombreHashtags(int n)  { this.nombreHashtags = n;  }
    public void setNombreRetweets(int n)  { this.nombreRetweets = n;  }
}