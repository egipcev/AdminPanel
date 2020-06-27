import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private static final By CUSTOMER = By.id("customer");
    private static final By DASHBOARD_NAME = By.id("dashboard_name");
    private static final By DISPLAY_TITLE = By.id("display_title");
    private static final By GENERATE_BUTTON = By.id("generate_button");
    private static final String MAIN_URL = "https://";
    private static final Logger LOGGER = LoggerFactory.getLogger(MainPage.class);


    public MainPage() {
        Selenide.open(MAIN_URL);
        LOGGER.info("Main page is opened");
        TestBase.waitFor(webDriver -> $(CUSTOMER).isDisplayed());
        LOGGER.info("CUSTOMER field is displayed");
    }

    public MainPage insertData(Map<String,String> inputData) {
        $(CUSTOMER).sendKeys(inputData.get("customer"));
        $(DASHBOARD_NAME).sendKeys(inputData.get("dashboard_name"));
        $(DISPLAY_TITLE).sendKeys(inputData.get("display_title"));
        return this;
    }

    public void generateJsonFile() {
        TestBase.waitFor(webDriver -> $(GENERATE_BUTTON).isDisplayed());
        $(GENERATE_BUTTON).click();
    }
}
