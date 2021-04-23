package com.bank.sys.modals;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClipboardModal {

    public Stage dialog;

    public ClipboardModalController controller;

    public ClipboardModal(Stage parent) throws IOException {
        dialog = new Stage();
        dialog.initOwner(parent);
        dialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clipboard_modal_form.fxml"));
        controller = new ClipboardModalController(this);
        loader.setController(controller);
        dialog.setScene(new Scene((Parent)loader.load()));
        dialog.setTitle("Schowek");
    }
    
}
