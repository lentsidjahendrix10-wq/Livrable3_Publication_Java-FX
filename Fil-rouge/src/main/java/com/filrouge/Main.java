package com.filrouge;

import com.filrouge.model.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Publication> publications = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        // 1. FacebookPost
        publications.add(new FacebookPost(1, "Alice", now, "Post valide", "FR", 10, true));
        publications.add(new FacebookPost(2, "Bob", now, "Post invalide", "FR", -5, false));

        // 2. FacebookComment
        publications.add(new FacebookComment(3, "Jean", now, "Super!", "FR", 1, false));
        publications.add(new FacebookComment(4, "Hater", now, "Nul", "FR", 1, true)); // Invalide car signalé

        // 3. TweetX
        publications.add(new TweetX(5, "Elon", now, "Go to Mars", "EN", 2, 100));
        publications.add(new TweetX(6, "Spammer", now, "Buy crypto", "EN", 50, 0)); // Invalide (trop de hashtags)

        // 4. LinkedInArticle
        publications.add(new LinkedInArticle(7, "ProUser", now, "Contenu long...", "FR", "Mon Titre", 5));
        publications.add(new LinkedInArticle(8, "Lazy", now, "Contenu...", "FR", "T", 1)); // Invalide (titre trop court)

        // 5. RedditPost
        publications.add(new RedditPost(9, "Redditor", now, "Meme", "EN", "r/java", 500));
        publications.add(new RedditPost(10, "Lost", now, "Hello", "EN", "general", 10)); // Invalide (pas de r/)

        // 6. InstagramStory
        publications.add(new InstagramStory(11, "Influencer", now, "http://media.com/1", 15, 24, "Lofi"));
        publications.add(new InstagramStory(12, "Late", now, "http://media.com/2", 15, 48, "None")); // Invalide (expire > 24h)

        // 7. InstagramReel
        publications.add(new InstagramReel(13, "Creator", now, "http://video.com/1", 30, true, 1000));
        publications.add(new InstagramReel(14, "Wrong", now, "http://video.com/2", 30, false, 0)); // Invalide (pas vertical)

        // 8. YouTubeVideo
        publications.add(new YouTubeVideo(15, "Youtuber", now, "http://yt.com/1", 300, "1080p", true));
        publications.add(new YouTubeVideo(16, "Shorty", now, "http://yt.com/2", 10, "720p", false)); // Invalide (durée < 30s)

        // 9. YouTubeShort
        publications.add(new YouTubeShort(17, "ShortsMaker", now, "http://yt.com/s1", 45, true));
        publications.add(new YouTubeShort(18, "Cine", now, "http://yt.com/s2", 45, false)); // Invalide (pas vertical)

        // 10. FacebookAd
        publications.add(new FacebookAd(19, "Brand", now, "http://ads.com/1", 20, 50.0, "Nike"));
        publications.add(new FacebookAd(20, "Cheap", now, "http://ads.com/2", 20, 0.5, "Scam")); // Invalide (budget < 1.0)

        // --- AFFICHAGE ---
        System.out.println("================================");
        System.out.println("   PUBLICATIONS VALIDES");
        System.out.println("================================");
        for (Publication p : publications) {
            if (p.isValid()) {
                System.out.println(p.toInfo());
                System.out.println("--------------------------------");
            }
        }

        System.out.println("\n================================");
        System.out.println("   PUBLICATIONS NON VALIDES");
        System.out.println("================================");
        for (Publication p : publications) {
            if (!p.isValid()) {
                System.out.println(p.toInfo());
                System.out.println("--------------------------------");
            }
        }
    }
}