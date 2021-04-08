package com.bank.sys.screens.LoginWindow;

import com.bank.sys.MainController;
import com.bank.sys.models.User;
import com.bank.sys.utils.AlertHelper;
import com.bank.sys.utils.GenericWindowController;
import com.mongodb.MongoException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginWindowController extends GenericWindowController {

    @FXML
    private TextField idField;

    @FXML 
    private Button loginButton;

    private MainController parent;

    public LoginWindowController(MainController parent) {
        this.parent = parent;
    }

    public void handleLoginButton(ActionEvent event) {
        try {
            System.out.println("Trying to login");
            User foundUser = parent.dbService.findUser(idField.getText());
            
            if(foundUser == null) {
                AlertHelper.showAlert(AlertType.ERROR, loginButton.getScene().getWindow(), "Nie znaleziono",
                    "Nie znaleziono użytkownika z takim identyfikatorem");
                return;
            }

            System.out.println("Login successful");
            System.out.println(foundUser);

        }catch(MongoException e) {
            System.out.println("[DB_ERROR] " + e);
            AlertHelper.showAlert(AlertType.ERROR, loginButton.getScene().getWindow(), "Błąd bazy danych",
                    "Wystąpił błąd podczas komunikacji z bazą danych :(");
        }
    }

    public void handleCancelButton(ActionEvent event) {
        cleanupOnExit();
        parent.naviageTo("/");
    }

    @Override
    public void cleanupOnExit() {
        idField.setText("");
    }
    
}
