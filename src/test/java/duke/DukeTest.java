package duke;

import duke.tool.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DukeTest {

    @Test
    public void testDelete() {
        String[] actionsList = new String[]{"todo sleep 100 years", "todo toBeDeleted", "delete 2", "list"};

        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("tasks_test.txt", taskList);
        UI ui = new UI();
        storage.clearFile();

        for (String action : actionsList) {
            Command c = parser.parse(action);
            c.execute(taskList, ui);
            storage.saveToFile();
        }

        String filePath = "tasks_test.txt";
        String actual = storage.readAllBytes(filePath);
        String expected = "T|X|sleep 100 years\n";

        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        assertEquals(expected, actual);
    }

    @Test
    public void testDone() {
        String[] actionsList = new String[]{"todo sleep 100 years", "event fireworks /at 2019-10-12 20:00 to 2019-10-12 21:00", "done 1", "list"};
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        UI ui = new UI();
        StringBuilder outputString = new StringBuilder();

        for (String action : actionsList) {
            Command c = parser.parse(action);
            outputString.append(c.execute(taskList, ui));
        }

        String actual = outputString.toString();
        String expected = "Got it. I've added this task:\n" +
                "[T][X] sleep 100 years\n" +
                "Now you have 1 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "[E][X] fireworks (at: 12 Oct. 2019 20:00HRS to 12 Oct. 2019 21:00HRS)\n" +
                "Now you have 2 tasks in the list.\n" +
                "Nice! I've marked this task as done:\n" +
                "[T][V] sleep 100 years\n" +
                "Here are the tasks in your list:\n" +
                "1.[T][V] sleep 100 years\n" +
                "2.[E][X] fireworks (at: 12 Oct. 2019 20:00HRS to 12 Oct. 2019 21:00HRS)\n";

        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        assertEquals(expected, actual);
    }

    @Test
    public void testTasks() {
        String[] actionsList = new String[]{"todo study 24 hours", "deadline get six packs /by 2020-02-29 09:00", "event holidaysss /at 2020-05-01 20:00 to 2019-08-01 21:00", "done 2", "list"};
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        UI ui = new UI();
        StringBuilder outputString = new StringBuilder();

        for (String action : actionsList) {
            Command c = parser.parse(action);
            outputString.append(c.execute(taskList, ui));
        }

        String actual = outputString.toString();
        String expected = "Got it. I've added this task:\n" +
                "[T][X] study 24 hours\n" +
                "Now you have 1 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "[D][X] get six packs (by: 29 Feb. 2020 09:00HRS)\n" +
                "Now you have 2 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "[E][X] holidaysss (at: 01 May 2020 20:00HRS to 01 Aug. 2019 21:00HRS)\n" +
                "Now you have 3 tasks in the list.\n" +
                "Nice! I've marked this task as done:\n" +
                "[D][V] get six packs (by: 29 Feb. 2020 09:00HRS)\n" +
                "Here are the tasks in your list:\n" +
                "1.[T][X] study 24 hours\n" +
                "2.[D][V] get six packs (by: 29 Feb. 2020 09:00HRS)\n" +
                "3.[E][X] holidaysss (at: 01 May 2020 20:00HRS to 01 Aug. 2019 21:00HRS)\n";

        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        assertEquals(expected, actual);
    }

    @Test
    public void testFind() {
        String[] actionsList = new String[]{"todo study 24 hours", "deadline get six packs /by 2020-02-29 09:00", "event holidaysss /at 2020-05-01 20:00 to 2020-08-01 20:00", "done 1", "find 2020"};
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        UI ui = new UI();
        StringBuilder outputString = new StringBuilder();

        for (String action : actionsList) {
            Command c = parser.parse(action);
            outputString.append(c.execute(taskList, ui));
        }

        String actual = outputString.toString();
        String expected = "Got it. I've added this task:\n" +
                "[T][X] study 24 hours\n" +
                "Now you have 1 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "[D][X] get six packs (by: 29 Feb. 2020 09:00HRS)\n" +
                "Now you have 2 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "[E][X] holidaysss (at: 01 May 2020 20:00HRS to 01 Aug. 2020 20:00HRS)\n" +
                "Now you have 3 tasks in the list.\n" +
                "Nice! I've marked this task as done:\n" +
                "[T][V] study 24 hours\n" +
                "Here are the matching tasks in your list:\n" +
                "1.[D][X] get six packs (by: 29 Feb. 2020 09:00HRS)\n" +
                "2.[E][X] holidaysss (at: 01 May 2020 20:00HRS to 01 Aug. 2020 20:00HRS)\n";

        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        assertEquals(expected, actual);
    }

    @Test
    public void testList() {
        String[] actionsList = new String[]{"todo study 24 hours", "deadline get six packs /by 2020-02-29 09:00", "event holidaysss /at 2020-05-01 20:00 to 2020-08-01 20:00", "done 3", "list"};
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        UI ui = new UI();
        StringBuilder outputString = new StringBuilder();

        for (String action : actionsList) {
            Command c = parser.parse(action);
            outputString.append(c.execute(taskList, ui));
        }

        String actual = outputString.toString();
        String expected = "Got it. I've added this task:\n" +
                "[T][X] study 24 hours\n" +
                "Now you have 1 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "[D][X] get six packs (by: 29 Feb. 2020 09:00HRS)\n" +
                "Now you have 2 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "[E][X] holidaysss (at: 01 May 2020 20:00HRS to 01 Aug. 2020 20:00HRS)\n" +
                "Now you have 3 tasks in the list.\n" +
                "Nice! I've marked this task as done:\n" +
                "[E][V] holidaysss (at: 01 May 2020 20:00HRS to 01 Aug. 2020 20:00HRS)\n" +
                "Here are the tasks in your list:\n" +
                "1.[T][X] study 24 hours\n" +
                "2.[D][X] get six packs (by: 29 Feb. 2020 09:00HRS)\n" +
                "3.[E][V] holidaysss (at: 01 May 2020 20:00HRS to 01 Aug. 2020 20:00HRS)\n";

        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        assertEquals(expected, actual);
    }

    @Test
    public void testHelp() {
        String[] actionsList = new String[]{"help"};
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        UI ui = new UI();
        StringBuilder outputString = new StringBuilder();

        for (String action : actionsList) {
            Command c = parser.parse(action);
            outputString.append(c.execute(taskList, ui));
        }

        String actual = outputString.toString();
        String expected = "List of available commands: todo, event, deadline, list, sort, find, done, delete, bye\n\n" +
                "Detailed user guide is at: https://shengxue97.github.io/duke/\n";

        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        assertEquals(expected, actual);
    }
}
