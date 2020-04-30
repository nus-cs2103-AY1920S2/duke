package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void dukeTodoTest() {
        ToDo test = new ToDo("go out", "");
        assertEquals("[T][✘] go out", test.toString());
    }
}
