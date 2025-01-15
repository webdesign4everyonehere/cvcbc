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
    private Label animatedLabel; // R�f�rence � l'�tiquette pour l'animation

    // DATABASE TOOLS
    private Connection connect; // Connexion � la base de donn�es
    private PreparedStatement prepare; // Pr�paration de la requ�te SQL
    private ResultSet result; // R�sultat de la requ�te SQL

    private double x = 0; // Coordonn�e X pour d�placer la fen�tre
    private double y = 0; // Coordonn�e Y pour d�placer la fen�tre

    // Texte � afficher avec un effet de frappe
    private final String typingText = "Connecting You to Possibilities, Seamlessly.";

    // M�thode d'initialisation pour d�finir l'animation de frappe
    @FXML
    public void initialize() {
        typingAnimation(); // Lance l'animation de frappe
    }

    // M�thode pour l'animation de frappe
    private void typingAnimation() {
        Timeline timeline = new Timeline();
        final int[] index = {0}; // Suivre l'index actuel du caract�re

        // Cr�er un KeyFrame pour mettre � jour le texte de l'�tiquette � intervalles r�guliers
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            if (index[0] < typingText.length()) {
                animatedLabel.setText(typingText.substring(0, index[0] + 1)); // Ajouter un caract�re � la fois
                index[0]++; // Incr�menter l'index
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(typingText.length()); // Jouer pour la longueur du texte
        timeline.play(); // Lancer l'animation
    }
    
    // M�thode utilitaire pour hacher les mots de passe avec SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // Utilisation de l'algorithme SHA-256
            byte[] hashedBytes = md.digest(password.getBytes()); // Hacher le mot de passe
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b)); // Convertir chaque octet en hexad�cimal
            }
            return sb.toString(); // Retourner le mot de passe hach�
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e); // G�rer l'exception si l'algorithme n'est pas trouv�
        }
    }

    // M�thode pour se connecter en tant qu'administrateur
    public void loginAdmin() {
        String sql = "SELECT password FROM admin WHERE username = ?"; // Requ�te SQL pour obtenir le mot de passe de l'administrateur
        
        connect = database.connectDb(); // �tablir la connexion � la base de donn�es
        
        try {
            Alert alert; // D�claration de l'alerte
            prepare = connect.prepareStatement(sql); // Pr�parer la requ�te SQL
            prepare.setString(1, username.getText());  // Obtenir le nom d'utilisateur saisi

            result = prepare.executeQuery(); // Ex�cuter la requ�te et obtenir le r�sultat

            if (username.getText().isEmpty() || password.getText().isEmpty()) { // V�rifier si les champs sont vides
                alert = new Alert(AlertType.ERROR); // Cr�er une alerte d'erreur
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields"); // Afficher un message d'erreur
                alert.showAndWait(); // Attendre que l'utilisateur ferme l'alerte
            } else {
                if (result.next()) { // Si un r�sultat est trouv� dans la base de donn�es
                    String storedHashedPassword = result.getString("password"); // R�cup�rer le mot de passe stock�
                    String enteredHashedPassword = hashPassword(password.getText());  // Hacher le mot de passe saisi

                    if (storedHashedPassword.equals(enteredHashedPassword)) { // Comparer les mots de passe hach�s
                        alert = new Alert(AlertType.INFORMATION); // Cr�er une alerte de succ�s
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login!"); // Afficher un message de connexion r�ussie
                        alert.showAndWait();
                        login.getScene().getWindow().hide(); // Fermer la fen�tre de connexion

                        // Charger le tableau de bord
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show(); // Afficher le tableau de bord
                    } else {
                        alert = new Alert(AlertType.ERROR); // Cr�er une alerte d'erreur pour mot de passe incorrect
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Username/Password"); // Afficher un message d'erreur
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(AlertType.ERROR); // Cr�er une alerte d'erreur si le nom d'utilisateur n'est pas trouv�
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // G�rer les exceptions
        }
    }

    // M�thode pour fermer l'application
    @FXML
    private void onClose() {
        System.exit(0); // Fermer l'application lorsque le bouton de fermeture est cliqu�
    }
}
