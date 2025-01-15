package application;

import javafx.application.Application; // Importation de la classe Application pour d�marrer l'application JavaFX
import javafx.stage.Stage; // Importation de la classe Stage pour g�rer la fen�tre principale
import javafx.scene.Scene; // Importation de la classe Scene pour g�rer le contenu de la fen�tre
import javafx.scene.layout.Pane; // Importation de la classe Pane pour g�rer les �l�ments d'interface graphique
import javafx.fxml.FXMLLoader; // Importation de la classe FXMLLoader pour charger le fichier FXML
import javafx.scene.input.MouseEvent; // Importation de la classe MouseEvent pour g�rer les �v�nements de souris
import javafx.stage.StageStyle; // Importation de la classe StageStyle pour d�finir le style de la fen�tre

public class Main extends Application { // La classe principale qui h�rite de Application

    // Variables pour m�moriser les positions de la souris
    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) { // M�thode principale pour d�marrer l'application
        try {
            // Charger la mise en page � partir du fichier FXML
            Pane root = FXMLLoader.load(getClass().getResource("layout.fxml")); // Le fichier FXML contient la structure de l'interface graphique

            // Cr�er la sc�ne et appliquer les styles
            Scene scene = new Scene(root); // La sc�ne est cr��e avec le panneau "root" comme contenu
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // Charger le fichier CSS pour styliser l'interface
            stage.setScene(scene); // Appliquer la sc�ne � la fen�tre principale

            // Permettre de d�placer la fen�tre en cliquant et en faisant glisser la souris
            root.setOnMousePressed((MouseEvent event) -> { // D�tecter quand la souris est press�e
                x = event.getSceneX(); // Enregistrer la position de la souris sur l'axe X
                y = event.getSceneY(); // Enregistrer la position de la souris sur l'axe Y
            });

            root.setOnMouseDragged((MouseEvent event) -> { // D�tecter quand la souris est d�plac�e
                // D�placer la fen�tre en fonction de la position actuelle de la souris
                stage.setX(event.getScreenX() - x); // Calculer la nouvelle position horizontale de la fen�tre
                stage.setY(event.getScreenY() - y); // Calculer la nouvelle position verticale de la fen�tre
                stage.setOpacity(0.8); // R�duire l'opacit� de la fen�tre pendant le d�placement pour un effet visuel
            });

            root.setOnMouseReleased((MouseEvent event) -> { // D�tecter quand la souris est rel�ch�e
                stage.setOpacity(1); // Restaurer l'opacit� normale de la fen�tre apr�s le d�placement
            });

            // Appliquer un style transparent � la fen�tre
            stage.initStyle(StageStyle.TRANSPARENT); // La fen�tre n'aura pas de bordure, et son arri�re-plan sera transparent

            // Afficher la fen�tre
            stage.show(); // La fen�tre est affich�e � l'�cran
        } catch (Exception e) { // Gestion des erreurs si le chargement de la sc�ne �choue
            e.printStackTrace(); // Afficher l'exception dans la console pour d�boguer
        }
    }

    public static void main(String[] args) { // M�thode principale pour lancer l'application JavaFX
        launch(args); // D�marre l'application en appelant la m�thode start()
    }
}
