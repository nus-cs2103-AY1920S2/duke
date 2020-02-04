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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (command[0].toLowerCase()) {
        case "bye":
            ui.showBye();
            break;
        case "list":
            ui.listTask(tasks);
            break;
        case "done":
            if (command.length < 2 || command[1].trim().equals("")) {
                System.out.println("\tOOPS!!! I'm sorry, please enter an index to mark completed!");
            } else {
                int number = Integer.parseInt(command[1].trim());
                if (number < 1 || number > tasks.getList().size()) {
                    System.out.println("\tThe index inputted is not in the list! Please enter a valid index!");
                    break;
                }
                if (tasks.getList().get(number - 1).isDone == false) {
                    tasks.markDone(number);
                    ui.printDone(number, tasks);
                    storage.updateDone(number);
                } else {
                    System.out.println("\tThis task is already completed! Please do other tasks!");
                }
            }
            break;
        case "todo":
            if (command.length < 2 || command[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a todo cannot be empty.");
            } else {
                String taskName = command[1].trim();
                Task t = new Todo(taskName);
                storage.addTodo(taskName);
                tasks.add(t);
                ui.printAdd(t);
            }
            break;
        case "deadline":
            if (command.length < 2 || command[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a deadline cannot be empty.");
            } else {
                String deadline = command[1].trim();
                String[] arrDeadline = deadline.split("/by");
                if (arrDeadline.length < 2 || arrDeadline[1].trim().equals("")) {
                    System.out.println("\tOOPS! Please input /by Date Time (e.g. /by dd/mm/yyyy HHMM)");
                } else {
                    String timeDeadline = Parser.convertDateAndTime(arrDeadline[1].trim());
                    Task taskDeadline = new Deadline(arrDeadline[0].trim(), timeDeadline);
                    storage.addDeadline(deadline);
                    tasks.add(taskDeadline);
                    ui.printAdd(taskDeadline);
                }
            }
            break;
        case "event":
            if (command.length < 2 || command[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a event cannot be empty.");
            } else {
                String event = command[1].trim();
                String[] arrEvent = event.split("/at");
                if (arrEvent.length < 2 || arrEvent[1].trim().equals("")) {
                    System.out.println("\tOOPS! Please input /at Date (e.g. /at dd/mm/yyyy 1500-2000)");
                } else {
                    String time = Parser.convertDateAndTime(arrEvent[1].trim());
                    Task taskEvent = new Event(arrEvent[0].trim(), time);
                    storage.addEvent(event);
                    tasks.add(taskEvent);
                    ui.printAdd(taskEvent);
                }
            }
            break;
        case "delete":
            if (command.length < 2 || command[1].trim().equals("")) {
                System.out.println("\tOOPS! Please enter an index to delete.");
            } else {
                int deletionNumber = Integer.parseInt(command[1].trim());
                if (deletionNumber < 1 || deletionNumber > tasks.getList().size()) {
                    System.out.println("\tThe index inputted is not in the list! Please enter a valid index!");
                    break;
                }
                storage.delete(deletionNumber);
                Task toDelete = tasks.getList().get(deletionNumber - 1);
                tasks.delete(deletionNumber);
                ui.printDelete(toDelete);
            }
            break;
        case "find":
            if (command.length < 2 || command[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a find cannot be empty.");
            } else {
                String keyWord = command[1].trim();
                ui.printFind(keyWord);
            }
            break;
        default:
            try {
                throw new DukeException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Gets a boolean (true/false) depending on user's command.
     *
     * @return true if user's command is "bye".
     */
    public boolean isExit() {
        boolean flag = false;
        if (command.equals("bye")) {
            flag = true;
        }
        return flag;
    }
}