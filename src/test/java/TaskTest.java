import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    public void runTest() {
        Task task = new Task("Test");
        assert(task.getStatusIcon().equals("\u2718"));
        task.setDone(true);
        assert(task.getStatusIcon().equals("\u2713"));

    }

}