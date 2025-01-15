package application;

import java.sql.Connection; // Importation de la classe Connection pour g�rer la connexion � la base de donn�es
import java.sql.DriverManager; // Importation de la classe DriverManager pour �tablir la connexion

public class database { // Classe permettant de g�rer la connexion � la base de donn�es
    
    // M�thode statique pour se connecter � la base de donn�es
    public static Connection connectDb(){
        
        try{
            // Charger le driver MySQL n�cessaire pour se connecter � la base de donn�es
            // Cette ligne permet de charger la classe n�cessaire pour utiliser MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // �tablir la connexion � la base de donn�es
            // Utilisation de DriverManager pour obtenir une connexion � la base de donn�es 'servicehubdata'
            // L'URL JDBC indique le chemin de la base de donn�es (localhost), le nom de la base (servicehubdata),
            // le nom d'utilisateur (root) et le mot de passe (ici, vide)
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/servicehubdata", "root", "");
            
            // Si la connexion est �tablie avec succ�s, afficher un message dans la console
            System.out.println("Connexion �tablie avec succ�s � la base de donn�es servicehubdata.");
            
            // Retourner l'objet Connection pour pouvoir l'utiliser dans d'autres parties du programme
            return connect;
        }catch(Exception e){ // Si une exception se produit (ex: �chec de la connexion)
            e.printStackTrace(); // Afficher l'exception dans la console pour d�boguer
        }
        return null; // Retourner null si la connexion �choue
    }
}
