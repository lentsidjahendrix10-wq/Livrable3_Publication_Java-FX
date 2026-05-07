# 🎯 Fil-rouge - Gestionnaire de Publications Sociales

## 📋 Description du Projet

Application JavaFX complète permettant de gérer et planifier des publications sur plusieurs réseaux sociaux. Ce projet représente le livrable final (Livrable 3) du cours de programmation orientée objet.

### Fonctionnalités principales

- ✅ **Gestion CRUD** des publications
- ✅ **10 types de publications** (Facebook, Twitter/X, Instagram, YouTube, LinkedIn, Reddit)
- ✅ **3 vues calendrier** : Mensuelle, Hebdomadaire, Journalière
- ✅ **Table principale** avec recherche et filtres
- ✅ **Système de tags** avec couleurs personnalisées
- ✅ **Brouillons** - sauvegarde automatique
- ✅ **Export** : JSON, CSV, XML
- ✅ **Statistiques** : graphiques par réseau social

---

## 🗂️ Structure du Projet

```
Fil-rouge/
├── pom.xml                                    # Configuration Maven
├── README.md                                   # Ce fichier
├── DIAGRAMME_CLASSES.puml                      # Diagramme PlantUML
├── PROJECT_STRUCTURE.txt                       # Structure détaillée
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/filrouge/
│   │   │   │   ├── Main.java                    # Point d'entrée console
│   │   │   │   ├── DemoRepository.java          # Classe de démonstration
│   │   │   │   │
│   │   │   │   ├── model/                       # Modèles de données (POO)
│   │   │   │   │   ├── Publication.java         # Classe abstraite racine
│   │   │   │   │   ├── Tag.java                 # Système de tags
│   │   │   │   │   ├── Brouillon.java            # Modèle brouillon
│   │   │   │   │   │
│   │   │   │   │   ├── PublicationTexteSociale.java  # Sous-classe texte
│   │   │   │   │   │   ├── FacebookPost.java
│   │   │   │   │   │   ├── FacebookComment.java
│   │   │   │   │   │   ├── TweetX.java
│   │   │   │   │   │   ├── LinkedInArticle.java
│   │   │   │   │   │   └── RedditPost.java
│   │   │   │   │   │
│   │   │   │   │   └── PublicationMediaSociale.java  # Sous-classe média
│   │   │   │   │       ├── FacebookAd.java
│   │   │   │   │       ├── InstagramReel.java
│   │   │   │   │       ├── InstagramStory.java
│   │   │   │   │       ├── YouTubeVideo.java
│   │   │   │   │       └── YouTubeShort.java
│   │   │   │   │
│   │   │   │   ├── controller/                  # Contrôleurs (MVC)
│   │   │   │   │   ├── MainController.java      # Contrôleur principal
│   │   │   │   │   ├── CalendrierController.java    # Gestion calendrier
│   │   │   │   │   ├── StatistiquesController.java   # Graphiques stats
│   │   │   │   │   ├── BrouillonsController.java    # Gestion brouillons
│   │   │   │   │   └── FormulaireDialog.java        # Dialog création/édition
│   │   │   │   │
│   │   │   │   ├── view/                        # Vues calendrier
│   │   │   │   │   ├── VueCalendrier.java        # Interface abstraite
│   │   │   │   │   ├── VueMois.java              # Vue mensuelle
│   │   │   │   │   ├── VueSemaine.java           # Vue hebdomadaire
│   │   │   │   │   └── VueJour.java              # Vue journalière
│   │   │   │   │
│   │   │   │   ├── repository/                   # Couche persistance
│   │   │   │   │   ├── IPublicationRepository.java  # Interface
│   │   │   │   │   └── PublicationFileRepository.java # Implémentation JSON
│   │   │   │   │
│   │   │   │   └── ui/
│   │   │   │       └── MainApp.java              # Application JavaFX
│   │   │   │
│   │   │   └── Service/                         # Services
│   │   │       ├── ExportImport.java            # Export JSON/CSV/XML
│   │   │       └── UserPreferencesService.java  # Préférences utilisateur
│   │   │
│   │   └── resources/
│   │       ├── fxml/                            # Fichiers FXML
│   │       │   ├── main.fxml                    # Fenêtre principale
│   │       │   ├── calendrier.fxml              # Vue calendrier
│   │       │   ├── statistiques.fxml            # Page statistiques
│   │       │   └── brouillons.fxml              # Page brouillons
│   │       │
│   │       ├── repository/                      # Données JSON
│   │       │   ├── publication/                 # Publications publiées
│   │       │   │   ├── fb-001.json à fb-005.json    # FacebookPost (3 valides, 2 invalides)
│   │       │   │   ├── fc-001.json à fc-005.json    # FacebookComment (3 valides, 2 invalides)
│   │       │   │   ├── fa-001.json à fa-005.json    # FacebookAd (3 valides, 2 invalides)
│   │       │   │   ├── tw-001.json à tw-005.json    # TweetX (3 valides, 2 invalides)
│   │       │   │   ├── ir-001.json à ir-005.json    # InstagramReel (3 valides, 2 invalides)
│   │       │   │   ├── is-001.json à is-005.json    # InstagramStory (3 valides, 2 invalides)
│   │       │   │   ├── li-001.json à li-005.json    # LinkedInArticle (3 valides, 2 invalides)
│   │       │   │   ├── rd-001.json à rd-005.json    # RedditPost (3 valides, 2 invalides)
│   │       │   │   ├── yv-001.json à yv-005.json    # YouTubeVideo (3 valides, 2 invalides)
│   │       │   │   └── ys-001.json à ys-005.json    # YouTubeShort (3 valides, 2 invalides)
│   │       │   │
│   │       │   └── brouillons/                  # Brouillons sauvegardés
│   │       │       └── *.json
│   │       │
│   │       ├── style.css                        # Style sombre (défaut)
│   │       └── style-light.css                  # Style clair
│   │
│   └── test/
│       └── java/
│           └── com/filrouge/
│               ├── model/
│               │   ├── PublicationValidationTest.java  # Tests validation
│               │   └── PublicationTagTest.java          # Tests tags
│               └── repository/
│                   └── PublicationFileRepositoryTest.java  # Tests repository
│
└── target/                                      # Fichiers compilés (généré)
```

