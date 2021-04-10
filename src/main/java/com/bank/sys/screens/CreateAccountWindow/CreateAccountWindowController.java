package com.bank.sys.screens.CreateAccountWindow;

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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CreateAccountWindowController extends GenericWindowController {

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
            // ClipboardContent id = new ClipboardContent();
            // id.putString(newUser.getId().toString());
            // Clipboard.getSystemClipboard().setContent(id);
            // AlertHelper.showAlert(AlertType.INFORMATION,
            // createAccountButton.getScene().getWindow(),
            // "Udało się założyć konto", "Twój indentyfikator użytkownika to: " +
            // newUser.getId()
            // + ". Nim będziesz się logować do systemu. Został skopiowany do schowka!");
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("userId", newUser.getId().toString());
            parent.naviageTo("/details", paramsMap);
        } catch (MongoException e) {
            System.out.println("[DB_ERROR] " + e);
            AlertHelper.showAlert(AlertType.ERROR, createAccountButton.getScene().getWindow(), "Błąd bazy danych",
                    "Wystąpił błąd podczas komunikacji z bazą danych :(");
        }

    }

    public void handleCancelButton(ActionEvent event) {
        parent.naviageTo("/");
    }

    @Override
    public void cleanupOnExit() {
        nameField.setText("");
        surnameField.setText("");
        peselField.setText("");
        adresField.setText("");
    }
}
