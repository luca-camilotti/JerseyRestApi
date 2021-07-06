package luke.jersey.rest.config;

public class ServiceConfiguration {
	
	
	/* Debug flag */
	public static final boolean Debug = true;
	
	/* JWT */
	public static final String secret = "Uqa$0@tum#1Y5z80";
	public static final String issuer = "auth0";
	public static final String keyName = "name";
	public static final long tokenExpiredMs = 1000*60*5;  // 5 mins

}
