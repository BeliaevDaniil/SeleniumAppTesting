import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private final WebDriver driver;

    //Page Elements
    @FindBy(id="nickname")
    private WebElement nickname;
    @FindBy(id="email")
    private WebElement email;
    @FindBy(id="password")
    private WebElement password;
    @FindBy(id="password2")
    private WebElement repeated_password;
    @FindBy(id="tel")
    private WebElement phone_number;
    @FindBy(className="btn_form")
    private WebElement register_btn;
    @FindBy(id="error_nickname")
    private WebElement nickname_error;
    @FindBy(id="error_email")
    private WebElement email_error;
    @FindBy(id="error_password")
    private WebElement password_error;
    @FindBy(id="error_password2")
    private WebElement repeated_password_error;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void visitPage() {this.driver.get("http://wa.toad.cz/~beliadan/semestralka/registration.php");}

    public void setNickname(String nickname){this.nickname.sendKeys(nickname);}

    public void setEmail(String email){
        this.email.sendKeys(email);
    }

    public void setPassword(String password){
        this.password.sendKeys(password);
    }

    public void setRepeatedPassword(String password){
        this.repeated_password.sendKeys(password);
    }

    public void setPhoneNumber(String phoneNumber){
        this.phone_number.sendKeys(phoneNumber);
    }

    public void sendForm() {
        this.register_btn.click();
    }

    public WebElement getNickname_error() {return nickname_error;}

    public WebElement getEmail_error() {return email_error;}

    public WebElement getPassword_error() {return password_error;}

    public WebElement getRepeated_password_error() {return repeated_password_error;}
}
