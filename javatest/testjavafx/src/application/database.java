package application;

import java.sql.Connection; // Importation de la classe Connection pour gérer la connexion à la base de données
import java.sql.DriverManager; // Importation de la classe DriverManager pour établir la connexion

public class database { // Classe permettant de gérer la connexion à la base de données
    
    // Méthode statique pour se connecter à la base de données
    public static Connection connectDb(){
        
        try{
            // Charger le driver MySQL nécessaire pour se connecter à la base de données
            // Cette ligne permet de charger la classe nécessaire pour utiliser MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Établir la connexion à la base de données
            // Utilisation de DriverManager pour obtenir une connexion à la base de données 'servicehubdata'
            // L'URL JDBC indique le chemin de la base de données (localhost), le nom de la base (servicehubdata),
            // le nom d'utilisateur (root) et le mot de passe (ici, vide)
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/servicehubdata", "root", "");
            
            // Si la connexion est établie avec succès, afficher un message dans la console
            System.out.println("Connexion établie avec succès à la base de données servicehubdata.");
            
            // Retourner l'objet Connection pour pouvoir l'utiliser dans d'autres parties du programme
            return connect;
        }catch(Exception e){ // Si une exception se produit (ex: échec de la connexion)
            e.printStackTrace(); // Afficher l'exception dans la console pour déboguer
        }
        return null; // Retourner null si la connexion échoue
    }
}
