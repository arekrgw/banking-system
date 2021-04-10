package com.bank.sys;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bank.sys.models.User;
import com.bank.sys.screens.CreateAccountWindow.CreateAccountWindowController;
import com.bank.sys.screens.MainWindow.MainWindowController;
import com.bank.sys.screens.UserDetailsWindow.UserDetailsWindowController;
import com.bank.sys.utils.GenericWindowController;
import com.bank.sys.utils.Pair;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Application {

    private Stage mainStage;
    private Map<String, Pair<Scene, GenericWindowController>> navigation;
    private String currentPath;

    public User loggedUser = null;

    public DatabaseService dbService;

    @Override
    public void start(Stage stage) throws Exception {

        dbService = new DatabaseService();

        navigation = new HashMap<>();

        mainStage = stage;

        naviageTo("/");

        mainStage.show();
    }

    public static void runApp(String[] args) {
        launch(args);
    }

    private Parent loadFXML(Object controller, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        loader.setController(controller);
        return loader.load();
    }

    public void naviageTo(String path, Map<String, String> params) {
        try {
            if (currentPath != null) {
                navigation.get(currentPath).getSecond().cleanupOnExit();
            }

            if (navigation.get(path) == null) {
                Pair<Scene, GenericWindowController> tmp = new Pair<>();

                switch (path) {
                case "/":
                    tmp.setSecond(new MainWindowController(this));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), "./screens/MainWindow/main_window_form.fxml")));
                    break;
                case "/create":
                    tmp.setSecond(new CreateAccountWindowController(this));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), "./screens/CreateAccountWindow/create_window_form.fxml")));
                    break;
                case "/details":
                    tmp.setSecond(new UserDetailsWindowController(this));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), "./screens/UserDetailsWindow/user_details_window_form.fxml")));
                    break;
                default:
                    System.out.println("[NAVIGATION ERR]: path \"" + path + "\" not found");
                    return;
                }
                navigation.put(path, tmp);
            }

            mainStage.setScene(navigation.get(path).getFirst());
            currentPath = path;

            navigation.get(currentPath).getSecond().onMount(params);
        } catch (IOException fileEx) {
            System.out.println("[NAVIGATION ERR]: path \"" + path + "\" couldn't be resolved");
        }

    }

    public void naviageTo(String path) {
        naviageTo(path, null);
    }
}
