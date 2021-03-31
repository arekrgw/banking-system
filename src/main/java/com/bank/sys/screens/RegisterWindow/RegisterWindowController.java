package com.bank.sys.screens.RegisterWindow;

import com.bank.sys.MainController;

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
        System.out.println("REGISTERING USER: ");
        System.out.println(nameField.getText());
        System.out.println(surnameField.getText());
        System.out.println(peselField.getText());
        System.out.println(adresField.getText());
    }

    public void handleCancelButton(ActionEvent event) {
        parent.naviageTo("/");
    }


}
