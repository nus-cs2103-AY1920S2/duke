package cathulhu;

import cathulhu.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    /**
     * Tests if the getTask method works as expected
     */
    public void getTaskTest() {

        String response;
        TaskList tl = new TaskList();
        tl.addTask(new Task("qwerty"));
        tl.addTask(new Task("asdfgh"));
        tl.addTask(new Task("zxcvbn"));
        response = tl.getTask(1).toString();

        assertEquals("[N] asdfgh", response);
    }

    @Test
    /**
     * Tests if the size method works as expected
     */
    public void sizeTest() {

        String response;
        TaskList tl = new TaskList();
        response = String.valueOf(tl.size());
        tl.addTask(new Task("qwerty"));
        tl.addTask(new Task("asdfgh"));
        tl.addTask(new Task("zxcvbn"));
        response += String.valueOf(tl.size());

        assertEquals("03", response);
    }


}
