public class AddCommand extends Command {

    protected String op;
    protected String description;

    public AddCommand(String op, String description) {
        this.op = op;
        this.description = description;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        String[] task_date;
        switch (op) {
            case "todo":
                task = new ToDo(description);
                tasks.addTask(task);
                ui.showMessages(new String[]{"Got it. I've added this task:", " " + task.toString(),
                        "Now you have " + tasks.getSize() + " tasks in the list."});
                break;
            case "deadline":
                task_date = description.split(" /by ");
                if (task_date.length == 1 || task_date[0].trim().equals("")
                        || task_date[1].trim().equals("")) {
                    throw new DukeException("OOPS!!! The description or by of a deadline cannot be empty.");
                }
                task = new Deadline(task_date[0], task_date[1]);
                tasks.addTask(task);
                ui.showMessages(new String[]{"Got it. I've added this task:", " " + task.toString(),
                        "Now you have " + tasks.getSize() + " tasks in the list."});
                break;
            case "event":
                task_date = description.split(" /at ");
                if (task_date.length == 1 || task_date[0].trim().equals("")
                        || task_date[1].trim().equals("")) {
                    throw new DukeException("OOPS!!! The description or at of an event cannot be empty.");
                }
                task = new Event(task_date[0], task_date[1]);
                tasks.addTask(task);
                ui.showMessages(new String[]{"Got it. I've added this task:", " " + task.toString(),
                        "Now you have " + tasks.getSize() + " tasks in the list."});
                break;
        }
    }
}
