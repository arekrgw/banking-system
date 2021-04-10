package com.bank.sys;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainController extends Application {

    public Navigation navigation;

    public DatabaseService dbService;

    @Override
    public void start(Stage stage) throws Exception {

        dbService = new DatabaseService();
        navigation = new Navigation(this, stage);

        navigation.naviageTo("/");

        stage.show();
    }

    public static void runApp(String[] args) {
        launch(args);
    }



   
}
