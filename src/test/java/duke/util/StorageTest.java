package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    /*
    @Test
    public void testLoad() throws IOException, DukeException {
        File temp = File.createTempFile("temp", ".txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        String testInput = "[E][X] school dance /at 2020-01-20 1800\n" +
                            "[T][O] eat food\n" +
                            "[D][X] study /by 2020-02-18 1700\n";
        out.write(testInput);
        temp.deleteOnExit();
        out.close();
        ArrayList<Task> test = new ArrayList<>();
        test.add(new Event("school dance", "2020-01-20 1800"));
        test.add(new ToDo("eat food"));
        test.add(new Deadline("study", "2020-02-18 1700"));
        assertEquals(test, new Storage(temp.toString()).load());
    }

    @Test
    public void testWrite() throws IOException, DukeWriteFailException {
        File temp = File.createTempFile("temp", ".txt");
        temp.deleteOnExit();
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        out.write("");
        Storage test = new Storage(temp.toString());

        ArrayList<Task> testArrayList = new ArrayList<>();
        Event t1 = new Event("school dance", "2020-01-20 1800");
        ToDo t2 = new ToDo("eat food");
        Deadline t3 = new Deadline("study", "2020-02-18 1700");
        t1.setDone();
        t3.setDone();
        testArrayList.add(t1);
        testArrayList.add(t2);
        testArrayList.add(t3);
        TaskList testTaskList = new TaskList(testArrayList);

        test.write(testTaskList);

        List<String> expectedText = Arrays.asList("[E][X] school dance /at 20 January 2020 1800\n",
                                                    "[T][O] eat food\n",
                                                    "[D][X] study /by 18 February 2020 1700\n");
        System.out.println(expectedText.toString());
        out.close();
        assertEquals(expectedText, Files.readAllLines(temp.toPath()));
    }

     */
}
