package dukelist;

import dukeexceptions.DukeException;
import duketasks.Deadline;
import duketasks.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeListTest {
    @Test
    public void getNumOfTasksTest() {
        DukeList test = new DukeList();
        test.addTask(new Todo("feed cat"));
        test.addTask(new Deadline("2103 IP", LocalDate.parse(("2020-01-30"))));
        assertEquals(2, test.numOfTasks);
    }

    @Test
    public void markAsDoneTest() {
        DukeList test = new DukeList();
        Todo feedCat = new Todo("feed cat");
        Deadline IP = new Deadline("2103 IP", LocalDate.parse(("2020-01-28")));
        Event bday = new Event("birthday", LocalDate.parse("2020-01-30"));
        test.addTask(feedCat);
        test.addTask(IP);
        test.addTask(bday);
        try {
            assertEquals(bday, test.markTaskAsDone(3));
        } catch (DukeException de) {
        }

    }
}


