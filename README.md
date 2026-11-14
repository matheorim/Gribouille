# Gribouille

Application de dessin en **JavaFX** réalisée dans le cadre des travaux pratiques de l'IUT (TP1 à TP6). Gribouille permet de dessiner à main levée avec un crayon, d'ajouter des étoiles, de choisir ses couleurs et de gérer son dessin depuis une interface graphique.

## Fonctionnalités

- Dessin à main levée avec l'outil **crayon**
- Ajout de figures **étoile**
- Choix de la couleur de tracé
- Barre de menus (gestion du dessin)
- Barre de statut
- Boîtes de dialogue (`Dialogues.java`)

## Technologies

- **Java** (avec modules, voir `module-info.java`)
- **JavaFX** pour l'interface graphique (fichiers FXML)
- **Maven** pour la gestion des dépendances et la compilation

## Prérequis

- JDK 17 ou supérieur (adapte selon la version indiquée dans `pom.xml`)
- Maven 3.8 ou supérieur
- JavaFX (récupéré automatiquement par Maven via le `pom.xml`)

## Installation et lancement

```bash
git clone https://github.com/matheorim/Gribouille.git
cd Gribouille
git checkout gribouille_stable
cd gribouille
mvn clean javafx:run
```

> La commande `mvn javafx:run` suppose que le plugin `javafx-maven-plugin` est configuré dans le `pom.xml`. Sinon, ouvre le dossier `gribouille` dans ton IDE (IntelliJ IDEA, Eclipse, NetBeans) et lance la classe `App`.

## Structure du projet

```
gribouille/
├── pom.xml
└── src/main/
    ├── java/
    │   ├── module-info.java
    │   └── iut/gon/gribouille_tp1/
    │       ├── App.java                  # Point d'entrée de l'application
    │       ├── Dialogues.java            # Boîtes de dialogue
    │       ├── controleurs/
    │       │   ├── Controleur.java
    │       │   ├── CouleursControleur.java
    │       │   ├── DessinControleur.java
    │       │   ├── MenusControleur.java
    │       │   ├── StatutControleur.java
    │       │   ├── Outil.java            # Interface / classe de base des outils
    │       │   ├── OutilCrayon.java
    │       │   └── OutilEtoile.java
    │       └── modele/
    │           ├── Dessin.java
    │           ├── Figure.java
    │           ├── Trace.java
    │           ├── Etoile.java
    │           ├── Point.java
    │           └── package-info.java
    └── resources/iut/gon/gribouille_tp1/
        ├── CadreGribouille.fxml          # Fenêtre principale
        ├── couleurs.fxml                 # Sélecteur de couleurs
        ├── dessin.fxml                   # Zone de dessin
        ├── menus.fxml                    # Barre de menus
        └── statut.fxml                   # Barre de statut
```

## Architecture

Le projet suit le patron **MVC** (Modèle - Vue - Contrôleur).

**Modèle** (`modele`) : représente les données du dessin.
- `Dessin` regroupe l'ensemble des figures.
- `Figure` est la classe de base de tout élément dessiné, dont `Trace` (trait du crayon) et `Etoile` héritent.
- `Point` représente une position dans le dessin.

**Vue** (fichiers FXML) : l'interface est découpée en plusieurs fichiers (cadre principal, menus, couleurs, dessin, statut) assemblés dans `CadreGribouille.fxml`.

**Contrôleurs** (`controleurs`) : un contrôleur par zone de l'interface fait le lien entre la vue et le modèle. Les outils de dessin (`OutilCrayon`, `OutilEtoile`) héritent de `Outil`, ce qui permet d'en ajouter de nouveaux facilement.

## Branches

| Branche | Contenu |
|---|---|
| `master` | Branche par défaut |
| `gribouille_stable` | Version stable du projet |
| `gribouille_tp1` à `gribouille_tp6` | Évolution du projet au fil des TP |
| `TP1` à `TP6` | Étapes de chaque TP |
| `gribouille_dessin`, `gribouille_geometrie` | Branches de développement thématiques |

## Auteur

**matheorim**, projet réalisé à l'IUT.
