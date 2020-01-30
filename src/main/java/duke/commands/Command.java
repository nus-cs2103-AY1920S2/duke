package duke.commands;

import duke.tasks.*;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;

public class Command {
    public enum Commands {
        TASK("task"), TODO("todo"), EVENT("event"), DEADLINE("deadline"), LIST("list"), BYE("bye");

        private String command;

        Commands(String command) {
            this.command = command;
        }

        public String getCommand() {
            return this.command;
        }
    }

    private String[] elements;
    private String commandWord;
    private String fullInput;
    private boolean isExit = false;

    public Command(String fullInput) {
        this.elements = fullInput.split(" ");
        this.commandWord = elements[0];
        this.fullInput = fullInput;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        switch (this.commandWord) {
        case "bye":
            ui.showMessage("Aww okay, see you next time!");
            this.isExit = true;
            break;
        case "list":
            if (taskList.getTaskList().size() < 1) {
                ui.showMessage("no tasks");
                break;
            } else {
                System.out.println("Here are the tasks in your list:");
                int index = 1;
                String output = "";
                for (Task t : taskList.getTaskList()) {
                    output = index + ". " + t.toString();
                    System.out.println(output);
                    index++;
                }
            }
            break;
        case "done": {
            if (this.elements.length <= 1) {
                throw new DukeException("OOPS boi. Need moar arguments!!");
            }
            try {
                Integer.parseInt(this.elements[1]);
            } catch (Exception e) {
                throw new DukeException("Input a number boi.");
            }
            if (Integer.parseInt(this.elements[1]) > taskList.getTaskList().size()) {
                throw new DukeException("Boi. You don't have that many tasks boi! :)");
            }

            int taskNum = Integer.parseInt(this.elements[1]);
            taskList.getTaskList().get(taskNum - 1).setDone();
            ui.showMessage(
                    "Nice! I've marked this task as done:\n" + taskList.getTaskList().get(taskNum - 1).toString());
            break;
        }

        case "delete": {
            if (this.elements.length <= 1) {
                throw new DukeException("OOPS boi. Need moar arguments!!");
            }
            try {
                Integer.parseInt(this.elements[1]);
            } catch (Exception e) {
                throw new DukeException("Input a number boi.");
            }
            if (Integer.parseInt(this.elements[1]) > taskList.getTaskList().size()) {
                throw new DukeException("Boi. You don't have that many tasks boi! :)");
            }

            int taskNum = Integer.parseInt(elements[1]);
            System.out.println(
                    "Boi. I've went and deleted that task \n" + taskList.getTaskList().get(taskNum - 1).toString());
            taskList.getTaskList().remove(taskNum - 1);
            break;
        }

        case "todo":
            if (this.elements.length <= 1) {
                throw new DukeException("OOPS boi. Need moar arguments!!");
            }
            Task task = new Todo(this.fullInput.substring(5)); // stores input to storedText List
            TaskList.addTask(task, taskList);
            ui.showMessage("I've added the todo to the list!");
            break;

        case "deadline": {
            String[] deadlineElements = this.fullInput.split("/by");
            if (deadlineElements.length <= 1) {
                throw new DukeException("OOPS boi. Need moar arguments!!");
            }
            String deadlineDescription = deadlineElements[0].substring(9);
            String by = deadlineElements[1].substring(1);
            Task deadline = new Deadline(deadlineDescription, by);
            TaskList.addTask(deadline, taskList);
            ui.showMessage("I've added the deadline to the list!");
            break;
        }

        case "event": {
            String[] eventElements = this.fullInput.split("/at");
            if (eventElements.length <= 1) {
                throw new DukeException("OOPS boi. Need moar arguments!!");
            }
            String eventDescription = eventElements[0].substring(6);
            String at = eventElements[1].substring(1);
            Task event = new Event(eventDescription, at);
            TaskList.addTask(event, taskList);
            ui.showMessage("I've added the event to the list!");
            break;
        }

        default:
            throw new DukeException("unknown command!!!");
        }
    }
}
