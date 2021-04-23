package com.bank.sys.screens.MoneyManagerWindow;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.bank.sys.MainController;
import com.bank.sys.utils.AlertHelper;
import com.bank.sys.utils.GenericWindowController;
import com.bank.sys.utils.NotEnoughMoneyException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MoneyManagerWindowController extends GenericWindowController {
    public final static URL formPath = MoneyManagerWindowController.class.getResource("money_manager_window_form.fxml");

    @FXML
    private Label fieldLabel;

    @FXML
    private TextField amountField;

    @FXML
    private Label titleLabel; 

    private MainController parent;

    protected String type = null;

    protected String userId = null;

    public MoneyManagerWindowController(MainController parent) {
        this.parent = parent;
    }

    public void handleSubmit(ActionEvent event) {
        try {
            if(type.equals("deposit")) {
                parent.dbService.depositMoney(userId, Double.parseDouble(amountField.getText()));
            }
            else if(type.equals("withdraw")) {
                parent.dbService.withdrawMoney(userId, Double.parseDouble(amountField.getText()));
            }
            AlertHelper.showAlert(AlertType.INFORMATION, amountField.getScene().getWindow(), "Operacja udana",
            "Operacja została wykonana pomyślnie :)");
            backAction();
        }
        catch(NotEnoughMoneyException e) {
            System.out.println(e);
            AlertHelper.showAlert(AlertType.ERROR, amountField.getScene().getWindow(), "Błąd przy " + (type.equals("deposit") ? "wpłacie" : "wypłacie"),
                    "Użytkownik nie ma wystarczającej ilości pieniędzy :(");
        }  
        catch(NumberFormatException e) {
            System.out.println("Nan " + e);
            AlertHelper.showAlert(AlertType.ERROR, amountField.getScene().getWindow(), "Błąd przy " + (type.equals("deposit") ? "wpłacie" : "wypłacie"),
                    "Podana kwota nie jest liczbą :(");
        }
        catch(Exception e) {
            System.out.println("Something went wrong " + e);
            AlertHelper.showAlert(AlertType.ERROR, amountField.getScene().getWindow(), "Błąd przy " + (type.equals("deposit") ? "wpłacie" : "wypłacie"),
                    "Nieznany błąd :(");
        }
    }

    public void handleBack(ActionEvent event) {
        backAction();
    }

    private void backAction() {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        parent.navigation.naviageTo("/details", params);
    }

    @Override
    public void onMount(Map<String, String> params) {
        if(params != null) {
            if(params.containsKey("userId")) {
                userId = params.get("userId");
            }

            if(params.containsKey("type")) {
                if(params.get("type").equals("deposit")) {
                    titleLabel.setText("Wpłać pieniądze");
                    fieldLabel.setText("Wpłać");
                    type = "deposit";
                }
                else if (params.get("type").equals("withdraw")) {
                    titleLabel.setText("Wypłać pieniądze");
                    fieldLabel.setText("Wypłać");
                    type = "withdraw";
                }
            }
        }
    }

    @Override
    public void cleanupOnExit() {
        amountField.setText("");
    }
}
