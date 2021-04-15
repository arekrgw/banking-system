package com.bank.sys.screens.UsersListWindowController;

import java.net.URL;
import java.util.Map;

import com.bank.sys.MainController;
import com.bank.sys.models.User;
import com.bank.sys.utils.GenericWindowController;
import com.mongodb.client.FindIterable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class UsersListWindowController extends GenericWindowController{

    public final static URL formPath = UsersListWindowController.class.getResource("users_list_window_form.fxml");

    private MainController parent;

    public UsersListWindowController(MainController parent) {
        this.parent = parent;
    }



    @Override
    public void onMount(Map<String, String> params) {
        if (params != null) {
            FindIterable<User> users = parent.dbService.findUsersCriteria(params.get("filterType"), params.get("filterValue"));
            //print users
        }
    }
}

