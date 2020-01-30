package duke.other;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.task.TaskList;

import duke.task.Event;
import duke.task.Todo;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    @Test
    void saveFile() {
        ArrayList taskList = new ArrayList<>();
        LocalDate formattedDate = LocalDate.parse("2020/01/30", DateTimeFormatter.ofPattern("yyyy/M/d"));
        taskList.add(new Todo("Submit assignment", true));
        taskList.add(new Event("CNY Celebration", formattedDate, false));
        TaskList tasks = new TaskList(taskList);
        assertEquals("T>true>Submit assignment\n" + "E>false>CNY Celebration>2020-01-30\n",
                Storage.stringifyArray(tasks));
    }
}