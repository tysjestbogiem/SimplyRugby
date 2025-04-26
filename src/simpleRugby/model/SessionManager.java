package simpleRugby.model;


public class SessionManager {
    private static int userId;
    private static String username;
    private static String role;

    // get id of used logged in
    public static void setUserSession(int id, String uname, String userRole) {
        userId = id;
        username = uname;
        role = userRole;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static void logout() {
        userId = 0;
        username = null;
        role = null;
    }
}
