package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {

    public static final String BASE_URL;
    public static final String ADMIN_EMAIL;
    public static final String ADMIN_PASSWORD;

    static {
        Properties properties = new Properties();
        try (InputStream input = ConfigProvider.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BASE_URL = properties.getProperty("baseUrl");
        ADMIN_EMAIL = properties.getProperty("admin.email");
        ADMIN_PASSWORD = properties.getProperty("admin.password");
    }
}