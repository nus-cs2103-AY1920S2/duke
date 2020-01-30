import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TaskListTest {

    @Test
    void findTaskTest_keywordBook_listOfTaskWithBookInDescription() {
        List<Task> listOfTasks = new ArrayList<>();
        List<Task> expectedList = new ArrayList<>();
        ToDo toDo = new ToDo("read book");
        Deadline deadline = new Deadline("return book", LocalDate.parse("2020-01-30"));
        Event event = new Event("abcdef", LocalDate.parse("2020-01-29"));
        listOfTasks.add(toDo);
        listOfTasks.add(deadline);
        listOfTasks.add(event);
        expectedList.add(toDo);
        expectedList.add(deadline);
        TaskList taskListTest = new TaskList(listOfTasks);
        List<Task> actualResult = taskListTest.findTask("book");
        assertArrayEquals(expectedList.toArray(), actualResult.toArray());
    }
}