package com.bank.sys.utils;

import java.util.Map;

public abstract class GenericWindowController {
    public void cleanupOnExit() {}
    public void onMount(Map<String, String> params) {}
}
