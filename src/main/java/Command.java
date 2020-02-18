public class Command {

    private String[] command;

    /**
     * Creates a Command object.
     *
     * @param input A string representation of user's command.
     */
    public Command(String input) {
        this.command = input.split("\\s", 2);
    }


    /**
     * Executes the user's command accordingly.
     *
     * @param tasks TaskList object containing list of tasks.
     * @param ui Ui object that aid interaction with the user.
     * @param storage Storage object that stores a file with tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        switch (command[0].toLowerCase()) {
        case "bye":
            return ui.showBye();
        case "list":
            return ui.listTask(tasks);
        case "done":
            if (command.length < 2 || command[1].trim().equals("")) {
                return "OOPS!!! I'm sorry, please enter an index to mark completed!";
            } else {
                int number = Integer.parseInt(command[1].trim());
                if (number < 1 || number > tasks.getList().size()) {
                    assert number > 1 : "Invalid index, cannot be smaller than 1!";
                    assert number < tasks.getList().size() : "Invalid index, cannot be greater than list size!";
                    return "The index inputted is not in the list! Please enter a valid index!";
                }
                if (tasks.getList().get(number - 1).isDone == false) {
                    tasks.markDone(number);
                    storage.updateDone(number);
                    return ui.printDone(number, tasks);
                } else {
                    return "This task is already completed! Please do other tasks!";
                }
            }
        case "todo":
            if (command.length < 2 || command[1].trim().equals("")) {
                return "OOPS! The description of a todo cannot be empty.";
            } else {
                String taskName = command[1].trim();
                Task todo = new Todo(taskName);
                storage.addTodo(taskName);
                tasks.add(todo);
                return ui.printAdd(todo);
            }
        case "deadline":
            if (command.length < 2 || command[1].trim().equals("")) {
                return "OOPS! The description of a deadline cannot be empty.";
            } else {
                String deadline = command[1].trim();
                String[] arrDeadline = deadline.split("/by");
                if (arrDeadline.length < 2 || arrDeadline[1].trim().equals("")) {
                    return "OOPS! Please input /by Date Time (e.g. /by dd/mm/yyyy HHMM)";
                } else {
                    String timeDeadline = Parser.convertDateAndTime(arrDeadline[1].trim());
                    Task taskDeadline = new Deadline(arrDeadline[0].trim(), timeDeadline);
                    storage.addDeadline(deadline);
                    tasks.add(taskDeadline);
                    return ui.printAdd(taskDeadline);
                }
            }
        case "event":
            if (command.length < 2 || command[1].trim().equals("")) {
                return "OOPS! The description of a event cannot be empty.";
            } else {
                String event = command[1].trim();
                String[] arrEvent = event.split("/at");
                if (arrEvent.length < 2 || arrEvent[1].trim().equals("")) {
                    return "OOPS! Please input /at Date (e.g. /at dd/mm/yyyy 1500-2000)";
                } else {
                    String time = Parser.convertDateAndTime(arrEvent[1].trim());
                    Task taskEvent = new Event(arrEvent[0].trim(), time);
                    storage.addEvent(event);
                    tasks.add(taskEvent);
                    return ui.printAdd(taskEvent);
                }
            }
        case "delete":
            if (command.length < 2 || command[1].trim().equals("")) {
                return "OOPS! Please enter an index to delete.";
            } else {
                int deletionNumber = Integer.parseInt(command[1].trim());
                if (deletionNumber < 1 || deletionNumber > tasks.getList().size()) {
                    assert deletionNumber > 1 : "Invalid index, cannot be smaller than 1!";
                    assert deletionNumber < tasks.getList().size() : "Invalid index, cannot be greater than list size!";
                    return "The index inputted is not in the list! Please enter a valid index!";
                }
                storage.delete(deletionNumber);
                Task toDelete = tasks.getList().get(deletionNumber - 1);
                tasks.delete(deletionNumber);
                return ui.printDelete(toDelete);
            }
        case "find":
            if (command.length < 2 || command[1].trim().equals("")) {
                return "OOPS! The description of a find cannot be empty.";
            } else {
                String keyWord = command[1].trim();
                return ui.printFind(keyWord);
            }
        case "help":
            String instruction = "Available commands:\n"
                    + "list: displays all tasks that is recorded\n\n"
                    + "done [index]: marks the task as done\n\n"
                    + "todo [task]: adds the task to the list\n\n"
                    + "deadline /by [dd/mm/yy HHMM]: adds task with deadline to the list\n\n"
                    + "event /at [dd/mm/yy HHMM-HHMM]: adds event with date and time to the list\n\n"
                    + "delete [index]: deletes the task\n\n"
                    + "find [keyword]: finds all task that is related to the keyword\n\n"
                    + "bye: exits the application";
            return instruction;
        default:
            try {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + "Type 'help' to view available commands!");
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }
}