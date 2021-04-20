package com.bank.sys.screens.MainWindow;

import java.net.URL;

import com.bank.sys.MainController;
import com.bank.sys.utils.GenericWindowController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainWindowController extends GenericWindowController {

    public final static URL formPath = MainWindowController.class.getResource("main_window_form.fxml");

    public MainController parent;

    @FXML
    private Button createAccount;

    @FXML
    private Button searchButton;

    
    public MainWindowController(MainController parent) {
        this.parent = parent;
    }

    public void handleCreateAccount(ActionEvent event) {
        System.out.println("Create!");
        parent.navigation.naviageTo("/create");
    }

    public void handleSearch(ActionEvent event) {
        parent.navigation.naviageTo("/search");
    }
    
    public void handleTransfer(ActionEvent event) {
        parent.navigation.naviageTo("/transfer");
    }

    @Override
    public void cleanupOnExit() {}
}
