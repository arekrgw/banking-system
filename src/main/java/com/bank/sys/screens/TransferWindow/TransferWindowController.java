package com.bank.sys.screens.TransferWindow;

import java.net.URL;
import java.util.Map;

import com.bank.sys.MainController;
import com.bank.sys.utils.AlertHelper;
import com.bank.sys.utils.GenericWindowController;
import com.bank.sys.utils.NotEnoughMoneyException;
import com.bank.sys.utils.UserNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
        // TODO
    }
    
    public void pasteTo(ActionEvent event) {
        // TODO
    }

    public void switchSides(ActionEvent event) {
        String temp = toField.getText();
        toField.setText(fromField.getText());
        fromField.setText(temp);
    }

    public void handleTransferSubmit(ActionEvent event) {
        try {
            parent.dbService.transferMoney(fromField.getText(), toField.getText(), Double.parseDouble(amountField.getText()));
            AlertHelper.showAlert(AlertType.INFORMATION, fromField.getScene().getWindow(), "Operacja udana",
                    "Przelew został wykonany :)");
            cleanupOnExit();
        }
        catch(NotEnoughMoneyException e) {
            System.out.println(e);
            AlertHelper.showAlert(AlertType.ERROR, fromField.getScene().getWindow(), "Błąd przy przelewie",
                    "Użytkownik nie ma wystarczającej ilości pieniędzy :(");
        }
        catch(UserNotFoundException e) {
            System.out.println(e);
            AlertHelper.showAlert(AlertType.ERROR, fromField.getScene().getWindow(), "Błąd przy przelewie",
                    "Jeden z użytkowników nie istnieje :(");
        }
        catch(Exception e) {
            System.out.println("Something went wrong " + e);
            AlertHelper.showAlert(AlertType.ERROR, fromField.getScene().getWindow(), "Błąd przy przelewie",
                    "Nieznany błąd :(");
        }
    }

    public void handleBack(ActionEvent event) {
        parent.navigation.naviageTo("/");
    }

    public TransferWindowController(MainController parent) {
        this.parent = parent;
    }

    @Override
    public void cleanupOnExit() {
        fromField.setText("");
        toField.setText("");
        amountField.setText("");
    }

    @Override
    public void onMount(Map<String, String> params) {
        if(params != null) {
            if(params.containsKey("userId")){
                fromField.setText(params.get("userId"));
            }
        }
    }

}
