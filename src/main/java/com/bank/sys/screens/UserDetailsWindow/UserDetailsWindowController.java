package com.bank.sys.screens.UserDetailsWindow;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.bank.sys.MainController;
import com.bank.sys.models.User;
import com.bank.sys.utils.GenericWindowController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserDetailsWindowController extends GenericWindowController {

    public final static URL formPath = UserDetailsWindowController.class.getResource("user_details_window_form.fxml");

    @FXML
    private Label userId;

    @FXML
    private Label address;

    @FXML
    private Label fullName;

    @FXML
    private Label pesel;

    @FXML
    private Label balance;

    @FXML
    private Button inButton;

    @FXML
    private Button outButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button backButton;

    private MainController parent;
    private User user;

    public UserDetailsWindowController(MainController parent) {
        this.parent = parent;
    }

    private void naviageToManager(String type) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", user.getId().toString());
        params.put("type", type);

        parent.navigation.naviageTo("/manager", params);
    }

    public void handleInButton(ActionEvent event) {
        naviageToManager("deposit");
    }
    
    public void handleDeleteButton(ActionEvent event) {
        parent.dbService.deleteUser(user.getId().toString());
        parent.navigation.naviageTo("/");
    }

    public void handleOutButton(ActionEvent event) {
        naviageToManager("withdraw");
    }

    public void handleBackButton(ActionEvent event) {
        parent.navigation.naviageTo("/");
    }

    public void handleMoveToTransfer(ActionEvent event) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", user.getId().toString());
        parent.navigation.naviageTo("/transfer", params);
    
    }
    @Override
    public void onMount(Map<String, String> params) {
        if (params != null && params.containsKey("userId")) {
            User user = parent.dbService.findUser(params.get("userId"));
            if(user != null) {
                this.user = user;
                userId.setText(user.getId().toString());
                fullName.setText(user.getName() + " " + user.getSurname());
                address.setText(user.getAddress());
                balance.setText(user.formattedMoney() + " zł");
                pesel.setText(user.getPesel());
            }
            
        }
    }

    @Override
    public void cleanupOnExit() {
        userId.setText("");
        fullName.setText("");
        address.setText("");
        balance.setText("");
        pesel.setText("");
        user = null;
    }

}
