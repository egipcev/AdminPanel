import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AdminPanelTest extends TestBase {
    public static final String API_URL = "https://";

    @Test
    public void testGenerateConfigFile() {
        new MainPage()
                .insertData(Helper.getProperties())
                .generateJsonFile();
        String configId = getConfigId();
        Assert.assertTrue("File was not created!", isFileGenerated(configId));
        //check json fields
    }

    private String getConfigId() {
        //some code to get config id
        return null;
    }

    private boolean isFileGenerated(String configId) {
        Response response = given()
                .baseUri(API_URL)
                .get("/API/configurations/" + configId);
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            return true;
        }
        return false;
    }
}
