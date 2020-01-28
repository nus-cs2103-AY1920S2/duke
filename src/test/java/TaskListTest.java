import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TaskListTest {
    @Test
    public void addTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("1"));
        taskList.addTask(new Todo("2"));
        taskList.addTask(new Todo("3"));
        assertEquals(3, taskList.size());
    }
    
    @Test
    public void removeTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("1"));
        taskList.addTask(new Todo("2"));
        assertEquals(2, taskList.size());
        
        taskList.removeTask(1);
        taskList.removeTask(0);
        
        assertEquals(0, taskList.size());
    }

    @Test
    public void markTaskAsDone() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("1"));
        taskList.addTask(new Todo("2"));
        assertEquals(false, taskList.getTask(0).isDone());
        assertEquals(false, taskList.getTask(1).isDone());
        
        taskList.markTaskAsDone(0);
        assertEquals(true, taskList.getTask(0).isDone());
        assertEquals(false, taskList.getTask(1).isDone());
        
        taskList.markTaskAsDone(1);
        assertEquals(true, taskList.getTask(0).isDone());
        assertEquals(true, taskList.getTask(1).isDone());
    }
}
