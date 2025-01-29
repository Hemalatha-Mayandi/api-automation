import io.restassured.response.Response;
import org.example.domain.libraryUd.Libcourses;
import org.example.utils.HttpClientAllureWrapper;
import org.example.utils.PropertyUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import static java.nio.file.Files.readAllBytes;

public class jsonTests {

    private static final PropertyUtils propertyUtils = new PropertyUtils("api.properties");
    private static final String MAPS = "/maps/api/place/add/json";
    private static final String GET_BOOK_PHP = "/Library/GetBook.php";
    private static final String GET_COURSE_DETAILS = "/oauthapi/getCourseDetails";

    @Test
    public void testPostAPi() {

        String payload = "src/main/resources/template/text/jsonPayload/locationsPayload.json";
        Response response = HttpClientAllureWrapper.restAssuredClient(requestSpecification -> {
            try {
                return requestSpecification
                        .baseUri(propertyUtils.getProperty("api.udemy"))
                        .basePath(MAPS)
                        .queryParams("key", "qaclick123")
                        .headers("Accept", "application/json")
                        .body(new String(readAllBytes(Paths.get(payload))))
                        .post().then().extract().response();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("response ::: " + response.asPrettyString());
    }

    @Test
    public void testLibraryGetApi() {
        Response response = HttpClientAllureWrapper.restAssuredClient(requestSpecification -> requestSpecification
                .baseUri(propertyUtils.getProperty("api.udemy.baseuri"))
                .basePath(GET_BOOK_PHP)
                .queryParam("AuthorName","John foe")
                .get()).then().extract().response();

        System.out.println("RESPONSE :: "+ response.asPrettyString());

    }

    @Test
    public void testLibraryGetApiResponse() {
        Map<String, String> authParams = Map.of(
                "client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com",
                "client_secret", "erZOWM9g3UtwNRj340YYaK_W",
                "grant_type","client_credentials",
                "scope","trust");

        Response response = HttpClientAllureWrapper.restAssuredClient(requestSpecification -> requestSpecification
                .baseUri(propertyUtils.getProperty("api.udemy.oAuth.uri"))
                .formParams(authParams)
                .post().then().extract().response());

        String access_token = response.getBody().jsonPath().getString("access_token");

        System.out.printf("Access Token  "+ access_token);

        Response getCourseDetailsResponse = HttpClientAllureWrapper.restAssuredClient(requestSpecification -> requestSpecification
                .baseUri(propertyUtils.getProperty("api.udemy"))
                .basePath(GET_COURSE_DETAILS)
                .queryParam("access_token",access_token)
                .get().then().extract().response());

        System.out.println("Course Title "+ getCourseDetailsResponse
                .as(Libcourses.class).getCourses().getApi().get(0).getCourseTitle());

        System.out.println("Get Price "+ getCourseDetailsResponse
                .as(Libcourses.class).getCourses().getApi().get(0).getPrice());
    }
}

