import bot.command.exception.InadequateArgumentsException;

import bot.task.Todo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodoTest {
    @Test(expected = InadequateArgumentsException.class)
    public void createTodo_noArguments_exceptionThrown() throws InadequateArgumentsException {
        new Todo("todo");
    }

    @Test
    public void createTodo_withDescription_success() throws InadequateArgumentsException {
        Todo td = new Todo("todo return book");
        assertEquals(td.getTaskDetails(), "return book");
    }

    @Test
    public void markAsDone_newTodo_success() throws InadequateArgumentsException {
        Todo td = new Todo("todo buy groceries");
        assertFalse(td.isDone());
        td.markAsDone();
        assertTrue(td.isDone());
    }
}
