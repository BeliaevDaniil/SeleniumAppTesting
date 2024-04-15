import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class ToDoListPage {
    private final WebDriver driver;

    //Page Elements
    @FindBy(id="task")
    private WebElement write_task_input;
    @FindBy(className = "sendTask")
    private WebElement add_task_button;
    @FindBy(className = "task_plate")
    private List<WebElement> added_tasks;
    @FindBy(className = "task_plate_crossed")
    private List<WebElement> added_tasks_crossed;
    @FindBy(className = "del")
    private WebElement delete_task_button;
    @FindBy(className = "done")
    private WebElement done_task_button;
    @FindBy(className = "upd")
    private WebElement edit_task_button;
    @FindBy(id="updateTask")
    private WebElement edit_task;
    @FindBy(className = "save")
    private WebElement save_task_button;

    public ToDoListPage(WebDriver driver) {
        this.driver = driver;
        added_tasks = new ArrayList<>();
        PageFactory.initElements(driver, this);
    }

    public void visitPage() {
        this.driver.get("http://wa.toad.cz/~beliadan/semestralka/todo.php");
    }

    public void addTask(String text) {
        this.write_task_input.sendKeys(text);
        this.add_task_button.click();
    }

    public void editTask(String text) {
        this.edit_task.clear();
        this.edit_task.sendKeys(text);
    }

    public void pressDeleteTaskButton(){
        this.delete_task_button.click();
    }

    public void pressCompleteTaskButton(){
        this.done_task_button.click();
    }

    public void pressEditTaskButton(){
        this.edit_task_button.click();
    }

    public void pressSaveTaskButton(){
        this.save_task_button.click();
    }

    public List<WebElement> getAdded_tasks() {
        return added_tasks;
    }

    public List<WebElement> getAdded_tasks_crossed() {
        return added_tasks_crossed;
    }

    public void deleteAllTasks(){
        if ((!this.getAdded_tasks().isEmpty())||(!this.getAdded_tasks_crossed().isEmpty())) {
            int added_tasks_size = this.added_tasks.size();
            int added_crossed_tasks_size = this.added_tasks_crossed.size();
            for (int i = 0; i < added_tasks_size; i++) {
                pressDeleteTaskButton();
            }
            for (int i = 0; i < added_crossed_tasks_size; i++) {
                pressDeleteTaskButton();
            }
        }
    }
}