---

## 🏗️ Architecture MVC

### Modèle (Model)
- **Publication** : Classe abstraite racine avec 14 sous-classes
- **Tag** : Système de categorization colorée
- **Héritage** : Publication → PublicationTexteSociale / PublicationMediaSociale → 10 types concrets

### Vue (View)
- **FXML** : Interfaces JavaFX (main.fxml, calendrier.fxml, statistiques.fxml, brouillons.fxml)
- **Vues Java** : VueMois, VueSemaine, VueJour (implémentation du calendrier)

### Contrôleur (Controller)
- **MainController** : Logique principale (CRUD, recherche, export)
- **CalendrierController** : Gestion du calendrier et navigation
- **StatistiquesController** : Génération des graphiques
- **BrouillonsController** : Gestion des brouillons
- **FormulaireDialog** : Création et édition des publications

---

## 🧬 Modélisation POO

### Hiérarchie de classes

```
Publication (abstraite)
├── PublicationTexteSociale
│   ├── FacebookPost       → Facebook (bleu #1877f2)
│   ├── FacebookComment   → Facebook (bleu #1877f2)
│   ├── TweetX            → X/Twitter (bleu #1da1f2)
│   ├── LinkedInArticle   → LinkedIn (bleu #0077b5)
│   └── RedditPost        → Reddit (orange #ff4500)
│
└── PublicationMediaSociale
    ├── FacebookAd        → Facebook Ads (bleu #166fe5)
    ├── InstagramReel     → Instagram (violet #c13584)
    ├── InstagramStory    → Instagram (rose #e1306c)
    ├── YouTubeVideo     → YouTube (rouge #ff0000)
    └── YouTubeShort     → YouTube Shorts (rouge #ff4444)
```

### Concepts POO utilisés

| Concept | Application |
|---------|-------------|
| **Héritage** | Publication → PublicationTexteSociale / PublicationMediaSociale |
| **Polymorphisme** | isValid(), getCouleur(), getCategorie(), toInfo() |
| **Interface** | IPublicationRepository |
| **Composition** | Publication *- Tag |
| **Encapsulation** | Getters/Setters avec validation |

---

## 🚀 Instructions d'exécution

### Prérequis

- **JDK 17** ou supérieur
- **Maven 3.6+**
- **JavaFX SDK 21** (géré automatiquement par le plugin Maven)

