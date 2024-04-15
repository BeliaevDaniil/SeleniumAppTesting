import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {
    private final WebDriver driver;

    //Page Elements
    @FindBy(css="h1")
    private WebElement logo_title;
    @FindBy(className = "btn_create")
    private WebElement create_an_account_btn;
    @FindBy(className = "btn_dark")
    private WebElement dark_theme_btn;
    @FindBy(className = "btn_light")
    private WebElement light_theme_btn;
    @FindBy(css = "body")
    private WebElement body;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void visitPage() {
        this.driver.get("http://wa.toad.cz/~beliadan/semestralka/index.php");
    }

    public String getLogoTitle() {
        return logo_title.getAttribute("textContent");
    }

    public void switchDarkTheme(){
        dark_theme_btn.click();
    }

    public void switchLightTheme(){
        light_theme_btn.click();
    }

    public String getBodyColor(){
        return body.getCssValue("color");
    }

    public void sendForm() {
        create_an_account_btn.click();
    }
}
