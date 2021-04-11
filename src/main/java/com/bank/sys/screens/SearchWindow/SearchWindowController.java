package com.bank.sys.screens.SearchWindow;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.bank.sys.MainController;
import com.bank.sys.utils.GenericWindowController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SearchWindowController extends GenericWindowController implements Initializable {
    public final static URL formPath = SearchWindowController.class.getResource("search_window_form.fxml");
    private ObservableMap<String, String> listItems = FXCollections.observableHashMap();

    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private TextField filterField;

    @FXML
    private Button searchButton;

    @FXML
    private Button backButton;

    private MainController parent;

    public SearchWindowController(MainController parent) {
        this.parent = parent;
    }

    public void handleSearch(ActionEvent event) {
        Map<String, String> params = new HashMap<>();
        if(choice.getValue().compareTo("Identyfikator") == 0) {
            params.put("userId", filterField.getText());
            parent.navigation.naviageTo("/details", params);
            return;
        }   

        params.put("filterType", listItems.get(choice.getValue()));
        params.put("filterValue", filterField.getText());
        parent.navigation.naviageTo("/users", params);
    }

    public void handleBack(ActionEvent event) {
        parent.navigation.naviageTo("/");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listItems.put("Identyfikator", "_id");
        listItems.put("Nazwisko", "surname");
        listItems.put("Adres", "address");
        listItems.put("Pesel", "pesel");
        listItems.put("Imię", "name");
        ObservableList<String> l = FXCollections.observableArrayList(listItems.keySet());
        choice.setItems(l);
        choice.setValue("Identyfikator");
    }

}