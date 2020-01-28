package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;
    private List<String> list;

    @BeforeEach
    void init() {
        list = new ArrayList<>();
        list.add("T | O | borrow book");
        list.add("D | X | return book | 2000-01-02");
        list.add("E | O | project meeting | 2001-02-03");
        list.add("D | X | do homework | 2002-03-04");
        taskList = new TaskList(list);
    }

    @Test
    void isEmpty() {
        assertFalse(taskList.isEmpty());

        taskList = new TaskList();
        assertTrue(taskList.isEmpty());
    }

    @Test
    void size() {
        assertEquals(4, taskList.size());

        taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    void remove() {
        assertEquals(4, taskList.size());
        taskList.remove(0);
        assertEquals(3, taskList.size());
    }
}
