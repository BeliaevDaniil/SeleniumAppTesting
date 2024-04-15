import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WelcomePageTest {
    private WebDriver driver;
    private WelcomePage welcomePage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/daniilbelaev/Desktop/webDriverForTS/chromedriver_mac64/chromedriver");
        driver = new ChromeDriver();
        welcomePage = new WelcomePage(driver);
        welcomePage.visitPage();
    }

    @Test
    public void welcomePage_checkLogoTitle() {
        //Expected results
        String expectedTitle = "To-do";
        //Assertions
        Assertions.assertEquals(expectedTitle, welcomePage.getLogoTitle());
    }

    @Test
    public void switchDarkThemeTest(){
        welcomePage.switchDarkTheme();
        driver.navigate().refresh();

        //Expected results
        String expBodyColor = "rgba(255, 255, 255, 1)";
        //Actual results
        String resBodyColor = welcomePage.getBodyColor();
        //Assertions
        Assertions.assertEquals(expBodyColor, resBodyColor);
    }

    @Test
    public void switchLightThemeTest(){
        welcomePage.switchLightTheme();
        driver.navigate().refresh();

        //Expected results
        String expBodyColor = "rgba(33, 33, 33, 1)";
        //Actual results
        String resBodyColor = welcomePage.getBodyColor();
        //Assertions
        Assertions.assertEquals(expBodyColor, resBodyColor);
    }

    @Test
    public void createAnAccountButtonTest(){
        welcomePage.sendForm();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://wa.toad.cz/~beliadan/semestralka/registration.php?"));
        //Expected results
        String expUrlOfNewPage = "http://wa.toad.cz/~beliadan/semestralka/registration.php?";
        //Actual results
        String resUrlOfNewPage = driver.getCurrentUrl();
        //Assertions
        Assertions.assertEquals(expUrlOfNewPage,resUrlOfNewPage);
    }

}
