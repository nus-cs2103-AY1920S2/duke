import duke.*;
import duke.task.*;
import duke.command.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DukeTest {

    private static final String LIST_TEST = "list";
    private static final String TODO_TEST = "todo read book";
    private static final String DEADLINE_TEST = "deadline pao /by 02/02/2020 1800";
    private static final String EVENT_TEST = "event siewmai /by 12/12/2020 1800";


    public String list() {
        return LIST_TEST;
    }

    public void testTodo() {
        Todo todo = new Todo("todo", TODO_TEST);
        assert(todo.toString().equals("[T][N]  read book"));
    }

    public void testDeadline() throws DukeException {
        Deadline deadline = new Deadline("deadline", DEADLINE_TEST);
        assert(deadline.toString().equals("[D][N]  pao (by: 02 Feb 2020 6:00 PM)"));
    }

    public void testEvent() throws DukeException {
        Event event = new Event("event", EVENT_TEST);
        assert(event.toString().equals("[E][N]  siewmai (by: 12 Dec 2020 6:00 PM)"));
    }

    public void testTaskList() throws DukeException {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.add("todo", TODO_TEST);
        assert(taskList.getData().size() == 1);
        taskList.delete("1");
        assert(taskList.getData().size() == 0);
    }
}
