import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo myTodoTest = new Todo("Eating");
        assertEquals(myTodoTest.toString(), "[T][N] Eating");
    }
}