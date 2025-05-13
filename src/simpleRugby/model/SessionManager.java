package simpleRugby.model;


/**
 * SessionManager is a utility class used to store the session information
 * of the currently logged-in user. It keeps track of user ID, username, and role
 * throughout the application session.
 */
public class SessionManager {

    private static int userId;
    private static String username;
    private static String role;

    /**
     * Sets the current session details after a successful login.
     *
     * @param id the user ID of the logged-in user
     * @param uname the username of the logged-in user
     * @param userRole the role of the logged-in user
     */
    public static void setUserSession(int id, String uname, String userRole) {
        userId = id;
        username = uname;
        role = userRole;
    }

    /**
     * Retrieves the user ID of the current session.
     *
     * @return the user ID
     */
    public static int getUserId() {
        return userId;
    }

    /**
     * Retrieves the username of the current session.
     *
     * @return the username
     */
    public static String getUsername() {
        return username;
    }

    /**
     * Retrieves the role of the current session.
     *
     * @return the user role (e.g., Coach)
     */
    public static String getRole() {
        return role;
    }

    /**
     * Clears all session data, logging out the user.
     */
    public static void logout() {
        userId = 0;
        username = null;
        role = null;
    }
}
