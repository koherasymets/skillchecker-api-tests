package helpers;

import java.util.ResourceBundle;

public class ConfigProvider {

    public static final String BASE_URL;

    static {
        ResourceBundle resource = ResourceBundle.getBundle("config");
        BASE_URL = resource.getString("baseUrl");
    }
}