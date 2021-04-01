package com.bank.sys.screens.RegisterWindow;

import com.bank.sys.MainController;
import com.bank.sys.models.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterWindowController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField peselField;

    @FXML
    private TextField adresField;

    @FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    private MainController parent;

    public RegisterWindowController(MainController parent) {
        this.parent = parent;
    }

    public void handleRegisterButton(ActionEvent event) {
        User newUser = new User(nameField.getText(), surnameField.getText(), peselField.getText(), adresField.getText());
        parent.dbService.insertUser(newUser);
        System.out.println("CREATED USER: ");
        System.out.println(newUser);
        parent.naviageTo("/");
    }

    public void handleCancelButton(ActionEvent event) {
        parent.naviageTo("/");
    }


}
