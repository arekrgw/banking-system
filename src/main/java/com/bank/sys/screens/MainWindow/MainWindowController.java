package com.bank.sys.screens.MainWindow;

import com.bank.sys.MainController;
import com.bank.sys.utils.GenericWindowController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainWindowController extends GenericWindowController {

    public MainController parent;

    @FXML
    private Button createAccount;

    
    public MainWindowController(MainController parent) {
        this.parent = parent;
    }

    public void handleCreateAccount(ActionEvent event) {
        System.out.println("Create!");
        parent.naviageTo("/create");
    }

    public void handleRegisterButton(ActionEvent event) {
        System.out.println("Register!");
        parent.naviageTo("/register");
    }
    

    @Override
    public void cleanupOnExit() {}
}
