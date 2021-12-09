package api_test;

import javax.xml.bind.DatatypeConverter;

public abstract class BaseTests {

    // Config data.
    protected final static String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    protected static String apiUri;
    protected static String authApiMethod;
    protected static String playersManageApiMethod;
    protected static String apiBasicUsername;

    // Passed variables. Needed to transfer data between tests.
    protected static String accessToken;
    protected static String usersAccessToken;
    protected static int userID;

    // User credentials.
    protected static int randomNumber;
    protected static String username;
    protected static String name;
    protected static String surname;
    protected static String email;

    // Helper methods.
    public String encodePassword(String passPhrase) {
        String str = passPhrase;
        String encodedPassword = DatatypeConverter.printBase64Binary(str.getBytes());
        return encodedPassword;
    }

}
