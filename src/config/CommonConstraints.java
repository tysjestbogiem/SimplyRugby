package config;

/**
 * This class contains constants that are used across multiple parts of the application,
 * such as:
 * - Database login details
 * - Host and port info
 * - A full DB URL that combines everything above
 */
public class CommonConstraints {
	
	
	public static final String DB_HOST = "hopper.proxy.rlwy.net"; // railway public host
	public static final String DB_PORT = "11207";                 // railway public port
	public static final String DB_NAME = "railway";               // database name
	public static final String DB_USER = "root";                  // user 
	public static final String DB_PASSWORD = "eWyBaHFVujVxlxRAaOMEjHASSUTPMYca"; 

	// Full URL string for connecting to the database with JDBC
    // "useSSL=false" avoids SSL warnings, "allowPublicKeyRetrieval=true" allows public key exchange
	public static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useSSL=false&allowPublicKeyRetrieval=true"; // disable the warning while connecting to a database in Java

	
}










