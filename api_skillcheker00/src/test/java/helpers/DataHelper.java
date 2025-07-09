package helpers;

import java.util.Random;

public class DataHelper {

    public static String generateRandomEmail() {
        return "user" + System.currentTimeMillis() + "@skillchecker.tech";
    }

    public static String generateRandomPassword() {
        return "pass" + new Random().nextInt(99999);
    }

    public static String generateInvalidEmail() {
        return "invalid" + new Random().nextInt(99999) + "@wrongdomain.com";
    }

    public static String generateInvalidPassword() {
        return "wrongpass" + new Random().nextInt(99999);
    }

    public static String generateRandomTestName() {
        return "API_Test_" + System.currentTimeMillis();
    }
}