package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoTest {
    @Test
    public void getDescription_todo_returnsDescription() {
        assertEquals("duke", new Todo("duke").getDescription());
    }

    @Test
    public void isCompleted_incompleteTodo_returnsFalse() {
        assertFalse(new Todo("duke", false).isCompleted());
    }

    @Test
    public void isCompleted_completedTodo_returnsTrue() {
        assertTrue(new Todo("duke", true).isCompleted());
    }

    @Test
    public void complete_incompleteTodo_completesTodo() {
        assertTrue(new Todo("duke", false).complete().isCompleted());
    }

    @Test
    public void complete_completedTodo_remainsComplete() {
        assertTrue(new Todo("duke", false).complete().isCompleted());
    }

    @Test
    public void toString_todo_returnsStringRepresentation() {
        assertEquals("[T][✘] duke", new Todo("duke").toString());
    }
}
