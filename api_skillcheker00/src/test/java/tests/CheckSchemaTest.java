package tests;

import org.testng.annotations.Test;

import java.net.URL;

public class CheckSchemaTest {

    @Test
    public void checkSchemaClasspath() {
        URL resource = getClass().getClassLoader().getResource("schemas/error_response.json");
        System.out.println("Resource URL = " + resource);
        if (resource == null) {
            throw new RuntimeException("Файл schemas/error_response.json не найден в classpath!");
        }
    }
}