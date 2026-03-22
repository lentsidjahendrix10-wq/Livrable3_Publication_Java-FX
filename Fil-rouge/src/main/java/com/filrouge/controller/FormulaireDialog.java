package com.filrouge.controller;

import com.filrouge.model.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.time.LocalDateTime;
import java.util.UUID;

public class FormulaireDialog extends Dialog<Publication> {

    private final TextField tfAuteur           = new TextField();
    private final TextField tfId               = new TextField("0");
    private final TextField tfContenu          = new TextField();
    private final TextField tfLangue           = new TextField("fr");
    private final TextField tfNombreHashtags   = new TextField("0");
    private final TextField tfNombreRetweets   = new TextField("0");
    private final TextField tfNombreLikes      = new TextField("0");
    private final CheckBox  cbPartageAutorise  = new CheckBox("Partage autorisé");
    private final TextField tfPostReferencedId = new TextField("0");
    private final CheckBox  cbSignale          = new CheckBox("Signalé");
    private final TextField tfTitre            = new TextField();
    private final TextField tfTempsLecture     = new TextField("0");
    private final TextField tfSubreddit        = new TextField("r/");
    private final TextField tfScore            = new TextField("0");
    private final TextField tfUrlMedia         = new TextField();
    private final TextField tfDureeSecondes    = new TextField("0");
    private final TextField tfExpireDansHeures = new TextField("24");
    private final TextField tfMusique          = new TextField();
    private final TextField tfNombreVues       = new TextField("0");
    private final CheckBox  cbOrientationVert  = new CheckBox("Vertical");
    private final TextField tfQualite          = new TextField("1080p");
    private final CheckBox  cbMonetise         = new CheckBox("Monétisé");
    private final CheckBox  cbFormatVertical   = new CheckBox("Format vertical");
    private final TextField tfBudget           = new TextField("1.0");
    private final TextField tfAnnonceur        = new TextField();

    private final String type;

