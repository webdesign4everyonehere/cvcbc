package application;

import javafx.application.Application; // Importation de la classe Application pour démarrer l'application JavaFX
import javafx.stage.Stage; // Importation de la classe Stage pour gérer la fenêtre principale
import javafx.scene.Scene; // Importation de la classe Scene pour gérer le contenu de la fenêtre
import javafx.scene.layout.Pane; // Importation de la classe Pane pour gérer les éléments d'interface graphique
import javafx.fxml.FXMLLoader; // Importation de la classe FXMLLoader pour charger le fichier FXML
import javafx.scene.input.MouseEvent; // Importation de la classe MouseEvent pour gérer les événements de souris
import javafx.stage.StageStyle; // Importation de la classe StageStyle pour définir le style de la fenêtre

public class Main extends Application { // La classe principale qui hérite de Application

    // Variables pour mémoriser les positions de la souris
    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) { // Méthode principale pour démarrer l'application
        try {
            // Charger la mise en page à partir du fichier FXML
            Pane root = FXMLLoader.load(getClass().getResource("layout.fxml")); // Le fichier FXML contient la structure de l'interface graphique

            // Créer la scène et appliquer les styles
            Scene scene = new Scene(root); // La scène est créée avec le panneau "root" comme contenu
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // Charger le fichier CSS pour styliser l'interface
            stage.setScene(scene); // Appliquer la scène à la fenêtre principale

            // Permettre de déplacer la fenêtre en cliquant et en faisant glisser la souris
            root.setOnMousePressed((MouseEvent event) -> { // Détecter quand la souris est pressée
                x = event.getSceneX(); // Enregistrer la position de la souris sur l'axe X
                y = event.getSceneY(); // Enregistrer la position de la souris sur l'axe Y
            });

            root.setOnMouseDragged((MouseEvent event) -> { // Détecter quand la souris est déplacée
                // Déplacer la fenêtre en fonction de la position actuelle de la souris
                stage.setX(event.getScreenX() - x); // Calculer la nouvelle position horizontale de la fenêtre
                stage.setY(event.getScreenY() - y); // Calculer la nouvelle position verticale de la fenêtre
                stage.setOpacity(0.8); // Réduire l'opacité de la fenêtre pendant le déplacement pour un effet visuel
            });

            root.setOnMouseReleased((MouseEvent event) -> { // Détecter quand la souris est relâchée
                stage.setOpacity(1); // Restaurer l'opacité normale de la fenêtre après le déplacement
            });

            // Appliquer un style transparent à la fenêtre
            stage.initStyle(StageStyle.TRANSPARENT); // La fenêtre n'aura pas de bordure, et son arrière-plan sera transparent

            // Afficher la fenêtre
            stage.show(); // La fenêtre est affichée à l'écran
        } catch (Exception e) { // Gestion des erreurs si le chargement de la scène échoue
            e.printStackTrace(); // Afficher l'exception dans la console pour déboguer
        }
    }

    public static void main(String[] args) { // Méthode principale pour lancer l'application JavaFX
        launch(args); // Démarre l'application en appelant la méthode start()
    }
}
