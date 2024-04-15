import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPageTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/daniilbelaev/Desktop/webDriverForTS/chromedriver_mac64/chromedriver");
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        registrationPage.visitPage();
    }

    @ParameterizedTest
    @CsvSource({
            "ValidName1, validemail11@mail.com, validPassword1, validPassword1, 696969696",
            "ValidName2, validemail22@mail.com, validPassword2, validPassword2, 420420420",
            "ValidName3, validemail33@mail.com, validPassword3, validPassword3, 666666666"
    })
    public void fillForm_allInputsValid(String nickname, String email, String password, String repeated_password, String phone_number){
        registrationPage.setNickname(nickname);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setRepeatedPassword(repeated_password);
        registrationPage.setPhoneNumber(phone_number);
        registrationPage.sendForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://wa.toad.cz/~beliadan/semestralka/login.php"));

        //Expected results
        String expUrlOfNewPage = "http://wa.toad.cz/~beliadan/semestralka/login.php";
        //Actual results
        String resUrlOfNewPage = driver.getCurrentUrl();
        //Assertions
        Assertions.assertEquals(expUrlOfNewPage,resUrlOfNewPage);
    }

    @ParameterizedTest
    @CsvSource({
            "Va, invalidemailgmail.com, invP1, inValidPassword1, 101010",
            "Le, invalidemail2gmail.com, invP2, invalidPassword2, notAPhoneNumber",
            "23, invalidemail3gmail.com, invP3, invalidPassword3, //``z["
    })
    public void fillForm_allInputsInvalid(String nickname, String email, String password, String repeated_password, String phone_number){
        registrationPage.setNickname(nickname);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setRepeatedPassword(repeated_password);
        registrationPage.setPhoneNumber(phone_number);
        registrationPage.sendForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://wa.toad.cz/~beliadan/semestralka/registration.php"));

        //Expected results
        String expUrlOfNewPage = "http://wa.toad.cz/~beliadan/semestralka/registration.php";
        String expNicknameError = "Min. length of nickname is 3 characters";
        String expEmailError = "Email should match format xxx@xxx.xxx";
        String expPasswordError = "Min. length of password is 6 characters";
        String expRepeatedPasswordError = "Passwords do not match";
        //Actual results
        String resUrlOfNewPage = driver.getCurrentUrl();
        String resNicknameError = registrationPage.getNickname_error().getText();
        String resEmailError = registrationPage.getEmail_error().getText();
        String resPasswordError = registrationPage.getPassword_error().getText();
        String resRepeatedPasswordError = registrationPage.getRepeated_password_error().getText();

        //Assertions
        Assertions.assertEquals(expUrlOfNewPage, resUrlOfNewPage);
        Assertions.assertEquals(expNicknameError, resNicknameError);
        Assertions.assertEquals(expEmailError, resEmailError);
        Assertions.assertEquals(expPasswordError, resPasswordError);
        Assertions.assertEquals(expRepeatedPasswordError, resRepeatedPasswordError);
    }
}
