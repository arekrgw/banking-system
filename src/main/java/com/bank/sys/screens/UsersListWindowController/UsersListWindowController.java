package com.bank.sys.screens.UsersListWindowController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;  
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.bank.sys.MainController;
import com.bank.sys.models.User;
import com.bank.sys.utils.GenericWindowController;
import com.bank.sys.utils.UserTileWidget;
import com.mongodb.client.MongoCursor;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class UsersListWindowController extends GenericWindowController{

    public final static URL formPath = UsersListWindowController.class.getResource("users_list_window_form.fxml");

    @FXML
    private VBox verticalBox;

    @FXML
    private ScrollPane scrollPane;

    private MainController parent;

    public EventHandler<MouseEvent> elementClickEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            UserTileWidget widget = (UserTileWidget)event.getSource();

            Map<String, String> params = new HashMap<>();
            params.put("userId", widget.getUser().getId().toString());
            parent.navigation.naviageTo("/details", params);
        }
    };

    public UsersListWindowController(MainController parent) {
        this.parent = parent;
    }

    public void returnToHome(ActionEvent event) {
        parent.navigation.naviageTo("/");
    }

    @Override
    public void onMount(Map<String, String> params) {
        if (params != null) {
            
            MongoCursor<User> users = parent.dbService.findUsersCriteria(params.get("filterType"), params.get("filterValue")).cursor();
            while(users.hasNext()) {
                User user = users.next();
                verticalBox.getChildren().add(new UserTileWidget(user, elementClickEvent));
            }
        }
    }
}

