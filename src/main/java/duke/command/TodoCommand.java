package duke.command;

import duke.other.DukeException;
import duke.other.Ui;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {
    private String instruction;
    private String details;

    public TodoCommand(String instruction, String details) {
        this.instruction = instruction;
        this.details = details;
    }

    /**
     * Handles the Todo command and its details. Then the Todo task will be added into the TaskList.
     *
     * @param taskList Overall TaskList of all the Tasks
     * @throws DukeException If details of Todo is invalid(i.e. insufficient arguments)
     */
    public String execute(TaskList taskList, Ui ui){
        String[] replyArr = details.split(" ");
        String replyWoSpace = "";
        for (int i = 1; i < replyArr.length; i++) {
            replyWoSpace += replyArr[i] + " ";
        }
        if (!replyWoSpace.equals("")) {
            Todo toDo = new Todo(replyWoSpace, false);
            taskList.addTask(toDo);
            return Ui.showTaskAdded(toDo, taskList);
        } else {
            return Ui.todoInputError();
        }
    }


}
