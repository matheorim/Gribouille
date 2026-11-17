# Gribouille

🇫🇷 [Version française](README.fr.md)

A drawing application built with **JavaFX** as part of the IUT practical work (TP1 to TP6). Gribouille lets you draw freehand with a pencil, add stars, pick colors and manage your drawing from a graphical interface.

## Features

- Freehand drawing with the **pencil** tool
- **Star** shapes
- Stroke color selection
- Menu bar (drawing management)
- Status bar
- Dialog boxes (`Dialogues.java`)

## Technologies

- **Java** (with modules, see `module-info.java`)
- **JavaFX** for the graphical interface (FXML files)
- **Maven** for dependency management and build

## Requirements

- JDK 17 or higher (adjust to the version set in `pom.xml`)
- Maven 3.8 or higher
- JavaFX (fetched automatically by Maven through `pom.xml`)

## Installation and launch

```bash
git clone https://github.com/matheorim/Gribouille.git
cd Gribouille
git checkout gribouille_stable
cd gribouille
mvn clean javafx:run
```

> The `mvn javafx:run` command assumes that the `javafx-maven-plugin` is configured in `pom.xml`. Otherwise, open the `gribouille` folder in your IDE (IntelliJ IDEA, Eclipse, NetBeans) and run the class `App`.

## Project structure

```
gribouille/
├── pom.xml
└── src/main/
    ├── java/
    │   ├── module-info.java
    │   └── iut/gon/gribouille_tp1/
    │       ├── App.java                  # Application entry point
    │       ├── Dialogues.java            # Dialog boxes
    │       ├── controleurs/
    │       │   ├── Controleur.java
    │       │   ├── CouleursControleur.java
    │       │   ├── DessinControleur.java
    │       │   ├── MenusControleur.java
    │       │   ├── StatutControleur.java
    │       │   ├── Outil.java            # Base interface / class of the tools
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
        ├── CadreGribouille.fxml          # Main window
        ├── couleurs.fxml                 # Color picker
        ├── dessin.fxml                   # Drawing area
        ├── menus.fxml                    # Menu bar
        └── statut.fxml                   # Status bar
```

## Architecture

The project follows the **MVC** pattern (Model - View - Controller).

**Model** (`modele`): represents the drawing data.
- `Dessin` holds all the figures.
- `Figure` is the base class of every drawn element; `Trace` (pencil stroke) and `Etoile` (star) extend it.
- `Point` represents a position in the drawing.

**View** (FXML files): the interface is split into several files (main frame, menus, colors, drawing, status) assembled in `CadreGribouille.fxml`.

**Controllers** (`controleurs`): one controller per interface area connects the view to the model. The drawing tools (`OutilCrayon`, `OutilEtoile`) extend `Outil`, which makes it easy to add new ones.

## Branches

| Branch | Content |
|---|---|
| `master` | Default branch |
| `gribouille_stable` | Stable version of the project |
| `gribouille_tp1` to `gribouille_tp6` | Evolution of the project across the practical sessions |
| `TP1` to `TP6` | Steps of each practical session |
| `gribouille_dessin`, `gribouille_geometrie` | Thematic development branches |

## Author

**matheorim**, project completed at the IUT.
