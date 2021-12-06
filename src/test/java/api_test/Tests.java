package api_test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class Tests extends BaseTests {


        @BeforeClass
        public static void setUp() {

            System.out.println("Start tests");

            randomNumber = 1 + (int) (Math.random() * 10000000);
            username = "kinyaev" + randomNumber;
            name = "Jason";
            surname = "Bourne";
            email = "foma"+ randomNumber + "@cia.com";

            FileInputStream fileInputStream;
            Properties prop = new Properties();

            try {

                fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
                prop.load(fileInputStream);

                apiUri = prop.getProperty("api_uri");
                authApiMethod = prop.getProperty("auth_api_method");
                playersManageApiMethod = prop.getProperty("players_manage_api_method");
                apiBasicUsername = prop.getProperty("api_basic_username");

            } catch (IOException e) {
                System.out.println("Error: file " + PATH_TO_PROPERTIES + " not found");
                e.printStackTrace();
            }

            RestAssured.baseURI = apiUri;

        }

        //@Ignore
        @Test
        public void test1_getToken() {

            Map<String, Object> body = new HashMap<>();
            body.put("grant_type", "client_credentials");
            body.put("scope", "guest:default");

            accessToken = "Bearer " +
                    given()
                            //.log().all()
                            .auth()
                            .preemptive()
                            .basic(apiBasicUsername, "")
                            .contentType(ContentType.JSON)
                            .body(body)
                            .when().post(authApiMethod)
                            .then()
                            .assertThat()
                            .statusCode(200).and()
                            .time(lessThan(4000L)).and()
                            .body("access_token", notNullValue())
                            .extract().jsonPath().getString("access_token");

            System.out.println("1. The response code are correct (200), the token was received successfully");

        }

        //@Ignore
        @Test
        public void test2_createUser() {

            Map<String, Object> body = new HashMap<>();
            body.put("username", username);
            body.put("password_change", encodePassword("newpassword1"));
            body.put("password_repeat", encodePassword("newpassword1"));
            body.put("email", email);
            body.put("name", name);
            body.put("surname", surname);
            body.put("currency", "EUR");

            userID =
                    given()
                            //.log().all()
                            .header("Authorization", accessToken)
                            .contentType(ContentType.JSON)
                            .body(body)
                            .when().post(playersManageApiMethod)
                            .then()
                            .assertThat()
                            .body("id", notNullValue()).and()
                            .body("username", equalTo(username)).and()
                            .body("email", equalTo(email)).and()
                            .body("name", equalTo(name)).and()
                            .body("surname", equalTo(surname)).and()
                            .body("country_id", equalTo(null)).and()
                            .body("timezone_id", equalTo(null)).and()
                            .body("gender", equalTo(null)).and()
                            .body("phone_number", equalTo(null)).and()
                            .body("birthdate", equalTo(null)).and()
                            .body("bonuses_allowed", equalTo(true)).and()
                            .body("is_verified", equalTo(false)).and()
                            .statusCode(201)
                            .time(lessThan(4000L)).and()
                            .extract().jsonPath().getInt("id");

            System.out.println("2. The response code and JSON schema are correct (201). New user created successfully");

        }

        //@Ignore
        @Test
        public void test3_loginUser() {

            Map<String, Object> body = new HashMap<>();
            body.put("grant_type", "password");
            body.put("username", username);
            body.put("password", encodePassword("newpassword1"));

            usersAccessToken = "Bearer " +
                    given()
                            //.log().all()
                            .auth()
                            .preemptive()
                            .basic(apiBasicUsername, "")
                            .contentType(ContentType.JSON)
                            .body(body)
                            .when().post(authApiMethod)
                            .then()
                            .assertThat()
                            .statusCode(200).and()
                            .time(lessThan(4000L)).and()
                            .body("access_token", notNullValue()).and()
                            .body("refresh_token", notNullValue())
                            .extract().jsonPath().getString("access_token");

            System.out.println("3. The response code are correct (200), the token was received successfully");

        }

        //@Ignore
        @Test
        public void test4_getUserInfo() {

                    given()
                            //.log().all()
                            .header("Authorization", usersAccessToken)
                            .contentType(ContentType.JSON)
                            .when().get(playersManageApiMethod + "/" + userID)
                            .then()
                            .assertThat()
                            .body("id", equalTo(userID)).and()
                            .body("username", equalTo(username)).and()
                            .body("email", equalTo(email)).and()
                            .body("name", equalTo(name)).and()
                            .body("surname", equalTo(surname)).and()
                            .body("country_id", equalTo(null)).and()
                            .body("timezone_id", equalTo(null)).and()
                            .body("gender", equalTo(null)).and()
                            .body("phone_number", equalTo(null)).and()
                            .body("birthdate", equalTo(null)).and()
                            .body("bonuses_allowed", equalTo(true)).and()
                            .body("is_verified", equalTo(false)).and()
                            .time(lessThan(4000L)).and()
                            .statusCode(200);

            System.out.println("4. The response code are correct (200), the  response matches the specification");

        }

        //@Ignore
        @Test
        public void test5_getNonExistentUserInfo() {

                    given()
                            //.log().all()
                            .header("Authorization", usersAccessToken)
                            .contentType(ContentType.JSON)
                            .when().get(playersManageApiMethod + "/" + (userID + randomNumber))
                            .then()
                            .assertThat()
                            .time(lessThan(4000L)).and()
                            .statusCode(404);

            System.out.println("5. The response code are correct (404). A non-existent user was not found");

        }


        @AfterClass
        public static void tearDown() {

            System.out.println("Finish tests");

        }

 }

