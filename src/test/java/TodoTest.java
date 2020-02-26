import bot.command.exception.InadequateArgumentsException;

import bot.task.Todo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodoTest {
    @Test
    public void createTodo_withDescription_success() {
        Todo td = new Todo("return book");
        assertEquals(td.getTaskDetails(), "return book");
    }

    @Test
    public void markAsDone_newTodo_success() {
        Todo td = new Todo("buy groceries");
        assertFalse(td.isDone());
        td.markAsDone();
        assertTrue(td.isDone());
    }
}
