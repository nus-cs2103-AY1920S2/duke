package jiachen.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void add() {
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            tasks.add(new MockTask("hellow world"));
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
        assertEquals(tasks.getList().size(), 1);
    }

    @Test
    void get() {
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            tasks.add(new MockTask("hellow world"));
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
        assertEquals(tasks.get(0).description, "hellow world");
    }

    @Test
    void size() {
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            tasks.add(new MockTask("hellow world"));
            tasks.add(new MockTask("hellow world"));
            tasks.add(new MockTask("hellow world"));
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
        assertEquals(tasks.getList().size(), 3);
    }

    @Test
    void remove() {
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            tasks.add(new MockTask("hellow world"));
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
        assertEquals(tasks.getList().size(), 1);
        tasks.remove(0);
        assertEquals(tasks.getList().size(), 0);
    }
}
