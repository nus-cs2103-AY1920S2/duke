package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testStatus() {
        Task task = new TaskStub("test");
        assertEquals("\u2718", task.getStatus());
        task.markAsDone();
        assertEquals("\u2713", task.getStatus());
    }
}

class TaskStub extends Task {
    public TaskStub(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }
}