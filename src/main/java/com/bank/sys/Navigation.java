package com.bank.sys;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.bank.sys.screens.CreateAccountWindow.CreateAccountWindowController;
import com.bank.sys.screens.MainWindow.MainWindowController;
import com.bank.sys.screens.MoneyManagerWindow.MoneyManagerWindowController;
import com.bank.sys.screens.SearchWindow.SearchWindowController;
import com.bank.sys.screens.TransferWindow.TransferWindowController;
import com.bank.sys.screens.UserDetailsWindow.UserDetailsWindowController;
import com.bank.sys.screens.UsersListWindowController.UsersListWindowController;
import com.bank.sys.utils.GenericWindowController;
import com.bank.sys.utils.Pair;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {
    
    
    
    private Stage mainStage;
    private Map<String, Pair<Scene, GenericWindowController>> navigation;
    private String currentPath;
    private MainController parent;

    public Navigation(MainController parent, Stage mainStage) {
        this.mainStage = mainStage;
        this.parent = parent;
        this.navigation = new HashMap<>();
    }

    private Parent loadFXML(Object controller, URL path) throws IOException {
        FXMLLoader loader = new FXMLLoader(path);
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
                    tmp.setSecond(new MainWindowController(parent));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), MainWindowController.formPath)));
                    break;
                case "/create":
                    tmp.setSecond(new CreateAccountWindowController(parent));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), CreateAccountWindowController.formPath)));
                    break;
                case "/details":
                    tmp.setSecond(new UserDetailsWindowController(parent));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), UserDetailsWindowController.formPath)));
                    break;
                case "/search":
                    tmp.setSecond(new SearchWindowController(parent));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), SearchWindowController.formPath)));
                    break;
                case "/usersList":
                    tmp.setSecond(new UsersListWindowController(parent));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), UsersListWindowController.formPath)));
                break;
                case "/transfer":
                    tmp.setSecond(new TransferWindowController(parent));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), TransferWindowController.formPath)));
                break;
                case "/manager":
                    tmp.setSecond(new MoneyManagerWindowController(parent));
                    tmp.setFirst(new Scene(
                        loadFXML(tmp.getSecond(), MoneyManagerWindowController.formPath)));
                break;
                default:
                    System.out.println("[NAVIGATION ERR]: path \"" + path + "\" not found");
                    return;
                }
                navigation.put(path, tmp);
            }

            mainStage.setScene(navigation.get(path).getFirst());
            currentPath = path;

            navigation.get(currentPath).getSecond()
            
            
            .onMount(params);
        } catch (IOException fileEx) {
            System.out.println("[NAVIGATION ERR]: path \"" + path + "\" couldn't be resolved");
        }

    }

    public void naviageTo(String path) {
        naviageTo(path, null);
    }
}
