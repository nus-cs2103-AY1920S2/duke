package collection;

import collection.TaskCollection;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskCollectionTest {

    @Test
    public void testGetMethod() {
        TaskCollection tc = new TaskCollection();
        tc.add(new Task("a"));
        assertEquals("a", tc.get(0).getDescription(), "get correct task in collection");
    }

    @Test
    public void testRemoveMethod() {
        TaskCollection tc = new TaskCollection();
        tc.add(new Task("a"));
        tc.remove(0);
        assertEquals(0, tc.size(), "remove successfully");
    }
}
