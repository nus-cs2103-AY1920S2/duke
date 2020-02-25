package duke;

import java.util.ArrayList;

/**
 * A class which scans the user input and decides which task to be carried out.
 */
public class Parser {
    private String input;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor of the class Parser.
     *
     * @param ui The UI used for Duke.
     * @param taskList A list of tasks.
     */
    public Parser(Ui ui, TaskList taskList, String input) {
        this.input = input;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Scans the user inputs and calls necessary methods based on the command input by the user.
     */
    public String run() {
        if (this.input.equals("bye")) {
            return ui.bye();
        } else if (this.input.equals("hello") || this.input.equals("hi")) {
            return ui.greet();
        } else if (this.input.equals("list")) {
            return ui.list(taskList);
        } else {
            try {
                int i = this.input.indexOf(' ');
                String command = input.substring(0, i);
                if (command.equals("delete")) {
                    int n = Integer.parseInt(input.substring(i + 1));
                    return taskList.delete(n);
                } else if (command.equals("done")) {
                    int n = Integer.parseInt(input.substring(i + 1));
                    return taskList.done(n);
                } else if (command.equals("undone")) {
                    int n = Integer.parseInt(input.substring(i + 1));
                    return taskList.undone(n);
                } else if (command.equals("todo")) {
                    String desc = input.substring(i);
                    return taskList.addTodo(desc);
                } else if (command.equals("deadline")) {
                    String desc = input.substring(i);
                    return taskList.addDeadline(desc);
                } else if (command.equals("event")) {
                    String desc = input.substring(i);
                    return taskList.addEvent(desc);
                } else if (command.equals("find")) {
                    String desc = input.substring(i);
                    ArrayList<Task> foundList = taskList.find(desc);
                    if (foundList.isEmpty()) {
                        return ui.showTaskNotFound();
                    } else {
                        return ui.findList(new TaskList(foundList, ui));
                    }
                } else if (command.equals("sort")) {
                    String desc = input.substring(i);
                    return taskList.sort(desc);
                } else {
                    return ui.showInvalidCommand();
                }
            } catch (java.lang.StringIndexOutOfBoundsException e) {
                if (input.equals("todo")) {
                    return ui.showEmptyTodoDesc();
                } else if (input.equals("deadline")) {
                    return ui.showEmptyDeadlineDesc();
                } else if (input.equals("event")) {
                    return ui.showEmptyEventDesc();
                } else if (input.equals("sort")) {
                    return ui.showInvalidSortDesc();
                } else {
                    return ui.showInvalidCommand();
                }
            }
        }
    }
}
