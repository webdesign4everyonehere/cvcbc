package application;

import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class dashboardController  implements Initializable{


	@FXML
	private Label dashboardLabel;

	@FXML
	private Label clientEntryLabel;

	@FXML
	private Label specialistEntryLabel;

	@FXML
	private Label createServiceLabel;


    @FXML
    private Button addClient_addBtn;

    @FXML
    private Button addClient_btn;

    @FXML
    private Button addClient_clearBtn;

    @FXML
    private TextField addClient_clientNum;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_client;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_description;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_email;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_firstName;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_gender;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_lastName;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_registrationDate;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_service;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_tel;

    @FXML
    private Button addClient_deleteBtn;

    @FXML
    private TextField addClient_email;

    @FXML
    private TextField addClient_firstName;

    @FXML
    private ComboBox<String> addClient_gender;

    @FXML
    private TextField addClient_lastName;

    @FXML
    private DatePicker addClient_registirationDate;

    @FXML
    private TextField addClient_search;

    @FXML
    private ComboBox<?> addClient_service;

    @FXML
    private TableView<clientDatabase> addClient_tableView;

    @FXML
    private TextField addClient_tel;

    @FXML
    private Button addClient_updateBtn;
    @FXML
    private TextField addClient_description;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totaleClient;

    @FXML
    private BarChart<String, Number> home_totaleUsers_chart;

    @FXML
    private LineChart<String, Number> home_totaleSpecialist_chart;

    @FXML
    private LineChart<String, Number> home_totaleClients_chart;


    @FXML
    private Label home_totaleSpecialists;

    @FXML
    private Label home_totaleUsers;

   

    @FXML
    private AnchorPane main_form;


    @FXML
    private Button service_addBtn;

    @FXML
    private Button service_btn;

    @FXML
    private Button service_clearBtn;

    @FXML
    private TableColumn<serviceDatabase, String> service_col_description;

    @FXML
    private TableColumn<serviceDatabase, String> service_col_service;

    @FXML
    private Button service_deleteBtn;

    @FXML
    private TextField service_description;

    @FXML
    private AnchorPane service_form;

    @FXML
    private TextField service_service;

    @FXML
    private TableView<serviceDatabase> service_tableView;

    @FXML
    private Button service_updateBtn;
    
    @FXML
    private AnchorPane addClient_form;
    
    @FXML
    private AnchorPane idk_form;
    
    @FXML
    private Button idk_btn;
    

    @FXML
    private Button addSpec_Add;

    @FXML
    private TextField addSpec_CIN;

    @FXML
    private Button addSpec_Clear;

    @FXML
    private Button addSpec_Delete;

    @FXML
    private TextField addSpec_FirstName;

    @FXML
    private TextField addSpec_LastName;

    @FXML
    private TextField addSpec_SpecialistNum;

    @FXML
    private Button addSpec_Update;
    
    @FXML
    private ComboBox<String> addSpec_service;

    @FXML
    private TableColumn<specialistDatabase, String> addSpec_col_CIN;

    @FXML
    private TableColumn<specialistDatabase, String> addSpec_col_FirstName;

    @FXML
    private TableColumn<specialistDatabase, String> addSpec_col_LastName;

    @FXML
    private TableColumn<specialistDatabase, String> addSpec_col_Service;

    @FXML
    private TableColumn<specialistDatabase, String> addSpec_col_SpecialistNum;
    
   
    
    @FXML
    private TableView<specialistDatabase> addSpec_tableView;
    
    @FXML
    private Button logout;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private void animateLabel(Label label) {
        // Création d'une animation de fondu (Fade)
        FadeTransition fade = new FadeTransition(Duration.seconds(2), label);
        fade.setFromValue(0.3);  // Définir l'opacité de départ à 30% (transparent)
        fade.setToValue(1.0);    // Définir l'opacité de fin à 100% (totalement opaque)
        fade.setCycleCount(FadeTransition.INDEFINITE);  // L'animation de fondu va se répéter indéfiniment
        fade.setAutoReverse(true);  // L'animation va inverser la direction après chaque cycle (créant un effet de "clignotement")

        // Création d'une animation de translation (Translation)
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), label);
        translate.setByY(10);  // Le label se déplacera de 10 pixels vers le bas sur l'axe Y
        translate.setCycleCount(TranslateTransition.INDEFINITE);  // L'animation de translation va se répéter indéfiniment
        translate.setAutoReverse(true);  // L'animation va inverser la direction après chaque cycle (le label remontera après être descendu)

        // Combinaison des deux animations (fade et translate) pour les exécuter l'une après l'autre
        SequentialTransition combined = new SequentialTransition(fade, translate);
        combined.setCycleCount(SequentialTransition.INDEFINITE);  // L'animation combinée se répétera indéfiniment
        combined.play();  // Démarre l'animation
    }


    
    public void updateCharts() {
        // Établir la connexion à la base de données
        Connection connect = database.connectDb();  

        // Vérifier si la connexion a échoué
        if (connect == null) {
            System.err.println("La connexion à la base de données n'a pas pu être établie.");
            return;
        }

        // Déclaration des objets nécessaires pour les requêtes SQL
        PreparedStatement userStmt = null;
        ResultSet userResult = null;
        PreparedStatement specialistStmt = null;
        ResultSet specialistResult = null;
        PreparedStatement clientStmt = null;
        ResultSet clientResult = null;

        try {
            // 1. Obtenir le nombre total d'utilisateurs (spécialistes et clients)
            String userQuery = "SELECT (SELECT COUNT(*) FROM specialist) + (SELECT COUNT(*) FROM client) AS totalUsers";
            userStmt = connect.prepareStatement(userQuery);
            userResult = userStmt.executeQuery();
            int totalUsers = 0;
            if (userResult.next()) {
                totalUsers = userResult.getInt("totalUsers");  // Récupérer le total des utilisateurs
            }

            // Débogage : Afficher le total des utilisateurs
            System.out.println("Total des utilisateurs : " + totalUsers);

            // Mettre à jour le graphique des utilisateurs
            XYChart.Series<String, Number> userSeries = new XYChart.Series<>();
            userSeries.setName("Total des utilisateurs");
            userSeries.getData().add(new XYChart.Data<>("Utilisateurs", totalUsers));
            home_totaleUsers_chart.getData().clear();  // Effacer les anciennes données
            home_totaleUsers_chart.getData().add(userSeries);  // Ajouter les nouvelles données
            home_totaleUsers_chart.requestLayout();  // Demander la mise à jour du graphique

            // 2. Obtenir le nombre total de spécialistes
            String specialistQuery = "SELECT COUNT(*) AS totalSpecialists FROM specialist";
            specialistStmt = connect.prepareStatement(specialistQuery);
            specialistResult = specialistStmt.executeQuery();
            int totalSpecialists = 0;
            if (specialistResult.next()) {
                totalSpecialists = specialistResult.getInt("totalSpecialists");  // Récupérer le nombre de spécialistes
            }

            // Débogage : Afficher le total des spécialistes
            System.out.println("Total des spécialistes : " + totalSpecialists);

            // Mettre à jour le graphique des spécialistes
            XYChart.Series<String, Number> specialistSeries = new XYChart.Series<>();
            specialistSeries.setName("Total des spécialistes");
            specialistSeries.getData().add(new XYChart.Data<>("Spécialistes", totalSpecialists));
            home_totaleSpecialist_chart.getData().clear();  // Effacer les anciennes données
            home_totaleSpecialist_chart.getData().add(specialistSeries);  // Ajouter les nouvelles données
            home_totaleSpecialist_chart.requestLayout();  // Demander la mise à jour du graphique

            // 3. Obtenir le nombre total de clients
            String clientQuery = "SELECT COUNT(*) AS totalClients FROM client";
            clientStmt = connect.prepareStatement(clientQuery);
            clientResult = clientStmt.executeQuery();
            int totalClients = 0;
            if (clientResult.next()) {
                totalClients = clientResult.getInt("totalClients");  // Récupérer le nombre de clients
            }

            // Débogage : Afficher le total des clients
            System.out.println("Total des clients : " + totalClients);

            // Mettre à jour le graphique des clients
            XYChart.Series<String, Number> clientSeries = new XYChart.Series<>();
            clientSeries.setName("Total des clients");
            clientSeries.getData().add(new XYChart.Data<>("Clients", totalClients));
            home_totaleClients_chart.getData().clear();  // Effacer les anciennes données
            home_totaleClients_chart.getData().add(clientSeries);  // Ajouter les nouvelles données
            home_totaleClients_chart.requestLayout();  // Demander la mise à jour du graphique

        } catch (Exception e) {
            e.printStackTrace();  // Si une exception survient, l'afficher dans la console
        } finally {
            // Fermer les ressources de la base de données (PreparedStatement, ResultSet, etc.)
            try {
                if (userResult != null) userResult.close();
                if (userStmt != null) userStmt.close();
                if (specialistResult != null) specialistResult.close();
                if (specialistStmt != null) specialistStmt.close();
                if (clientResult != null) clientResult.close();
                if (clientStmt != null) clientStmt.close();
                if (connect != null) connect.close();  // Fermer la connexion à la base de données
            } catch (SQLException e) {
                e.printStackTrace();  // Si une exception survient lors de la fermeture, l'afficher dans la console
            }
        }
    }


  
 // Fonction pour afficher le nombre total d'utilisateurs (clients + spécialistes)
    public void homeDisplayTotalUsers() {
        // Requête SQL pour compter le nombre total d'utilisateurs (clients et spécialistes)
        String sql = "SELECT COUNT(*) AS total_users " +
                     "FROM ( " +
                     "    SELECT id FROM client " +  // Sélectionner les clients
                     "    UNION ALL " +  // Union des résultats des deux tables (clients et spécialistes)
                     "    SELECT id FROM specialist " +  // Sélectionner les spécialistes
                     ") AS combined";  // Créer une table combinée

        connect = database.connectDb();  // Se connecter à la base de données
        int countUsers = 0;  // Initialiser la variable pour stocker le nombre d'utilisateurs

        try {
            prepare = connect.prepareStatement(sql);  // Préparer la requête SQL
            result = prepare.executeQuery();  // Exécuter la requête et obtenir le résultat

            if (result.next()) {
                // Récupérer le total des utilisateurs à partir du champ 'total_users'
                countUsers = result.getInt("total_users");
            }

            // Afficher le nombre total d'utilisateurs sur l'élément UI (label ou champ texte)
            home_totaleUsers.setText(String.valueOf(countUsers));
        } catch (Exception e) {
            e.printStackTrace();  // En cas d'erreur, afficher la pile d'exceptions
        } finally {
            // Fermer les ressources (ResultSet, PreparedStatement, et connexion) dans le bloc finally
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();  // En cas d'erreur lors de la fermeture, afficher la pile d'exceptions
            }
        }
    }


    // Fonction pour afficher le nombre total de clients
    public void homeDisplayTotalClients() {
        // Requête SQL pour compter le nombre total de clients
        String sql = "SELECT COUNT(id) FROM client";  // Compter tous les clients dans la table 'client'
        
        connect = database.connectDb();  // Se connecter à la base de données
        int countUsers = 0;  // Initialiser la variable pour stocker le nombre de clients
        
        try {
            prepare = connect.prepareStatement(sql);  // Préparer la requête SQL
            result = prepare.executeQuery();  // Exécuter la requête et obtenir le résultat
            
            if (result.next()) {
                // Récupérer le nombre de clients à partir du champ 'COUNT(id)'
                countUsers = result.getInt("COUNT(id)");
            }
            
            // Afficher le nombre total de clients sur l'élément UI (label ou champ texte)
            home_totaleClient.setText(String.valueOf(countUsers));
        } catch (Exception e) {
            e.printStackTrace();  // En cas d'erreur, afficher la pile d'exceptions
        }
    }


    // Fonction pour afficher le nombre total de spécialistes
    public void homeDisplayTotalSpecialists() {
        // Requête SQL pour compter le nombre total de spécialistes
        String sql = "SELECT COUNT(id) FROM specialist";  // Compter tous les spécialistes dans la table 'specialist'
        
        connect = database.connectDb();  // Se connecter à la base de données
        int countUsers = 0;  // Initialiser la variable pour stocker le nombre de spécialistes
        
        try {
            prepare = connect.prepareStatement(sql);  // Préparer la requête SQL
            result = prepare.executeQuery();  // Exécuter la requête et obtenir le résultat
            
            if (result.next()) {
                // Récupérer le nombre de spécialistes à partir du champ 'COUNT(id)'
                countUsers = result.getInt("COUNT(id)");
            }
            
            // Afficher le nombre total de spécialistes sur l'élément UI (label ou champ texte)
            home_totaleSpecialists.setText(String.valueOf(countUsers));
        } catch (Exception e) {
            e.printStackTrace();  // En cas d'erreur, afficher la pile d'exceptions
        }
    }
  
 // Méthode qui retourne une liste observable de clients à partir de la base de données
    public ObservableList<clientDatabase> addClinetListData() {

        // Création d'une liste observable pour contenir les objets clientDatabase
        ObservableList<clientDatabase> listClient = FXCollections.observableArrayList();

        // Requête SQL pour sélectionner toutes les données de la table 'client'
        String sql = "SELECT * FROM client";

        // Connexion à la base de données
        connect = database.connectDb();

        try {
            clientDatabase clientD;

            // Préparation de la requête SQL
            prepare = connect.prepareStatement(sql);

            // Exécution de la requête et récupération du résultat
            result = prepare.executeQuery();

            // Parcours des résultats de la requête
            while(result.next()) {
                // Création d'un objet clientDatabase pour chaque ligne du résultat
                clientD = new clientDatabase(
                    result.getString("clientNum"),
                    result.getString("firstName"),
                    result.getString("lastName"),
                    result.getString("gender"),
                    result.getString("email"),
                    result.getString("tel"),
                    result.getString("service"),
                    result.getString("description"),
                    result.getDate("registration_date") // Conversion de la date au format Java
                );

                // Ajout de l'objet client à la liste observable
                listClient.add(clientD);
            }

        } catch(Exception e) {
            // Gestion des erreurs en cas d'échec de la requête
            e.printStackTrace();
        }

        // Retourne la liste observable des clients
        return listClient;
    }

    
 // Déclaration de la liste observable pour contenir les clients
    private ObservableList<clientDatabase> addClinetListD;

    // Méthode pour afficher les données des clients dans la TableView
    public void addClientShowListData() {

        // Récupère la liste observable de clients à partir de la base de données
        addClinetListD = addClinetListData();
        
        // Configure chaque colonne de la TableView pour afficher les propriétés correspondantes des objets clientDatabase
        addClient_col_client.setCellValueFactory(new PropertyValueFactory<>("clientNum")); // Affiche le numéro du client
        addClient_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName")); // Affiche le prénom du client
        addClient_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName")); // Affiche le nom de famille du client
        addClient_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender")); // Affiche le sexe du client
        addClient_col_email.setCellValueFactory(new PropertyValueFactory<>("email")); // Affiche l'email du client
        addClient_col_tel.setCellValueFactory(new PropertyValueFactory<>("tel")); // Affiche le numéro de téléphone du client
        addClient_col_service.setCellValueFactory(new PropertyValueFactory<>("service")); // Affiche le service demandé par le client
        addClient_col_description.setCellValueFactory(new PropertyValueFactory<>("description")); // Affiche la description du service
        addClient_col_registrationDate.setCellValueFactory(new PropertyValueFactory<>("registration_date")); // Affiche la date d'inscription du client

        // Associe la liste observable des clients à la TableView pour l'affichage
        addClient_tableView.setItems(addClinetListD);
    }


 // Méthode pour sélectionner un client dans la TableView et afficher ses informations dans les champs correspondants
    public void addClientSelect() {
        // Récupère l'élément sélectionné dans la TableView, qui est un objet clientDatabase
        clientDatabase clientD = addClient_tableView.getSelectionModel().getSelectedItem();
        
        // Récupère l'index de l'élément sélectionné dans la TableView
        int num = addClient_tableView.getSelectionModel().getSelectedIndex();

        // Vérifie si un élément est sélectionné (si l'index est valide, c'est-à-dire supérieur à -1)
        if ((num - 1) < -1) {
            return; // Si aucun client n'est sélectionné, quitte la méthode
        }

        // Remplir les champs avec les informations du client sélectionné
        addClient_clientNum.setText(clientD.getClientNum()); // Affiche le numéro du client
        addClient_firstName.setText(clientD.getFirstName()); // Affiche le prénom du client
        addClient_lastName.setText(clientD.getLastName()); // Affiche le nom de famille du client
        addClient_email.setText(clientD.getEmail()); // Affiche l'email du client
        addClient_tel.setText(clientD.getTel()); // Affiche le numéro de téléphone du client
        addClient_description.setText(clientD.getDescription()); // Remplie le champ de description avec les informations du client
        addClient_registirationDate.setValue(clientD.getRegistration_Date().toLocalDate()); // Remplie la date d'inscription avec la date du client
        addClient_gender.getSelectionModel().select(clientD.getGender()); // Sélectionne le sexe du client dans la liste déroulante (assume que le genre est une chaîne de caractères)
    }

    
 // Méthode pour ajouter un client dans la base de données après validation des champs
    public void addClientAdd() {
        // La requête SQL pour insérer un nouveau client dans la table 'client'
        String insertData = "INSERT INTO client (clientNum, firstName, lastName, gender, email, tel, service, description, registration_date, date)" 
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Connexion à la base de données
        connect = database.connectDb();

        try {
            Alert alert;
            
            // Validation des champs de formulaire pour s'assurer qu'aucun champ n'est vide
            if (addClient_clientNum.getText().isEmpty() 
                    || addClient_service.getSelectionModel().getSelectedItem() == null
                    || addClient_firstName.getText().isEmpty() 
                    || addClient_lastName.getText().isEmpty()
                    || addClient_gender.getSelectionModel().getSelectedItem() == null
                    || addClient_email.getText().isEmpty()
                    || addClient_tel.getText().isEmpty()
                    || addClient_description.getText().isEmpty() // Validation du champ description
                    || addClient_registirationDate.getValue() == null) {
                
                // Si un champ est vide, affiche une alerte d'erreur
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields.");
                alert.showAndWait();
            } else {
                // Vérifie si le numéro du client existe déjà dans la base de données
                String checkData = "SELECT clientNum FROM client WHERE clientNum = '" + addClient_clientNum.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                // Si le numéro de client existe déjà, affiche une alerte d'erreur
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Client #" + addClient_clientNum.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                    // Prépare la requête SQL pour l'insertion du nouveau client
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addClient_clientNum.getText()); // Numéro du client
                    prepare.setString(2, addClient_firstName.getText()); // Prénom du client
                    prepare.setString(3, addClient_lastName.getText()); // Nom du client
                    prepare.setString(4, (String) addClient_gender.getSelectionModel().getSelectedItem()); // Sexe du client
                    prepare.setString(5, addClient_email.getText()); // Email du client
                    prepare.setString(6, addClient_tel.getText()); // Téléphone du client
                    prepare.setString(7, (String) addClient_service.getSelectionModel().getSelectedItem()); // Service choisi
                    prepare.setString(8, addClient_description.getText()); // Description du client
                    prepare.setString(9, String.valueOf(addClient_registirationDate.getValue())); // Date d'inscription

                    // Définit la date actuelle comme la date d'enregistrement
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setDate(10, sqlDate);

                    // Exécute la requête pour insérer les données dans la base
                    prepare.executeUpdate();

                    // Affiche une alerte de succès
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // Rafraîchit la liste des clients affichée
                    addClientShowListData();
                    // Vide les champs de saisie après l'ajout du client
                    addClientClear();
                }
            }
        } catch (Exception e) {
            // En cas d'erreur, affiche la trace de l'exception pour le débogage
            e.printStackTrace();
        }
    }


 // Méthode pour mettre à jour les informations d'un client dans la base de données
    public void addClientUpdate() {
        // La requête SQL pour mettre à jour les informations d'un client spécifique
        String updateData = "UPDATE client SET firstName = ?, lastName = ?, gender = ?, email = ?, tel = ?, service = ?, description = ?, registration_date = ? WHERE clientNum = ?";

        // Connexion à la base de données
        connect = database.connectDb();

        try {
            Alert alert;
            
            // Validation des champs de saisie pour s'assurer qu'aucun champ obligatoire n'est vide
            if (addClient_clientNum.getText().isEmpty() 
                    || addClient_service.getSelectionModel().getSelectedItem() == null
                    || addClient_firstName.getText().isEmpty() 
                    || addClient_lastName.getText().isEmpty()
                    || addClient_gender.getSelectionModel().getSelectedItem() == null
                    || addClient_email.getText().isEmpty()
                    || addClient_tel.getText().isEmpty()
                    || addClient_registirationDate.getValue() == null) {
                
                // Si un champ est vide, affiche une alerte d'erreur
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields.");
                alert.showAndWait();
            } else {
                // Prépare la requête SQL pour la mise à jour des informations du client
                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, addClient_firstName.getText()); // Prénom du client
                prepare.setString(2, addClient_lastName.getText()); // Nom du client
                prepare.setString(3, (String) addClient_gender.getSelectionModel().getSelectedItem()); // Sexe du client
                prepare.setString(4, addClient_email.getText()); // Email du client
                prepare.setString(5, addClient_tel.getText()); // Téléphone du client
                prepare.setString(6, (String) addClient_service.getSelectionModel().getSelectedItem()); // Service choisi
                prepare.setString(7, addClient_description.getText()); // Description mise à jour du client
                prepare.setString(8, String.valueOf(addClient_registirationDate.getValue())); // Date d'inscription mise à jour
                prepare.setString(9, addClient_clientNum.getText()); // Identifiant du client (clientNum) pour la mise à jour

                // Exécute la requête pour mettre à jour les données dans la base
                int rowsUpdated = prepare.executeUpdate();

                // Si des lignes ont été mises à jour, affiche une alerte de succès
                if (rowsUpdated > 0) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Client information successfully updated!");
                    alert.showAndWait();

                    // Rafraîchit la liste des clients affichée
                    addClientShowListData();
                    // Vide les champs de saisie après la mise à jour
                    addClientClear();
                } else {
                    // Si aucune ligne n'a été mise à jour, cela signifie que le client n'existe pas
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No changes were made. Please verify the client number.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            // En cas d'erreur, affiche la trace de l'exception pour faciliter le débogage
            e.printStackTrace();
        }
    }

 // Méthode pour supprimer un client de la base de données après l'avoir archivé
    public void addClientDelete() {
        // Requête SQL pour archiver un client avant de le supprimer
        String archiveQuery = "INSERT INTO clients_archive SELECT *, NOW() FROM client WHERE clientNum = ?";
        
        // Requête SQL pour supprimer un client de la table 'client'
        String deleteQuery = "DELETE FROM client WHERE clientNum = ?";

        // Connexion à la base de données
        connect = database.connectDb();

        try {
            Alert alert;
            
            // Vérifie si le numéro du client est renseigné
            if (addClient_clientNum.getText().isEmpty()) {
                // Si le champ est vide, affiche une alerte d'erreur
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid Client Number to delete.");
                alert.showAndWait();
            } else {
                // Affiche une boîte de confirmation avant de procéder à la suppression
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Client #" + addClient_clientNum.getText() + "?");

                // Attendre la réponse de l'utilisateur (OK ou Annuler)
                Optional<ButtonType> option = alert.showAndWait();

                // Si l'utilisateur confirme la suppression
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    // Prépare la requête d'archivage pour enregistrer les données du client avant la suppression
                    PreparedStatement archiveStmt = connect.prepareStatement(archiveQuery);
                    archiveStmt.setString(1, addClient_clientNum.getText());
                    archiveStmt.executeUpdate(); // Archive les données du client

                    // Prépare la requête de suppression du client
                    PreparedStatement deleteStmt = connect.prepareStatement(deleteQuery);
                    deleteStmt.setString(1, addClient_clientNum.getText());
                    int rowsDeleted = deleteStmt.executeUpdate(); // Exécute la suppression

                    // Si la suppression a réussi (lignes supprimées)
                    if (rowsDeleted > 0) {
                        // Affiche une alerte de succès
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Client successfully deleted!");
                        alert.showAndWait();

                        // Rafraîchit la liste des clients et vide les champs de saisie
                        addClientShowListData();
                        addClientClear();
                    } else {
                        // Si aucune ligne n'a été supprimée, cela signifie que le client n'existe pas
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Client not found. Please verify the Client Number.");
                        alert.showAndWait();
                    }
                }
            }
        } catch (Exception e) {
            // En cas d'exception, affiche la trace pour le débogage
            e.printStackTrace();
        }
    }


 // Réinitialise les champs du formulaire d'ajout de client
    public void addClientClear() {
        addClient_clientNum.setText(""); // Efface le numéro de client
        addClient_service.getSelectionModel().clearSelection(); // Vide la sélection du service
        addClient_firstName.setText(""); // Efface le prénom
        addClient_lastName.setText(""); // Efface le nom de famille
        addClient_gender.getSelectionModel().clearSelection(); // Vide la sélection du genre
        addClient_email.setText(""); // Efface l'email
        addClient_tel.setText(""); // Efface le numéro de téléphone
        addClient_registirationDate.setValue(null); // Réinitialise la date d'inscription
        addClient_description.setText(""); // Efface la description
        getData.path = ""; // Réinitialise le chemin de données
    }

    
    
    public void addClientServiceList() {
        // Requête SQL pour récupérer tous les services
        String listService = "SELECT * FROM service";
        
        // Connexion à la base de données
        connect = database.connectDb();
        
        try {
            // Crée une liste observable pour stocker les services
            ObservableList listS = FXCollections.observableArrayList();
            
            // Prépare la requête SQL
            prepare = connect.prepareStatement(listService);
            result = prepare.executeQuery();
            
            // Ajoute les services récupérés à la liste
            while(result.next()) {
                listS.add(result.getString("service"));
            }
            
            // Remplit le ComboBox avec la liste des services
            addClient_service.setItems(listS);
            
        } catch (Exception e) {
            // Affiche une erreur en cas d'exception
            e.printStackTrace();
        }
    }

    
    
    private String[] genderList = {"Male", "Female"};

    public void addClientGenderList() {
        // Crée une liste temporaire pour stocker les options de genre
        List<String> genderL = new ArrayList<>();

        // Remplir la liste genderL avec les options de genre
        for (String data : genderList) {
            genderL.add(data);
        }

        // Convertit la liste en ObservableList pour l'utiliser dans un ComboBox
        ObservableList<String> obList = FXCollections.observableArrayList(genderL);

        // Définit les éléments dans le ComboBox pour le genre
        addClient_gender.setItems(obList);
    }


    
    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addClient_form.setVisible(false);
            service_form.setVisible(false);
            idk_form.setVisible(false);
            
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            service_btn.setStyle("-fx-background-color:transparent");
            idk_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalUsers();
            homeDisplayTotalClients();
            homeDisplayTotalSpecialists();
            updateCharts();
        }else if (event.getSource() == addClient_btn) {
            home_form.setVisible(false);
            addClient_form.setVisible(true);
            service_form.setVisible(false);
            idk_form.setVisible(false);
            
            addClient_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            service_btn.setStyle("-fx-background-color:transparent");
            idk_btn.setStyle("-fx-background-color:transparent");

            addClientShowListData();
            addClientServiceList();
            addClientGenderList();
           
            
        }else if (event.getSource() == service_btn) {
            home_form.setVisible(false);
            addClient_form.setVisible(false);
            service_form.setVisible(true);
            idk_form.setVisible(false);
            
            service_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            idk_btn.setStyle("-fx-background-color:transparent");
            
            serviceShowListData();

        }else if (event.getSource() == idk_btn) {
            home_form.setVisible(false);
            addClient_form.setVisible(false);
            service_form.setVisible(false);
            idk_form.setVisible(true);
            
            idk_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            service_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

        }
    }
	
    public void close() {
    	System.exit(0);
    }
    
    public void minimize() {
    	Stage stage = (Stage)main_form.getScene().getWindow();
    	stage.setIconified(true);
    }
    
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Ajouter des animations à chaque étiquette (label)
        animateLabel(dashboardLabel);
        animateLabel(clientEntryLabel);
        animateLabel(specialistEntryLabel);
        animateLabel(createServiceLabel);

        // Mettre à jour les graphiques
        updateCharts();

        // Afficher les totaux pour les utilisateurs, clients et spécialistes sur la page d'accueil
        homeDisplayTotalUsers();
        homeDisplayTotalClients();
        homeDisplayTotalSpecialists();

        // Afficher la liste des clients et des genres
        addClientShowListData();
        addClientGenderList();
        addClientServiceList();

        // Lier les colonnes aux propriétés de clientDatabase
        addClient_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        addClient_col_registrationDate.setCellValueFactory(new PropertyValueFactory<>("registration_Date"));

        // S'assurer que le TableView est peuplé avec des données
        addClient_tableView.setItems(addClinetListD);
        
        // Afficher la liste des services et des spécialistes
        serviceShowListData();
        
        addSpecShowListData();
        addSpecialistServiceList();

        // Peupler le TableView des spécialistes avec des données
        addSpec_tableView.setItems(addSpecListD);
    }

	
	public void serviceAdd() {

	    String insertData = "INSERT INTO service (service, description) VALUES(?, ?)";
	    connect = database.connectDb();

	    try {
	        Alert alert;

	        // Vérification si les champs sont vides
	        if (service_service.getText().isEmpty() || service_description.getText().isEmpty()) {
	            alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Error Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Please fill all blank fields");
	            alert.showAndWait();
	        } else {
	            // Requête pour vérifier si le service existe déjà
	            String checkData = "SELECT service FROM service WHERE service = '"
	                + service_service.getText() + "'";

	            statement = connect.createStatement();
	            result = statement.executeQuery(checkData);

	            if (result.next()) {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Service: " + service_service.getText() + " already exists!");
	                alert.showAndWait();
	            } else {
	                // Préparation de l'insertion
	                prepare = connect.prepareStatement(insertData);
	                prepare.setString(1, service_service.getText());
	                prepare.setString(2, service_description.getText());

	                // Exécution de l'insertion
	                prepare.executeUpdate();

	                alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Information Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Successfully Added!");
	                alert.showAndWait();

	                // Mise à jour de la liste des services
	                serviceShowListData();

	                // Nettoyage des champs
	                ServiceClear();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void serviceDelete() {
	    String archiveQuery = "INSERT INTO services_archive SELECT *, NOW() FROM service WHERE service = ?";
	    String deleteQuery = "DELETE FROM service WHERE service = ?";
	    
	    connect = database.connectDb();

	    try {
	        Alert alert;

	        if (service_service.getText().isEmpty() || service_description.getText().isEmpty()) {
	            alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Error Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Please fill all blank fields");
	            alert.showAndWait();
	        } else {
	            alert = new Alert(AlertType.CONFIRMATION);
	            alert.setTitle("Confirmation Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Are you sure you want to DELETE Service: " + service_service.getText() + "?");
	            Optional<ButtonType> option = alert.showAndWait();

	            if (option.get().equals(ButtonType.OK)) {
	                // Archive the service
	                PreparedStatement archiveStmt = connect.prepareStatement(archiveQuery);
	                archiveStmt.setString(1, service_service.getText());
	                archiveStmt.executeUpdate();

	                // Delete the service
	                PreparedStatement deleteStmt = connect.prepareStatement(deleteQuery);
	                deleteStmt.setString(1, service_service.getText());
	                deleteStmt.executeUpdate();

	                alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Information Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Successfully Deleted!");
	                alert.showAndWait();

	                // Update the service list and clear fields
	                serviceShowListData();
	                ServiceClear();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
    public void serviceUpdate() {

        // Requête SQL pour la mise à jour
        String updateData = "UPDATE service SET description = ? WHERE service = ?";

        connect = database.connectDb();

        try {
            Alert alert;

            // Vérification si les champs sont vides
            if (service_service.getText().isEmpty() || service_description.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // Confirmation de la mise à jour
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Service: " + service_service.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    // Préparation de la requête avec paramètres
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, service_description.getText());
                    prepare.setString(2, service_service.getText());

                    // Exécution de la mise à jour
                    prepare.executeUpdate();

                    // Message de succès
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // Mise à jour de la liste des services
                    serviceShowListData();

                    // Nettoyage des champs
                    ServiceClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void ServiceClear() {
		 service_service.setText("");
		 service_description.setText("");
    }
	
	
	public ObservableList<serviceDatabase> serviceListData(){
		
		ObservableList<serviceDatabase> listData = FXCollections.observableArrayList();
		
		String sql = "SELECT * FROM service";
		
		connect = database.connectDb();
		
	    try {
			serviceDatabase serviceD;
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			
			while(result.next()) {
				serviceD = new serviceDatabase(result.getString("service")
						, result.getString("description"));
				
				listData.add(serviceD);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	    return listData;
	}
	
	public ObservableList<serviceDatabase> serviceList;
	public void serviceShowListData() {
		serviceList = serviceListData();
		
		service_col_service.setCellValueFactory(new PropertyValueFactory<>("service"));
		service_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		service_tableView.setItems(serviceList);
	}
	
	public void serviceSelect() {
		serviceDatabase serviceD = service_tableView.getSelectionModel().getSelectedItem();
		int num = service_tableView.getSelectionModel().getSelectedIndex();
		
		if((num - 1) < -1) {
			return;
		}
		
		service_service.setText(serviceD.getService());
		service_description.setText(serviceD.getDescription());
	}
	
	 
	
	 private double x = 0;
	 private double y = 0;
	 
	  public void logout() {

	        try {

	            Alert alert = new Alert(AlertType.CONFIRMATION);
	            alert.setTitle("Confirmation Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Are you sure you want to logout?");

	            Optional<ButtonType> option = alert.showAndWait();

	            if (option.get().equals(ButtonType.OK)) {

	                //HIDE YOUR DASHBOARD FORM
	                logout.getScene().getWindow().hide();

	                //LINK YOUR LOGIN FORM 
	                Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
	                Stage stage = new Stage();
	                Scene scene = new Scene(root);

	                root.setOnMousePressed((MouseEvent event) -> {
	                    x = event.getSceneX();
	                    y = event.getSceneY();
	                });

	                root.setOnMouseDragged((MouseEvent event) -> {
	                    stage.setX(event.getScreenX() - x);
	                    stage.setY(event.getScreenY() - y);

	                    stage.setOpacity(.8);
	                });

	                root.setOnMouseReleased((MouseEvent event) -> {
	                    stage.setOpacity(1);
	                });

	                stage.initStyle(StageStyle.TRANSPARENT);

	                stage.setScene(scene);
	                stage.show();

	            } else {
	                return;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	  public ObservableList<specialistDatabase> addSpecListData() {
		    ObservableList<specialistDatabase> listSpecialist = FXCollections.observableArrayList();
		    
		    String sql = "SELECT * FROM specialist";
		    
		    connect = database.connectDb();
		    
		    try {
		        specialistDatabase specialistD;
		        prepare = connect.prepareStatement(sql);
		        result = prepare.executeQuery();
		        
		        while(result.next()) {
		            specialistD = new specialistDatabase(
		                result.getString("specialistNum"),
		                result.getString("firstName"),
		                result.getString("lastName"),
		                result.getString("cin"),
		                result.getString("service")
		            );
		            
		            listSpecialist.add(specialistD);
		        }
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    
		    return listSpecialist;
		}

	  private ObservableList<specialistDatabase> addSpecListD;
	  public void addSpecShowListData() {
		  addSpecListD = addSpecListData();

		  addSpec_col_SpecialistNum.setCellValueFactory(new PropertyValueFactory<>("specialistNum"));
		  addSpec_col_FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		  addSpec_col_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		  addSpec_col_CIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
		  addSpec_col_Service.setCellValueFactory(new PropertyValueFactory<>("service"));

		  addSpec_tableView.setItems(addSpecListD);

	  }
	  
	  public void addSpecSelect() {
		    specialistDatabase SpecialistD = addSpec_tableView.getSelectionModel().getSelectedItem();
		    int num = addSpec_tableView.getSelectionModel().getSelectedIndex();

		    if ((num - 1) < -1 || SpecialistD == null) {
		        System.out.println("No selection or invalid row.");
		        return;
		    }

		    System.out.println("Selected Specialist: " + SpecialistD.getSpecialistNum());

		    addSpec_SpecialistNum.setText(SpecialistD.getSpecialistNum());
		    addSpec_FirstName.setText(SpecialistD.getFirstName());
		    addSpec_LastName.setText(SpecialistD.getLastName());
		    addSpec_CIN.setText(SpecialistD.getCin());
		}

	  
	  public void addSpecAdd() {
		    String insertData = "INSERT INTO specialist (cin , firstName, lastName, specialistNum, service) VALUES (?, ?, ?, ?, ?)";
		    
		    connect = database.connectDb();
		    
		    try {
		        Alert alert;
		        
		        if (addSpec_SpecialistNum.getText().isEmpty() 
		            || addSpec_FirstName.getText().isEmpty() 
		            || addSpec_LastName.getText().isEmpty() 
		            || addSpec_CIN.getText().isEmpty() 
		            || addSpec_service.getSelectionModel().getSelectedItem() == null) {
		            
		            alert = new Alert(AlertType.ERROR);
		            alert.setTitle("Error Message");
		            alert.setHeaderText(null);
		            alert.setContentText("Please fill all blank fields.");
		            alert.showAndWait();
		        } else {
		            String checkData = "SELECT specialistNum FROM specialist WHERE specialistNum = '" + addSpec_SpecialistNum.getText() + "'";
		            statement = connect.createStatement();
		            result = statement.executeQuery(checkData);
		            
		            if (result.next()) {
		                alert = new Alert(AlertType.ERROR);
		                alert.setTitle("Error Message");
		                alert.setHeaderText(null);
		                alert.setContentText("SpecialistNum" + addSpec_SpecialistNum.getText() + " already exists!");
		                alert.showAndWait();
		            } else {
		                prepare = connect.prepareStatement(insertData);
		                prepare.setString(1, addSpec_SpecialistNum.getText());
		                prepare.setString(2, addSpec_FirstName.getText());
		                prepare.setString(3, addSpec_LastName.getText());
		                prepare.setString(4, addSpec_CIN.getText());
		                prepare.setString(5,(String) addSpec_service.getSelectionModel().getSelectedItem());
		                
		                prepare.executeUpdate();
		                
		                alert = new Alert(AlertType.INFORMATION);
		                alert.setTitle("Information Message");
		                alert.setHeaderText(null);
		                alert.setContentText("Successfully Added!");
		                alert.showAndWait();
		                
		                addSpecShowListData();
		                addSpecClear();
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

	  public void addSpecDelete() {
		    String archiveQuery = "INSERT INTO specialists_archive SELECT *, NOW() FROM specialist WHERE cin = ?";
		    String deleteQuery = "DELETE FROM specialist WHERE cin = ?";

		    connect = database.connectDb();

		    try {
		        Alert alert;
		        String specialistNum = addSpec_SpecialistNum.getText().trim().toUpperCase();

		        if (specialistNum.isEmpty()) {
		            alert = new Alert(AlertType.ERROR);
		            alert.setTitle("Error Message");
		            alert.setHeaderText(null);
		            alert.setContentText("Please enter a valid Specialist Number to delete.");
		            alert.showAndWait();
		        } else {
		            alert = new Alert(AlertType.CONFIRMATION);
		            alert.setTitle("Confirmation Message");
		            alert.setHeaderText(null);
		            alert.setContentText("Are you sure you want to delete Specialist #" + specialistNum + "?");

		            Optional<ButtonType> option = alert.showAndWait();

		            if (option.isPresent() && option.get() == ButtonType.OK) {
		                // Archive the specialist first
		                PreparedStatement archiveStmt = connect.prepareStatement(archiveQuery);
		                archiveStmt.setString(1, specialistNum);
		                archiveStmt.executeUpdate();

		                // Delete the specialist
		                PreparedStatement deleteStmt = connect.prepareStatement(deleteQuery);
		                deleteStmt.setString(1, specialistNum);

		                // Debugging logs
		                System.out.println("Attempting to delete specialist with number: " + specialistNum);

		                int rowsDeleted = deleteStmt.executeUpdate();

		                if (rowsDeleted > 0) {
		                    alert = new Alert(AlertType.INFORMATION);
		                    alert.setTitle("Information Message");
		                    alert.setHeaderText(null);
		                    alert.setContentText("Specialist successfully deleted!");
		                    alert.showAndWait();

		                    // Update the list and clear the input fields
		                    addSpecShowListData();
		                    addSpecClear();
		                } else {
		                    alert = new Alert(AlertType.ERROR);
		                    alert.setTitle("Error Message");
		                    alert.setHeaderText(null);
		                    alert.setContentText("Specialist not found. Please verify the Specialist Number.");
		                    alert.showAndWait();
		                }
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (prepare != null) prepare.close();
		            if (connect != null) connect.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}



	  public void addSpecialisttUpdate() {
	        String updateData = "UPDATE specialist SET firstName = ?, lastName = ?,  service = ? WHERE cin = ?";

	        connect = database.connectDb();

	        try {
	            Alert alert;
	            // Validate fields
	            if (addSpec_SpecialistNum.getText().isEmpty() 
	                    || addSpec_FirstName.getText().isEmpty() 
	                    || addSpec_LastName.getText().isEmpty()
	                    || addSpec_CIN.getText().isEmpty()
	                    || addSpec_service.getSelectionModel().getSelectedItem() == null) {
	                
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields.");
	                alert.showAndWait();
	            } else {
	                // Prepare the SQL update statement
	                prepare = connect.prepareStatement(updateData);
	             // Set the values for the placeholders in the SQL query
	                prepare.setString(1, addSpec_FirstName.getText());
	                prepare.setString(2, addSpec_LastName.getText());
	                prepare.setString(3,(String) addSpec_service.getSelectionModel().getSelectedItem());
	                prepare.setString(4, addSpec_SpecialistNum.getText()); // Update based on specialistNum

	                // Execute the update query
	                int rowsUpdated = prepare.executeUpdate();

	                if (rowsUpdated > 0) {
	                    alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Information Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Specialist information successfully updated!");
	                    alert.showAndWait();

	                    addSpecShowListData();
		                addSpecClear();
	                } else {
	                    alert = new Alert(AlertType.ERROR);
	                    alert.setTitle("Error Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("No changes were made. Please verify the specialist number.");
	                    alert.showAndWait();
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  
	  public void addSpecialistServiceList() {
	    	
	    	String listService = "SELECT * FROM service";
	    	
	    	connect = database.connectDb();
	    	
	    	try {
	    		
	    		ObservableList listS = FXCollections.observableArrayList();
	    		
	    		prepare = connect.prepareStatement(listService);
	    		result = prepare.executeQuery();
	    		
	    		while(result.next()) {
	    			listS.add(result.getString("service"));
	    		}
	    		addSpec_service.setItems(listS);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    }
	    

	  
	  public void addSpecClear() {
		    addSpec_SpecialistNum.setText("");
		    addSpec_FirstName.setText("");
		    addSpec_LastName.setText("");
		    addSpec_CIN.setText("");
		    addSpec_service.getSelectionModel().clearSelection();
		}

	   
	  public void defaultNav() {
		    // Appliquer un dégradé de couleurs de fond sur les boutons de navigation
		    home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
		    addClient_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
		    service_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
		    idk_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
		}

	    
	  
	    
	    
}

	   