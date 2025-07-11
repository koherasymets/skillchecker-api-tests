package helpers;

import org.apache.commons.lang3.RandomStringUtils;

public class DataHelper {

    public static String generateRandomEmail() {
        return "user_" + RandomStringUtils.randomAlphabetic(6).toLowerCase() + "@skillchecker.tech";
    }

    public static String generateRandomPassword() {
        return "Pass" + RandomStringUtils.randomNumeric(5);
    }

    public static String generateInvalidEmail() {
        return "invalid_" + RandomStringUtils.randomAlphabetic(6).toLowerCase() + "@wrongdomain.com";
    }

    public static String generateInvalidPassword() {
        return "WrongPass" + RandomStringUtils.randomNumeric(5);
    }

    public static String generateRandomTestName() {
        return "API_Test_" + RandomStringUtils.randomAlphanumeric(8);
    }
}