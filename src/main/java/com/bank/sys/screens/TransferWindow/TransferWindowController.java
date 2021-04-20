package com.bank.sys.screens.TransferWindow;

import java.net.URL;

import com.bank.sys.MainController;
import com.bank.sys.utils.GenericWindowController;

public class TransferWindowController extends GenericWindowController {
    public final static URL formPath = TransferWindowController.class.getResource("transfer_window_form.fxml");

    MainController parent;
    
    public TransferWindowController(MainController parent) {
        this.parent = parent;
    }
}
