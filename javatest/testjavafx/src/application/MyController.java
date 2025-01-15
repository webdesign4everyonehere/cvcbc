package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MyController {

    @FXML
    private AnchorPane loginBar;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button login;

    @FXML
    private TextField username; // Champ pour saisir le nom d'utilisateur

    @FXML
    private PasswordField password; // Champ pour saisir le mot de passe

    @FXML
    private Label animatedLabel; // Référence à l'étiquette pour l'animation

    // DATABASE TOOLS
    private Connection connect; // Connexion à la base de données
    private PreparedStatement prepare; // Préparation de la requête SQL
    private ResultSet result; // Résultat de la requête SQL

    private double x = 0; // Coordonnée X pour déplacer la fenêtre
    private double y = 0; // Coordonnée Y pour déplacer la fenêtre

    // Texte à afficher avec un effet de frappe
    private final String typingText = "Connecting You to Possibilities, Seamlessly.";

    // Méthode d'initialisation pour définir l'animation de frappe
    @FXML
    public void initialize() {
        typingAnimation(); // Lance l'animation de frappe
    }

    // Méthode pour l'animation de frappe
    private void typingAnimation() {
        Timeline timeline = new Timeline();
        final int[] index = {0}; // Suivre l'index actuel du caractère

        // Créer un KeyFrame pour mettre à jour le texte de l'étiquette à intervalles réguliers
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            if (index[0] < typingText.length()) {
                animatedLabel.setText(typingText.substring(0, index[0] + 1)); // Ajouter un caractère à la fois
                index[0]++; // Incrémenter l'index
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(typingText.length()); // Jouer pour la longueur du texte
        timeline.play(); // Lancer l'animation
    }
    
    // Méthode utilitaire pour hacher les mots de passe avec SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // Utilisation de l'algorithme SHA-256
            byte[] hashedBytes = md.digest(password.getBytes()); // Hacher le mot de passe
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b)); // Convertir chaque octet en hexadécimal
            }
            return sb.toString(); // Retourner le mot de passe haché
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e); // Gérer l'exception si l'algorithme n'est pas trouvé
        }
    }

    // Méthode pour se connecter en tant qu'administrateur
    public void loginAdmin() {
        String sql = "SELECT password FROM admin WHERE username = ?"; // Requête SQL pour obtenir le mot de passe de l'administrateur
        
        connect = database.connectDb(); // Établir la connexion à la base de données
        
        try {
            Alert alert; // Déclaration de l'alerte
            prepare = connect.prepareStatement(sql); // Préparer la requête SQL
            prepare.setString(1, username.getText());  // Obtenir le nom d'utilisateur saisi

            result = prepare.executeQuery(); // Exécuter la requête et obtenir le résultat

            if (username.getText().isEmpty() || password.getText().isEmpty()) { // Vérifier si les champs sont vides
                alert = new Alert(AlertType.ERROR); // Créer une alerte d'erreur
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields"); // Afficher un message d'erreur
                alert.showAndWait(); // Attendre que l'utilisateur ferme l'alerte
            } else {
                if (result.next()) { // Si un résultat est trouvé dans la base de données
                    String storedHashedPassword = result.getString("password"); // Récupérer le mot de passe stocké
                    String enteredHashedPassword = hashPassword(password.getText());  // Hacher le mot de passe saisi

                    if (storedHashedPassword.equals(enteredHashedPassword)) { // Comparer les mots de passe hachés
                        alert = new Alert(AlertType.INFORMATION); // Créer une alerte de succès
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login!"); // Afficher un message de connexion réussie
                        alert.showAndWait();
                        login.getScene().getWindow().hide(); // Fermer la fenêtre de connexion

                        // Charger le tableau de bord
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show(); // Afficher le tableau de bord
                    } else {
                        alert = new Alert(AlertType.ERROR); // Créer une alerte d'erreur pour mot de passe incorrect
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Username/Password"); // Afficher un message d'erreur
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(AlertType.ERROR); // Créer une alerte d'erreur si le nom d'utilisateur n'est pas trouvé
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions
        }
    }

    // Méthode pour fermer l'application
    @FXML
    private void onClose() {
        System.exit(0); // Fermer l'application lorsque le bouton de fermeture est cliqué
    }
}
