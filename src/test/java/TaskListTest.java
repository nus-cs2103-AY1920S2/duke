import duke.TaskList;
import duke.Task;
import duke.exceptions.InvalidIndexException;

/**
 * A class that tests the TaskList class.
 */
class TaskListTest {

    /**
     * Test class driver function.
     * @param args Command line arguments.
     */
    public static void main(String[] args) throws InvalidIndexException{
        TaskList testTaskList = new TaskList();
        testTaskListAdd(testTaskList);
        testTaskListDone(testTaskList);
    }

    /**
     * Tests the adding function of the TaskList.
     * @param testTaskList the TaskList that needs to be tested.
     */
    static void testTaskListAdd(TaskList testTaskList) {
        for (int i = 0; i < 10; i++) {
            Task testTask = new Task("test case " + i);
            testTaskList.addTask(testTask);
        }
        System.out.println(testTaskList.list());
    }

    /**
     * Tests the done function of the TaskList.
     * @param testTaskList the TaskList that needs to be tested.
     * @throws InvalidIndexException the invalid index to delete.
     */
    static void testTaskListDone(TaskList testTaskList) throws InvalidIndexException {
        for (int i = 0; i < 10; i++) {
            testTaskList.done(i + 1);
        }
        System.out.println(testTaskList.list());
    }


}