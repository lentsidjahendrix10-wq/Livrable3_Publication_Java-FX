package com.filrouge;

import com.filrouge.model.*; // Pour utiliser YouTubeVideo, TweetX, etc.
import com.filrouge.repository.PublicationFileRepository;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class DemoRepository {
    public static void main(String[] args) throws Exception {
        // Configuration du dossier (il sera créé automatiquement dans ton projet)
    	PublicationFileRepository repo = new PublicationFileRepository();

        // 1. Création d'un exemple
        YouTubeVideo maVideo = new YouTubeVideo();
        maVideo.setAuteur("Mon Pseudo");
        // Remplis d'autres champs si tu en as (ex: maVideo.setTitle("Ma vidéo");)

        // 2. Sauvegarde
        System.out.println("Tentative de sauvegarde...");
        repo.save(maVideo); 
        System.out.println("Réussi ! UUID généré : " + maVideo.getUid());

        // 3. Lecture pour vérifier
        List<Publication> liste = repo.findAll();
        System.out.println("Nombre de fichiers lus sur le disque : " + liste.size());
        
        for (Publication p : liste) {
            System.out.println("Objet récupéré : " + p.getClass().getSimpleName() + " par " + p.getAuteur());
        }
    }
}