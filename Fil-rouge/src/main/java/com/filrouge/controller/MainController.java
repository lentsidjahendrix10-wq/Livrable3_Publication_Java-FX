package com.filrouge.controller;

import com.filrouge.model.Publication;
import com.filrouge.repository.PublicationFileRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.time.LocalDateTime;
import java.net.URL;
import java.util.ResourceBundle;
import com.filrouge.model.PublicationTexteSociale;
import com.filrouge.model.PublicationMediaSociale;

public class MainController implements Initializable {

    @FXML private TableView<Publication>            tableView;
    @FXML private TableColumn<Publication, String>  colId;
    @FXML private TableColumn<Publication, String>  colType;
    @FXML private TableColumn<Publication, String>  colAuteur;
    @FXML private TableColumn<Publication, String>  colDate;
    @FXML private TableColumn<Publication, String>  colValide;
    @FXML private TextArea                          detailArea;
    @FXML private Label                             statusLabel;
    @FXML private ComboBox<String>                  typeCombo;
    @FXML private TextField searchField;
    @FXML private Label     statTotal;
    @FXML private Label     statValides;
    @FXML private Label     statInvalides;
    @FXML private Button    btnFiltreAll;
    @FXML private Button    btnFiltreTexte;
    @FXML private Button    btnFiltreMedia;

