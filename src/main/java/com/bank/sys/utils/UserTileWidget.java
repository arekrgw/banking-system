package com.bank.sys.utils;

import com.bank.sys.models.User;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UserTileWidget extends VBox {
    private User user;
    public UserTileWidget(User user, EventHandler<MouseEvent> eventHandler) {
        super();
        this.user = user;
        Label userName = new Label(user.getName() + " " + user.getSurname());
        userName.setFont(new Font(24));
        Label address = new Label(user.getAddress());
        Label pesel = new Label(user.getPesel());
        Label id = new Label(user.getId().toString());
        id.setFont(new Font(14));
        addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        setPadding(new Insets(15, 15, 15, 15));
        getChildren().addAll(userName, address, pesel, id);
    }

    public User getUser() {
        return user;
    }
}
