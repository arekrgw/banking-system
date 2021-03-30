package com.bank.sys;

import java.io.IOException;

import com.bank.sys.screens.MainWindow.MainWindowController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Application {

    private Parent mainWindow;

    @Override
    public void start(Stage stage) throws Exception {

        mainWindow = loadFXML(new MainWindowController(this), "./screens/MainWindow/main_window_form.fxml");

        stage.setScene(new Scene(mainWindow));

        stage.show();
    }

    public static void runApp(String[] args) {
        launch(args);
    }

    private Parent loadFXML(Object controller, String path) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        loader.setController(controller);
        return loader.load();
    }
}
