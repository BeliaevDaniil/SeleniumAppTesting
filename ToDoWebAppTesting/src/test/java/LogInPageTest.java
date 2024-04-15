import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPageTest {
    private WebDriver driver;
    private LogInPage logInPage;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/daniilbelaev/Desktop/webDriverForTS/chromedriver_mac64/chromedriver");
        driver = new ChromeDriver();
        logInPage = new LogInPage(driver);
        logInPage.visitPage();
    }

    @ParameterizedTest
    @CsvSource({
            "test@gmail.com, 123456",
            "test2@gmail.com, 654321",
            "test3@gmail.com, 123123"
    })
    public void fillForm_allInputsValid(String email, String password){
        logInPage.setEmail(email);
        logInPage.setPassword(password);
        logInPage.sendForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://wa.toad.cz/~beliadan/semestralka/todo.php"));

        //Expected results
        String expUrlOfNewPage = "http://wa.toad.cz/~beliadan/semestralka/todo.php";
        //Actual results
        String resUrlOfNewPage = driver.getCurrentUrl();
        //Assertions
        Assertions.assertEquals(expUrlOfNewPage,resUrlOfNewPage);
    }

    @ParameterizedTest
    @CsvSource({
            "notexistingemail1@gmail.com, 123456",
            "notexistingemail2@gmail.com, 654321",
            "notexistingemail3@gmail.com, 123123"
    })
    public void fillForm_allInputsInvalid(String email, String password){
        logInPage.setEmail(email);
        logInPage.setPassword(password);
        logInPage.sendForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://wa.toad.cz/~beliadan/semestralka/login.php"));

        //Expected results
        String expUrlOfNewPage = "http://wa.toad.cz/~beliadan/semestralka/login.php";
        String expEmailError = "User with this e-mail does not exist";
        String expPasswordError = "Password was written wrong";
        //Actual results
        String resUrlOfNewPage = driver.getCurrentUrl();
        String resEmailError = logInPage.getEmail_error().getText();
        String resPasswordError = logInPage.getPassword_error().getText();
        //Assertions
        Assertions.assertEquals(expUrlOfNewPage,resUrlOfNewPage);
        Assertions.assertEquals(expEmailError, resEmailError);
        Assertions.assertEquals(expPasswordError, resPasswordError);
    }


    @ParameterizedTest
    @CsvSource({
            "test@gmail.com, 111111",
            "test@gmail.com, 222222",
            "test@gmail.com, 333333"
    })
    public void fillForm_rightEmailWrongPassword(String email, String password){
        logInPage.setEmail(email);
        logInPage.setPassword(password);
        logInPage.sendForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://wa.toad.cz/~beliadan/semestralka/login.php"));

        //Expected results
        String expUrlOfNewPage = "http://wa.toad.cz/~beliadan/semestralka/login.php";
        String expEmailError = "";
        String expPasswordError = "Password was written wrong";
        //Actual results
        String resPasswordError = logInPage.getPassword_error().getText();
        String resUrlOfNewPage = driver.getCurrentUrl();
        if (logInPage.getEmail_error() != null) {
            String resEmailError = logInPage.getEmail_error().getText();
            //Assertions
            Assertions.assertEquals(expEmailError, resEmailError);
        }
        //Assertions
        Assertions.assertEquals(expUrlOfNewPage,resUrlOfNewPage);
        Assertions.assertEquals(expPasswordError, resPasswordError);
    }
}
