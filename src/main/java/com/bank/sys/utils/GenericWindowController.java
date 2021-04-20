package com.bank.sys.utils;

import java.util.Map;

import com.bank.sys.MainController;

public abstract class GenericWindowController {
    MainController parent;
    public void cleanupOnExit() {}
    public void onMount(Map<String, String> params) {}
}
