package api_test;

import javax.xml.bind.DatatypeConverter;

public class BaseTest {

    // API access data.
    protected final static String API_URI = "http://someapiadress.com";
    protected final static String AUTH_METHOD = "/v2/method1";
    protected final static String MANAGE_PLAYERS_METHOD = "/v2/method2";
    protected final static String BASIC_AUTH_USERNAME = "front_123456eee1234567fff123";

    // Passed variables. Needed to transfer data between tests.
    protected static String accessToken;
    protected static String usersAccessToken;
    protected static int userId;

    // Generating user credentials.
    protected static int randomNumber = 1 + (int) (Math.random() * 10000000);
    protected static String username = "kinyaev" + randomNumber;
    protected static String name = "Jason";
    protected static String surname = "Bourne";
    protected static String email = "foma"+ randomNumber + "@cia.com";

    // Helper methods.
    protected String encodePassword(String passPhrase) {
        String str = passPhrase;
        String encodedPassword = DatatypeConverter.printBase64Binary(str.getBytes());
        return encodedPassword;
    }

}
