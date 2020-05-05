package hakunamatata.command;

import hakunamatata.HakunaMatataException;
import hakunamatata.Storage;
import hakunamatata.Ui;
import hakunamatata.task.Task;
import hakunamatata.task.TaskList;
import hakunamatata.task.ToDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    @Test
    public void deleteCommandTest_wrongIndexRange_throwHakunaMatataException() {
        HakunaMatataException actualException1 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new ToDo("test"));
            TaskList testList = new TaskList(tasks);
            new DeleteCommand(3).execute(testList, new Ui(), new Storage("tasks.txt"));
        });
        assertEquals("deleteWrongIndexRange", actualException1.getMessage());

        HakunaMatataException actualException2 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new ToDo("test"));
            TaskList testList = new TaskList(tasks);
            new DeleteCommand(-1).execute(testList, new Ui(), new Storage("tasks.txt"));
        });
        assertEquals("deleteWrongIndexRange", actualException2.getMessage());
    }
}