    private final PublicationFileRepository repo = PublicationFileRepository.getInstance();
    private final ObservableList<Publication> publications = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurerColonnes();
        configurerTypeCombo();
        configurerSelectionListener();
        chargerPublications();
    }

    private void configurerColonnes() {
        colId.setCellValueFactory(data ->
            new SimpleStringProperty(String.valueOf(data.getValue().getId())));

        colType.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getClass().getSimpleName()));

        colType.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setGraphic(null); return; }
                Label badge = new Label(item);
                Publication pub = getTableView().getItems().get(getIndex());
                badge.getStyleClass().add(
                    pub instanceof PublicationTexteSociale ? "badge-texte" : "badge-media"
                );
                setGraphic(badge);
                setText(null);
            }
        });

        colAuteur.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getAuteur()));

        colDate.setCellValueFactory(data -> {
            LocalDateTime date = data.getValue().getDatePublication();
            return new SimpleStringProperty(date != null ? date.toString() : "N/A");
        });

        colValide.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().isValid() ? "✓ Valide" : "✗ Invalide"));

        colValide.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setStyle(""); return; }
                setText(item);
                updateStyle(item, isSelected());
            }

            @Override
            public void updateSelected(boolean selected) {
                super.updateSelected(selected);
                if (getItem() != null) updateStyle(getItem(), selected);
            }

            private void updateStyle(String item, boolean selected) {
                if (selected) {
                    setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
                } else if (item.startsWith("✓")) {
                    setStyle("-fx-text-fill: #16a34a; -fx-font-weight: bold;");
                } else {
                    setStyle("-fx-text-fill: #dc2626; -fx-font-weight: bold;");
                }
            }
        });

        tableView.setItems(publicationsFiltrees);
    }

    private void configurerTypeCombo() {
        typeCombo.setItems(FXCollections.observableArrayList(
            "FacebookComment",
            "FacebookPost",
            "LinkedInArticle",
            "RedditPost",
            "TweetX",
            "FacebookAd",
            "InstagramReel",
            "InstagramStory",
            "YouTubeShort",
            "YouTubeVideo"
        ));
    }
    
    private String filtreActif = "TOUS";
    private final javafx.collections.transformation.FilteredList<Publication> publicationsFiltrees =
        new javafx.collections.transformation.FilteredList<>(publications, p -> true);

    private void configurerSelectionListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener(
            (obs, ancienne, nouvelle) -> {
                if (nouvelle != null) {
                    detailArea.setText(nouvelle.toInfo());
                } else {
                    detailArea.clear();
                }
            }
        );
    }

    private void chargerPublications() {
        publications.clear();
        publications.addAll(repo.findAll());
        appliquerFiltre();
        mettreAJourStats();
    }

    private void mettreAJourStats() {
        int total    = publications.size();
        long valides = publications.stream().filter(Publication::isValid).count();
        long invalides = total - valides;
        statTotal.setText(total + " publication" + (total > 1 ? "s" : ""));
        statValides.setText(valides + " valide" + (valides > 1 ? "s" : ""));
        statInvalides.setText(invalides + " invalide" + (invalides > 1 ? "s" : ""));
        statusLabel.setText(total + " publication(s) chargée(s)");
    }

    private void appliquerFiltre() {
        String recherche = searchField != null ? searchField.getText().toLowerCase() : "";
        publicationsFiltrees.setPredicate(pub -> {
            boolean matchFiltre = switch (filtreActif) {
                case "TEXTE" -> pub instanceof PublicationTexteSociale;
                case "MEDIA" -> pub instanceof PublicationMediaSociale;
                default      -> true;
            };
            boolean matchRecherche = recherche.isEmpty()
                || pub.getClass().getSimpleName().toLowerCase().contains(recherche)
                || (pub.getAuteur() != null && pub.getAuteur().toLowerCase().contains(recherche));
            return matchFiltre && matchRecherche;
        });
    }

    @FXML
    private void handleRafraichir() {
        chargerPublications();
    }

    @FXML
    private void handleAjouter() {
        String type = typeCombo.getValue();
        if (type == null) {
            statusLabel.setText("Choisissez un type d'abord !");
            return;
        }
        FormulaireDialog dialog = new FormulaireDialog(type, null);
        dialog.showAndWait().ifPresent(pub -> {
            if (pub == null) {
                statusLabel.setText("Erreur lors de la création.");
                return;
            }
            repo.save(pub);
            publications.add(pub);
            appliquerFiltre();
            mettreAJourStats();
            tableView.scrollTo(pub);
            tableView.getSelectionModel().select(pub);
            if (!pub.isValid()) {
                statusLabel.setText("Publication ajoutée mais INVALIDE !");
            } else {
                statusLabel.setText("Publication ajoutée avec succès !");
            }
        });
    }

    @FXML
    private void handleModifier() {
        Publication selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Sélectionnez une publication d'abord !");
            return;
        }
        String type = selected.getClass().getSimpleName();
        FormulaireDialog dialog = new FormulaireDialog(type, selected);
        dialog.showAndWait().ifPresent(pub -> {
            if (pub == null) {
                statusLabel.setText("Erreur lors de la modification.");
                return;
            }
            pub.setUid(selected.getUid());
            repo.save(pub);
            int idx = publications.indexOf(selected);
            publications.set(idx, pub);
            appliquerFiltre();
            mettreAJourStats();
            tableView.getSelectionModel().select(pub);
            if (!pub.isValid()) {
                statusLabel.setText("Publication modifiée mais INVALIDE !");
            } else {
                statusLabel.setText("Publication modifiée avec succès !");
            }
        });
    }

    @FXML
    private void handleSupprimer() {
        Publication selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Sélectionnez une publication d'abord !");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
            "Supprimer cette publication ?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                repo.delete(selected.getUid());
                publications.remove(selected);
                detailArea.clear();
                statusLabel.setText("Publication supprimée.");
            }
        });
    }
    
    @FXML
    private void handleRecherche() {
        appliquerFiltre();
    }

    @FXML
    private void handleFiltreAll() {
        filtreActif = "TOUS";
        btnFiltreAll.getStyleClass().setAll("filter-btn-active");
        btnFiltreTexte.getStyleClass().setAll("filter-btn");
        btnFiltreMedia.getStyleClass().setAll("filter-btn");
        appliquerFiltre();
    }

    @FXML
    private void handleFiltreTexte() {
        filtreActif = "TEXTE";
        btnFiltreAll.getStyleClass().setAll("filter-btn");
        btnFiltreTexte.getStyleClass().setAll("filter-btn-active");
        btnFiltreMedia.getStyleClass().setAll("filter-btn");
        appliquerFiltre();
    }

    @FXML
    private void handleFiltreMedia() {
        filtreActif = "MEDIA";
        btnFiltreAll.getStyleClass().setAll("filter-btn");
        btnFiltreTexte.getStyleClass().setAll("filter-btn");
        btnFiltreMedia.getStyleClass().setAll("filter-btn-active");
        appliquerFiltre();
    }
}