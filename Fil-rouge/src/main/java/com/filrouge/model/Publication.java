package com.filrouge.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = YouTubeVideo.class, name = "YOUTUBE_VIDEO"),
    @JsonSubTypes.Type(value = YouTubeShort.class, name = "YOUTUBE_SHORT"),
    @JsonSubTypes.Type(value = InstagramReel.class, name = "INSTAGRAM_REEL"),
    @JsonSubTypes.Type(value = InstagramStory.class, name = "INSTAGRAM_STORY"),
    @JsonSubTypes.Type(value = FacebookAd.class, name = "FACEBOOK_AD"),
    @JsonSubTypes.Type(value = FacebookComment.class, name = "FACEBOOK_COMMENT"),
    @JsonSubTypes.Type(value = FacebookPost.class, name = "FACEBOOK_POST"),
    @JsonSubTypes.Type(value = LinkedInArticle.class, name = "LINKEDIN_ARTICLE"),
    @JsonSubTypes.Type(value = RedditPost.class, name = "REDDIT_POST"),
    @JsonSubTypes.Type(value = TweetX.class, name = "TWEET_X")
})

@JsonIgnoreProperties(ignoreUnknown = true)

public abstract class Publication {
	private UUID uid;

	public UUID getUid() { return uid; }
	public void setUid(UUID uid) { this.uid = uid; }
    private int id;
    private String auteur;
    private LocalDateTime datePublication;

    public Publication() {
        // Laisser vide, c'est pour permettre l'héritage et Jackson
    }
    
    public Publication(int id, String auteur, LocalDateTime datePublication) {
        this.id = id;
        this.auteur = auteur;
        this.datePublication = datePublication;
    }

    // Règle de validation Niveau 1 : L'auteur ne doit pas être vide
    public boolean isValid() {
        return auteur != null && !auteur.trim().isEmpty();
    }

    public String toInfo() {
        return "Type: " + this.getClass().getSimpleName() + "\n" +
               "ID: " + id + "\n" +
               "Auteur: " + auteur + "\n" +
               "Date: " + datePublication;
    }

    // Getters et Setters
 // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public LocalDateTime getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDateTime datePublication) { this.datePublication = datePublication; }
}

