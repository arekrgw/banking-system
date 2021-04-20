package com.bank.sys.screens.TransferWindow;

import java.net.URL;

import com.bank.sys.MainController;
import com.bank.sys.utils.GenericWindowController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TransferWindowController extends GenericWindowController {
    public final static URL formPath = TransferWindowController.class.getResource("transfer_window_form.fxml");

    MainController parent;

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private TextField amountField;


    public void pasteFrom(ActionEvent event) {

    }
    
    public void pasteTo(ActionEvent event) {
        
    }

    public void switchSides(ActionEvent event) {
        
    }

    public void handleTransferSubmit(ActionEvent event) {

    }

    public void handleBack(ActionEvent event) {
        parent.navigation.naviageTo("/");
    }

    public TransferWindowController(MainController parent) {
        this.parent = parent;
    }


}
