package com.bank.sys.screens.MainWindow;

import com.bank.sys.MainController;
import com.bank.sys.interfaces.CleanupOnExit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainWindowController implements CleanupOnExit {

    public MainController parent;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
    
    public MainWindowController(MainController parent) {
        this.parent = parent;
    }

    public void handleLoginButton(ActionEvent event) {
        System.out.println("Login!");
        parent.naviageTo("/login");
    }

    public void handleRegisterButton(ActionEvent event) {
        System.out.println("Register!");
        parent.naviageTo("/register");
    }
    

    @Override
    public void cleanupOnExit() {}
}
