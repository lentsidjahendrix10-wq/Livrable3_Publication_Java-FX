package com.filrouge.model;
import java.time.LocalDateTime;

public class FacebookComment extends PublicationTexteSociale {
    private int postReferenceId;
    private boolean signale;

    public FacebookComment(int id, String auteur, LocalDateTime date, String contenu, String langue, int postRef, boolean signale) {
        super(id, auteur, date, contenu, langue);
        this.postReferenceId = postRef;
        this.signale = signale;
    }

    @Override
    public boolean isValid() {
        // Règle Niveau 3 : Un commentaire signalé est considéré invalide
        return super.isValid() && !signale && postReferenceId > 0;
    }

    @Override
    public String toInfo() {
        return super.toInfo() + "\nRéponse au post ID: " + postReferenceId + "\nSignalé: " + signale;
    }
    
    public int getPostReferenceId() { return postReferenceId; }
    public boolean isSignale()      { return signale;         }
    public FacebookComment() { super(); }
    public void setPostReferenceId(int id) { this.postReferenceId = id; }
    public void setSignale(boolean s)      { this.signale = s;          }
}