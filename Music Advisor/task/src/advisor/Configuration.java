package advisor;

public class Configuration {

    public static String SERVER_PATH = "https://accounts.spotify.com";
    public static final String REDIRECT_URI = "http://localhost:8080";
    public static final String CLIENT_ID = "e5c95267adf941d58b7b22b2889ac065";
    public static final String CLIENT_SECRET = "d15c6db35b504d10872fd0012d8bce71";

    public static final String URL = SERVER_PATH + "/authorize?client_id="
                                    + CLIENT_ID + "&redirect_uri="
                                    + REDIRECT_URI + "&response_type=code";
}
