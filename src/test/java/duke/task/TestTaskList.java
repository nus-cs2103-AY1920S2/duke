package duke.task;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class TestTaskList {
    @Test
    public void testSize() throws Exception {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());

        taskList.add(new Todo("dummy todo"));
        assertEquals(1, taskList.size());

        taskList = new TaskList(IntStream.range(0, 20)
                .mapToObj(x -> new Todo("dummy todo"))
                .collect(Collectors.toList()));
        assertEquals(20, taskList.size());

        taskList.remove(0);
        assertEquals(19, taskList.size());
    }

    @Test
    public void testGet_validIndex_success() throws Exception {
        TaskList taskList = new TaskList();
        Todo todo1 = new Todo("todo 1");
        Todo todo2 = new Todo("todo 2");
        Todo todo3 = new Todo("todo 3");
        taskList.add(todo1);
        taskList.add(todo2);
        taskList.add(todo3);
        assertEquals(todo1, taskList.get(0));
        assertEquals(todo2, taskList.get(1));
        assertEquals(todo3, taskList.get(2));
    }

    @Test
    public void testGet_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        Todo todo1 = new Todo("todo 1");
        Todo todo2 = new Todo("todo 2");
        taskList.add(todo1);
        taskList.add(todo2);
        try {
            assertNull(taskList.get(2));
            fail();
        } catch (Exception e) {
            assertEquals("The task of index 3 cannot be found in your list.", e.getMessage());
        }
    }

    @Test
    public void testRemove_validIndex_success() throws Exception {
        TaskList taskList = new TaskList();
        Todo todo1 = new Todo("todo 1");
        Todo todo2 = new Todo("todo 2");
        Todo todo3 = new Todo("todo 3");
        taskList.add(todo1);
        taskList.add(todo2);
        taskList.add(todo3);
        assertEquals(todo2, taskList.remove(1));
        assertEquals(todo3, taskList.remove(1));
    }

    @Test
    public void testRemove_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        Todo todo1 = new Todo("todo 1");
        Todo todo2 = new Todo("todo 2");
        taskList.add(todo1);
        taskList.add(todo2);
        try {
            assertNull(taskList.remove(2));
            fail();
        } catch (Exception e) {
            assertEquals("The task of index 3 cannot be found in your list.", e.getMessage());
        }
    }

    @Test
    public void testStream() {
        List<Task> tasks =  IntStream.range(0, 4)
                .mapToObj(x -> new Todo("dummy todo"))
                .collect(Collectors.toList());
        assertArrayEquals(tasks.toArray(), new TaskList(tasks).stream().toArray());
    }
}
