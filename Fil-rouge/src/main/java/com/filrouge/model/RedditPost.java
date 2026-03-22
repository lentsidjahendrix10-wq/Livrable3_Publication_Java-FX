package com.filrouge.model;
import java.time.LocalDateTime;

public class RedditPost extends PublicationTexteSociale {
    private String subreddit;
    private int score;

    public RedditPost(int id, String auteur, LocalDateTime date, String contenu, String langue, String subreddit, int score) {
        super(id, auteur, date, contenu, langue);
        this.subreddit = subreddit;
        this.score = score;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Le subreddit doit commencer par "r/"
        return super.isValid() && subreddit != null && subreddit.startsWith("r/");
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nSubreddit: " + subreddit + "\nScore (Upvotes): " + score;
    }
    
    public String getSubreddit() { return subreddit; }
    public int getScore()        { return score;     }
    public RedditPost() { super(); }
    public void setSubreddit(String s) { this.subreddit = s; }
    public void setScore(int s)        { this.score = s;     }
}