package com.bank.sys;

import java.io.IOException;

import com.bank.sys.models.User;
import com.bank.sys.screens.LoginWindow.LoginWindowController;
import com.bank.sys.screens.MainWindow.MainWindowController;
import com.bank.sys.screens.RegisterWindow.RegisterWindowController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Application {

    private Stage mainStage;
    private Scene mainWindow;
    private Scene registerWindow;
    private Scene loginWindow;

    public User loggedUser = null;

    public DatabaseService dbService;

    @Override
    public void start(Stage stage) throws Exception {

        dbService = new DatabaseService();

        mainStage = stage;

        naviageTo("/");

        mainStage.show();
    }

    public static void runApp(String[] args) {
        launch(args);
    }

    private Parent loadFXML(Object controller, String path) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        loader.setController(controller);
        return loader.load();
    }

    public void naviageTo(String path) {
        try {
            switch(path) {
                case "/":
                    if(mainWindow == null) {
                        mainWindow = new Scene(loadFXML(new MainWindowController(this), "./screens/MainWindow/main_window_form.fxml"));
                    }
                    mainStage.setScene(mainWindow);
                break;
                case "/register":
                    if(registerWindow == null) {
                        registerWindow = new Scene(loadFXML(new RegisterWindowController(this), "./screens/RegisterWindow/register_window_form.fxml"));
                    }
                    mainStage.setScene(registerWindow);
                break;
                case "/login":
                    if(loginWindow == null) {
                        loginWindow = new Scene(loadFXML(new LoginWindowController(this), "./screens/LoginWindow/login_window_form.fxml"));
                    }
                    mainStage.setScene(loginWindow);
                break;
                default:
                    System.out.println("[NAVIGATION ERR]: path \"" + path + "\" not found");
                break;
            }
        }
        catch(IOException fileEx) {
            System.out.println("[NAVIGATION ERR]: path \"" + path + "\" couldn't be resolved");
        } 
       
    }
}