    public FormulaireDialog(String type, Publication existing) {
        this.type = type;
        setTitle(existing == null ? "Ajouter — " + type : "Modifier — " + type);
        setHeaderText(null);

        ButtonType btnSauver = new ButtonType("Sauvegarder", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(btnSauver, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setPadding(new Insets(20));

        int row = 0;
        grid.add(new Label("Auteur :"), 0, row); grid.add(tfAuteur, 1, row++);
        grid.add(new Label("ID :"),     0, row); grid.add(tfId,     1, row++);

        ajouterChampsSpecifiques(grid, row);

        if (existing != null) preremplir(existing);

        getDialogPane().setContent(grid);
        getDialogPane().setPrefWidth(420);

        setResultConverter(btn -> {
            if (btn == btnSauver) return construirePublication();
            return null;
        });
    }

    private void ajouterChampsSpecifiques(GridPane grid, int startRow) {
        int row = startRow;
        switch (type) {
            case "TweetX" -> {
                grid.add(new Label("Contenu :"),  0, row); grid.add(tfContenu,        1, row++);
                grid.add(new Label("Langue :"),   0, row); grid.add(tfLangue,         1, row++);
                grid.add(new Label("Hashtags :"), 0, row); grid.add(tfNombreHashtags, 1, row++);
                grid.add(new Label("Retweets :"), 0, row); grid.add(tfNombreRetweets, 1, row++);
            }
            case "FacebookPost" -> {
                grid.add(new Label("Contenu :"),  0, row); grid.add(tfContenu,       1, row++);
                grid.add(new Label("Langue :"),   0, row); grid.add(tfLangue,        1, row++);
                grid.add(new Label("Likes :"),    0, row); grid.add(tfNombreLikes,   1, row++);
                grid.add(cbPartageAutorise, 1, row++);
            }
            case "FacebookComment" -> {
                grid.add(new Label("Contenu :"),   0, row); grid.add(tfContenu,          1, row++);
                grid.add(new Label("Langue :"),    0, row); grid.add(tfLangue,           1, row++);
                grid.add(new Label("Post ID :"),   0, row); grid.add(tfPostReferencedId, 1, row++);
                grid.add(cbSignale, 1, row++);
            }
            case "LinkedInArticle" -> {
                grid.add(new Label("Contenu :"),      0, row); grid.add(tfContenu,      1, row++);
                grid.add(new Label("Langue :"),       0, row); grid.add(tfLangue,       1, row++);
                grid.add(new Label("Titre :"),        0, row); grid.add(tfTitre,        1, row++);
                grid.add(new Label("Tps lecture :"),  0, row); grid.add(tfTempsLecture, 1, row++);
            }
            case "RedditPost" -> {
                grid.add(new Label("Contenu :"),   0, row); grid.add(tfContenu,   1, row++);
                grid.add(new Label("Langue :"),    0, row); grid.add(tfLangue,    1, row++);
                grid.add(new Label("Subreddit :"), 0, row); grid.add(tfSubreddit, 1, row++);
                grid.add(new Label("Score :"),     0, row); grid.add(tfScore,     1, row++);
            }
            case "InstagramStory" -> {
                grid.add(new Label("URL Média :"),  0, row); grid.add(tfUrlMedia,         1, row++);
                grid.add(new Label("Durée (s) :"),  0, row); grid.add(tfDureeSecondes,    1, row++);
                grid.add(new Label("Expire (h) :"), 0, row); grid.add(tfExpireDansHeures, 1, row++);
                grid.add(new Label("Musique :"),    0, row); grid.add(tfMusique,          1, row++);
            }
            case "InstagramReel" -> {
                grid.add(new Label("URL Média :"), 0, row); grid.add(tfUrlMedia,      1, row++);
                grid.add(new Label("Durée (s) :"), 0, row); grid.add(tfDureeSecondes, 1, row++);
                grid.add(cbOrientationVert, 1, row++);
                grid.add(new Label("Nb vues :"),   0, row); grid.add(tfNombreVues,    1, row++);
            }
            case "YouTubeVideo" -> {
                grid.add(new Label("URL Média :"), 0, row); grid.add(tfUrlMedia,      1, row++);
                grid.add(new Label("Durée (s) :"), 0, row); grid.add(tfDureeSecondes, 1, row++);
                grid.add(new Label("Qualité :"),   0, row); grid.add(tfQualite,       1, row++);
                grid.add(cbMonetise, 1, row++);
            }
            case "YouTubeShort" -> {
                grid.add(new Label("URL Média :"), 0, row); grid.add(tfUrlMedia,      1, row++);
                grid.add(new Label("Durée (s) :"), 0, row); grid.add(tfDureeSecondes, 1, row++);
                grid.add(cbFormatVertical, 1, row++);
            }
            case "FacebookAd" -> {
                grid.add(new Label("URL Média :"), 0, row); grid.add(tfUrlMedia,      1, row++);
                grid.add(new Label("Durée (s) :"), 0, row); grid.add(tfDureeSecondes, 1, row++);
                grid.add(new Label("Budget :"),    0, row); grid.add(tfBudget,        1, row++);
                grid.add(new Label("Annonceur :"), 0, row); grid.add(tfAnnonceur,     1, row++);
            }
        }
    }

    private Publication construirePublication() {
        try {
            int id        = Integer.parseInt(tfId.getText().trim());
            String auteur = tfAuteur.getText().trim();
            LocalDateTime date = LocalDateTime.now();

            Publication pub = switch (type) {
                case "TweetX" -> new TweetX(
                    id, auteur, date,
                    tfContenu.getText(), tfLangue.getText(),
                    Integer.parseInt(tfNombreHashtags.getText()),
                    Integer.parseInt(tfNombreRetweets.getText())
                );
                case "FacebookPost" -> new FacebookPost(
                    id, auteur, date,
                    tfContenu.getText(), tfLangue.getText(),
                    Integer.parseInt(tfNombreLikes.getText()),
                    cbPartageAutorise.isSelected()
                );
                case "FacebookComment" -> new FacebookComment(
                    id, auteur, date,
                    tfContenu.getText(), tfLangue.getText(),
                    Integer.parseInt(tfPostReferencedId.getText()),
                    cbSignale.isSelected()
                );
                case "LinkedInArticle" -> new LinkedInArticle(
                    id, auteur, date,
                    tfContenu.getText(), tfLangue.getText(),
                    tfTitre.getText(),
                    Integer.parseInt(tfTempsLecture.getText())
                );
                case "RedditPost" -> new RedditPost(
                    id, auteur, date,
                    tfContenu.getText(), tfLangue.getText(),
                    tfSubreddit.getText(),
                    Integer.parseInt(tfScore.getText())
                );
                case "InstagramStory" -> new InstagramStory(
                    id, auteur, date,
                    tfUrlMedia.getText(),
                    Integer.parseInt(tfDureeSecondes.getText()),
                    Integer.parseInt(tfExpireDansHeures.getText()),
                    tfMusique.getText()
                );
                case "InstagramReel" -> new InstagramReel(
                    id, auteur, date,
                    tfUrlMedia.getText(),
                    Integer.parseInt(tfDureeSecondes.getText()),
                    cbOrientationVert.isSelected(),
                    Integer.parseInt(tfNombreVues.getText())
                );
                case "YouTubeVideo" -> new YouTubeVideo(
                    id, auteur, date,
                    tfUrlMedia.getText(),
                    Integer.parseInt(tfDureeSecondes.getText()),
                    tfQualite.getText(),
                    cbMonetise.isSelected()
                );
                case "YouTubeShort" -> new YouTubeShort(
                    id, auteur, date,
                    tfUrlMedia.getText(),
                    Integer.parseInt(tfDureeSecondes.getText()),
                    cbFormatVertical.isSelected()
                );
                case "FacebookAd" -> new FacebookAd(
                    id, auteur, date,
                    tfUrlMedia.getText(),
                    Integer.parseInt(tfDureeSecondes.getText()),
                    Double.parseDouble(tfBudget.getText()),
                    tfAnnonceur.getText()
                );
                default -> throw new IllegalStateException("Type inconnu : " + type);
            };

            pub.setUid(UUID.randomUUID());
            return pub;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void preremplir(Publication pub) {
        tfAuteur.setText(pub.getAuteur());
        tfId.setText(String.valueOf(pub.getId()));

        if (pub instanceof TweetX t) {
            tfContenu.setText(t.getContenu());
            tfLangue.setText(t.getLangue());
            tfNombreHashtags.setText(String.valueOf(t.getNombreHashtags()));
            tfNombreRetweets.setText(String.valueOf(t.getNombreRetweets()));
        } else if (pub instanceof FacebookPost fp) {
            tfContenu.setText(fp.getContenu());
            tfLangue.setText(fp.getLangue());
            tfNombreLikes.setText(String.valueOf(fp.getNombreLikes()));
            cbPartageAutorise.setSelected(fp.isPartageAutorise());
        } else if (pub instanceof FacebookComment fc) {
            tfContenu.setText(fc.getContenu());
            tfLangue.setText(fc.getLangue());
            tfPostReferencedId.setText(String.valueOf(fc.getPostReferenceId()));
            cbSignale.setSelected(fc.isSignale());
        } else if (pub instanceof LinkedInArticle la) {
            tfContenu.setText(la.getContenu());
            tfLangue.setText(la.getLangue());
            tfTitre.setText(la.getTitre());
            tfTempsLecture.setText(String.valueOf(la.getTempsLecture()));
        } else if (pub instanceof RedditPost rp) {
            tfContenu.setText(rp.getContenu());
            tfLangue.setText(rp.getLangue());
            tfSubreddit.setText(rp.getSubreddit());
            tfScore.setText(String.valueOf(rp.getScore()));
        } else if (pub instanceof InstagramStory is) {
            tfUrlMedia.setText(is.getUrlMedia());
            tfDureeSecondes.setText(String.valueOf(is.getDureeSecondes()));
            tfExpireDansHeures.setText(String.valueOf(is.getExpireDansHeures()));
            tfMusique.setText(is.getMusique());
        } else if (pub instanceof InstagramReel ir) {
            tfUrlMedia.setText(ir.getUrlMedia());
            tfDureeSecondes.setText(String.valueOf(ir.getDureeSecondes()));
            cbOrientationVert.setSelected(ir.isOrientationVerticale());
            tfNombreVues.setText(String.valueOf(ir.getNombreVues()));
        } else if (pub instanceof YouTubeVideo yv) {
            tfUrlMedia.setText(yv.getUrlMedia());
            tfDureeSecondes.setText(String.valueOf(yv.getDureeSecondes()));
            tfQualite.setText(yv.getQualite());
            cbMonetise.setSelected(yv.isMonetisee());
        } else if (pub instanceof YouTubeShort ys) {
            tfUrlMedia.setText(ys.getUrlMedia());
            tfDureeSecondes.setText(String.valueOf(ys.getDureeSecondes()));
            cbFormatVertical.setSelected(ys.isFormatVertical());
        } else if (pub instanceof FacebookAd fa) {
            tfUrlMedia.setText(fa.getUrlMedia());
            tfDureeSecondes.setText(String.valueOf(fa.getDureeSecondes()));
            tfBudget.setText(String.valueOf(fa.getBudget()));
            tfAnnonceur.setText(fa.getAnnonceur());
        }
    }
}