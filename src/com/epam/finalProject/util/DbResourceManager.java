package com.epam.finalProject.util;

import java.util.ResourceBundle;

public class DbResourceManager {

    private final static String DB = "properties.dbconfig";

    private DbResourceManager() {
    }

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(DB);

    public static String getValue(String key) {
        return resourceBundle.getString(key);
    }

}
