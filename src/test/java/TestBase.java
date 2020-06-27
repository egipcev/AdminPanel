import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import org.junit.AfterClass;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

import static com.codeborne.selenide.Selenide.Wait;

public class TestBase {

    static {
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        Configuration.browser = TestBase.CustomWebDriverProviderChrome.class.getName();
    }

    public static class CustomWebDriverProviderChrome implements WebDriverProvider {
        public WebDriver createDriver(DesiredCapabilities capabilities) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-web-security");
            chromeOptions.addArguments("--window-size=1280,1696");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--incognito");
            chromeOptions.setAcceptInsecureCerts(true);
            return new ChromeDriver(chromeOptions);
        }
    }

    public static void waitFor(Function<WebDriver, Boolean> condition) {
        try {
            Wait()
                    .withTimeout(Duration.ofMinutes(2L))
                    .ignoring(InvalidElementStateException.class)
                    .pollingEvery(Duration.ofSeconds(5L))
                    .until(condition);
        } catch (TimeoutException ex) {
            LocalDateTime now = LocalDateTime.now();
            Selenide.screenshot(now.toString());
        }
    }

    @AfterClass
    public static void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }

}
