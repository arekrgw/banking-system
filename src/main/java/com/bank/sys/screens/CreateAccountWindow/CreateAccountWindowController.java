package com.bank.sys.screens.CreateAccountWindow;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

public class CreateAccountWindowController extends GenericWindowController {

    public final static URL formPath = CreateAccountWindowController.class.getResource("create_window_form.fxml");

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField peselField;

    @FXML
    private TextField adresField;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button cancelButton;

    private MainController parent;

    public CreateAccountWindowController(MainController parent) {
        this.parent = parent;
    }

    public void handleCreateAccountButton(ActionEvent event) {
        try {
            User newUser = new User(nameField.getText(), surnameField.getText(), peselField.getText(),
                    adresField.getText());
            parent.dbService.insertUser(newUser);
            System.out.println("CREATED USER: ");
            System.out.println(newUser);
         
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("userId", newUser.getId().toString());
            parent.navigation.naviageTo("/details", paramsMap);
        } catch (MongoException e) {
            System.out.println("[DB_ERROR] " + e);
            AlertHelper.showAlert(AlertType.ERROR, createAccountButton.getScene().getWindow(), "Błąd bazy danych",
                    "Wystąpił błąd podczas komunikacji z bazą danych :(");
        }

    }

    public void handleCancelButton(ActionEvent event) {
        parent.navigation.naviageTo("/");
    }

    @Override
    public void cleanupOnExit() {
        nameField.setText("");
        surnameField.setText("");
        peselField.setText("");
        adresField.setText("");
    }
}
