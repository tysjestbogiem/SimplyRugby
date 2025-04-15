package config;

/**
 * This class contains constants that are used across multiple parts of the application,
 * such as:
 * - UI colours
 * - Fonts
 * - MySQL credentials
 */
public class CommonConstraints {

    // MySQL Credentials
    public static final String DB_HOST = "localhost";
    public static final String DB_NAME = "simply_rugby";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";  

    public static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME;
}







