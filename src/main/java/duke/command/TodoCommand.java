package duke.command;

import duke.other.DukeException;
import duke.other.Ui;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDate;

public class TodoCommand extends Command {
    private String instruction;
    private String details;
    private java.time.LocalDate date;

    /**
     * Creates a TodoCommand.
     * @param details details oft eh instruction
     */
    public TodoCommand(String details) {
        this.details = details;
    }

    /**
     * Handles the Todo command and its details. Then the Todo task will be added into the TaskList.
     *
     * @param taskList Overall TaskList of all the Tasks
     * @throws DukeException If details of Todo is invalid(i.e. insufficient arguments)
     */
    public String execute(TaskList taskList, Ui ui) {
        String[] replyArr = details.split(" ");
        String replyWoSpace = "";
        for (int i = 0; i < replyArr.length; i++) {
            replyWoSpace += replyArr[i] + " ";
        }
        System.out.println("THIS IS" + replyWoSpace.length() + "!!!");
        System.out.println("THIS IS" + replyWoSpace.trim().length() + "!!!");

        if (replyWoSpace.trim().length() != 0) {
            Todo toDo = new Todo(replyWoSpace, false, LocalDate.MAX);
            taskList.addTask(toDo);
            return Ui.showTaskAdded(toDo, taskList);
        } else {
            return Ui.todoInputError();
        }
    }
}
