package com.bank.sys;

import com.bank.sys.modals.ClipboardModal;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainController extends Application {

    public Navigation navigation;

    public DatabaseService dbService;
    
    public ClipboardModal clipboardModal;

    @Override
    public void start(Stage stage) throws Exception {

        dbService = new DatabaseService();
        navigation = new Navigation(this, stage);

        navigation.naviageTo("/");

        stage.show();

        clipboardModal = new ClipboardModal(stage);
    }

    public static void runApp(String[] args) {
        launch(args);
    }



   
}
