import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    public void runTest() {
        Task task = new Task("Test");
        assert(task.getStatusIcon().equals("X"));
        task.setDone(true);
        assert(task.getStatusIcon().equals("O"));

    }

}