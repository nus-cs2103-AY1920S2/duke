import org.junit.jupiter.api.Test;

class TaskListTest {
    @Test
    public void runTest() {
        TaskList lst = new TaskList();

        Task task1 = new Task("Test1");
        Task task2 = new Task("Test2");

        lst.addTask(task1);
        lst.addTask(task2);

        assert(lst.getTask(0).equals(task1));
        assert(lst.getTask(1).equals(task2));
        assert(lst.getSize() == 2);

    }

}