import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class LogInPage {
    private final WebDriver driver;
    //Page Elements
    @FindBy(id="email_login")
    private WebElement email;
    @FindBy(id="password_login")
    private WebElement password;
    @FindBy(className="btn_form")
    private WebElement login_btn;
    @FindBy(className = "message")
    private List<WebElement> errors;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void visitPage() {
        this.driver.get("http://wa.toad.cz/~beliadan/semestralka/login.php");
    }

    public void setEmail(String email){
        this.email.sendKeys(email);
    }

    public void setPassword(String password){
        this.password.sendKeys(password);
    }

    public void sendForm() {
        this.login_btn.click();
    }

    public WebElement getEmail_error() {
        if (errors.size()==2) {
            return errors.get(0);
        } else {
            return null;
        }
    }

    public WebElement getPassword_error() {
        if (errors.size()==2) {
            return errors.get(1);
        } else {
            return errors.get(0);
        }
    }
}
