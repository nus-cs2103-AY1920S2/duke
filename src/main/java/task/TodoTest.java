package task;

public class TodoTest {

    /**
     * Main class of the TodoTest serving the purpose of testing Todo.class.
     *
     * @param args command line input
     * @throws Exception thrown in terms of storage error
     */
    public static void main(String[] args) throws Exception {

        //test setup
        Todo t = new Todo("TestTodoCase");

        //test case 1
        // automatically verify the response
        if (!t.toString().equals("[T][✗] TestTodoCase")) {
            throw new Error("case 1 failed - toString()");
        }

        //test case 2
        if (!t.toStringFileFormat().equals("T | 0 | TestTodoCase")) {
            throw new Error("case 2 failed - toStringFileFormat()");
        }

        t.markAsDone();

        //test case 3
        if (!t.toString().equals("[T][✓] TestTodoCase")) {
            throw new Error("case 3 failed - toString() after markAsDone()");
        }

        System.out.println("All tests passed");
    }
}