### Commandes Maven

```bash
# Compiler le projet
mvn clean compile

# Exécuter les tests unitaires
mvn test

# Lancer l'application
mvn javafx:run

# Générer le JAR
mvn package
```

---

## 📊 Données de test

Le projet contient **50 publications fictives** réparties sur Mai-Juin-Juillet 2026 :

| Type | Valides (PUBLIEE) | Invalides |
|------|-------------------|----------------------|
| FacebookPost | 3 | 2 |
| FacebookComment | 3 | 2 |
| FacebookAd | 3 | 2 |
| TweetX | 3 | 2 |
| InstagramReel | 3 | 2 |
| InstagramStory | 3 | 2 |
| LinkedInArticle | 3 | 2 |
| RedditPost | 3 | 2 |
| YouTubeVideo | 3 | 2 |
| YouTubeShort | 3 | 2 |
| **TOTAL** | **30** | **20** |

### Validation des publications

Chaque type possède ses propres règles de validation :
- **FacebookPost** : auteur non vide, contenu ≥ 2 caractères
- **TweetX** : auteur non vide, hashtags ≤ 10
- **InstagramReel** : URL valide, format vertical obligatoire
- **FacebookAd** : budget ≥ 1€, annonceur obligatoire
- etc.

---

## 🧪 Tests unitaires

Le projet inclut **17 tests unitaires** :

```bash
mvn test
# Résultat: Tests run: 17, Failures: 0, Errors: 0
```

### Couverture des tests
- **PublicationValidationTest** : Validation des règles métier
- **PublicationTagTest** : Système de tags
- **PublicationFileRepositoryTest** : Persistance JSON

---

## 📦 Dépendances Maven

| Dépendance | Version | Utilisation |
|------------|---------|-------------|
| JavaFX Controls | 21 | Interface utilisateur |
| JavaFX FXML | 21 | Chargement des vues |
| Jackson Core | 2.15.2 | Parsing JSON |
| JUnit 5 | 5.10.0 | Tests unitaires |
| Maven JAR Plugin | 3.3.0 | Génération JAR |

---

## 🎨 Interface utilisateur

### Thème sombre (défaut)
- Couleur de fond : `#111111`
- Couleur secondaire : `#141414`
- Accent : Bleu `#2563eb`
- Texte : Gris clair `#cccccc`

### Fonctionnalités UI
- **Table principale** : Colonnes ID, Type, Auteur, Date, Statut
- **Barre de recherche** : Filtrage en temps réel par auteur/contenu/tag
- **Filtres rapides** : Tous / Texte / Média
- **Panneau détails** : Affichage des informations complètes
- **Calendrier** : 3 vues (Mois/Semaine/Jour) avec navigation
- **Statistiques** : Graphiques BarChart et PieChart
- **Couleurs par réseau** : Chaque type a sa propre couleur

---

## 📝 Fonctionnalités détaillées

### CRUD des publications
- **Ajouter** : Bouton "+ Ajouter" → FormulaireDialog
- **Modifier** : Sélectionner → Bouton "✏ Modifier"
- **Supprimer** : Sélectionner → Bouton "🗑 Supprimer"
- **Rechercher** : Barre de recherche en haut

### Calendrier
- **Vue Mois** : Grille 7x5 avec les jours du mois
- **Vue Semaine** : 7 jours avec détails
- **Vue Jour** : Liste des publications du jour
- **Navigation** : Boutons ← → et "Aujourd'hui"
- **Drag & Drop** : Déplacer les publications entre dates
- **Ajout rapide** : Bouton "+" dans chaque vue

### Export/Import
- **JSON** : Export complet avec tous les champs
- **CSV** : Format tableur (Excel-compatible)
- **XML** : Format structuré
- **Import JSON** : Importer des publications depuis un fichier

### Brouillons
- **Sauvegarde automatique** : Toutes les 5 minutes
- **Page dédiée** : Bouton "📁 Brouillons"
- **Édition** : Modifier et republier

---

## 🔧 Configuration

### Fichier de préférences
- `user.home/.filrouge/preferences.json`
- Sauvegarde : thème, dernière vue calendrier, etc.

### Repository de données
- Publications : `src/main/resources/repository/publication/`
- Brouillons : `src/main/resources/repository/brouillons/`