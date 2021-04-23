package com.bank.sys.modals;

import com.bank.sys.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ClipboardModalController {
    
    @FXML
    private ListView<UserModalEntry> usersList;

    private ClipboardModal parent;
    private ObservableList<UserModalEntry> users;
    private User selectedUser = null;

    public ClipboardModalController(ClipboardModal parent) {
        this.parent = parent;
    }

    public void handlePaste(ActionEvent event) {
        parent.dialog.hide();
        selectedUser = usersList.getSelectionModel().getSelectedItem().user;
    }

    public User getUser() {
        selectedUser = null;
        this.parent.dialog.showAndWait();
        return selectedUser;
    } 

    public void addUser(User user) {
        users.add(0, new UserModalEntry(user));
    }

    @FXML
    public void initialize() {
        users = FXCollections.observableArrayList();
        usersList.setItems(users);
    }


}
