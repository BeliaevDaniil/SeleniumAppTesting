import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToDoListPageTest {
    private ToDoListPage toDoListPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/daniilbelaev/Desktop/webDriverForTS/chromedriver_mac64/chromedriver");
        WebDriver driver = new ChromeDriver();
        LogInPage logInPage = new LogInPage(driver);
        logInPage.visitPage();
        logInPage.setEmail("test@gmail.com");
        logInPage.setPassword("123456");
        logInPage.sendForm();
        toDoListPage = new ToDoListPage(driver);
        toDoListPage.visitPage();
        toDoListPage.deleteAllTasks();
    }

    @Test
    public void addTasksTest(){
        //Adding tasks
        toDoListPage.addTask("buy milk");
        toDoListPage.addTask("do homework");
        toDoListPage.addTask("go to the gym");
        //Expected results
        String expTask1 = "buy milk";
        String expTask2 = "do homework";
        String expTask3 = "go to the gym";
        //Actual results
        String resTask1 = toDoListPage.getAdded_tasks().get(0).getAttribute("value");
        String resTask2 = toDoListPage.getAdded_tasks().get(1).getAttribute("value");
        String resTask3 = toDoListPage.getAdded_tasks().get(2).getAttribute("value");
        //Assertions
        Assertions.assertEquals(expTask1, resTask1);
        Assertions.assertEquals(expTask2, resTask2);
        Assertions.assertEquals(expTask3, resTask3);
    }

    @Test
    public void deleteTasksTest(){
        //Adding tasks
        toDoListPage.addTask("buy milk");
        toDoListPage.addTask("do homework");
        toDoListPage.addTask("go to the gym");
        //Deleting tasks
        for (int i = 0; i < 3; i++) {
            toDoListPage.pressDeleteTaskButton();
        }
        //Expected results
        int expCountOfTasks = 0;
        //Actual results
        int resCountOfTasks = toDoListPage.getAdded_tasks().size();
        //Assertions
        Assertions.assertEquals(expCountOfTasks, resCountOfTasks);
    }

    @Test
    public void markTaskCompleted(){
        //Adding tasks
        toDoListPage.addTask("buy milk");
        toDoListPage.addTask("do homework");
        toDoListPage.addTask("go to the gym");
        //Marking tasks completed
        for (int i = 0; i < 3; i++) {
            toDoListPage.pressCompleteTaskButton();
        }
        //Expected results
        boolean expTasksAreMarkedCompleted = true;
        //Actual results
        boolean resTasksAreMarkedCompleted = (toDoListPage.getAdded_tasks_crossed() != null);
        //Assertions
        Assertions.assertEquals(expTasksAreMarkedCompleted, resTasksAreMarkedCompleted);
    }

    @Test
    public void editTaskTest(){
        //Adding tasks
        toDoListPage.addTask("buy milk");
        //Editing task
        toDoListPage.pressEditTaskButton();
        toDoListPage.editTask("buy beer");
        toDoListPage.pressSaveTaskButton();
        //Expected results
        String expTaskText = "buy beer";
        //Actual results
        String resTaskText = toDoListPage.getAdded_tasks().get(0).getAttribute("value");
        //Assertions
        Assertions.assertEquals(expTaskText,resTaskText);
    }}
