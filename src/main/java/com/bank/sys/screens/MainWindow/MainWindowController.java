package com.bank.sys.screens.MainWindow;

import com.bank.sys.MainController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainWindowController {

    public MainController parent;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
    
    public MainWindowController(MainController parent) {
        this.parent = parent;
    }

    public MainWindowController() {
       
    }


    public void handleLoginButton(ActionEvent event) {
        System.out.println("Login!");
    }

    public void handleRegisterButton(ActionEvent event) {
        System.out.println("Register!");
        parent.naviageTo("/register");
    }
    
}
