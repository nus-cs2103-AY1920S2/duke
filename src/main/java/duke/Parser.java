package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static void parse(Storage storage, TaskList tasks, Ui ui) {
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = ui.readCommand();
                String[] inputSplit = input.split(" ", 2);

                Command cmd = Command.getCommand(inputSplit[0]);

                switch (cmd) {
                    case BYE:
                        Ui.printMessage("Bye! Hope you visit again soon!");
                        isRunning = false;
                        break;
                    case LIST:
                        tasks.displayTasks();
                        break;
                    case DONE:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! Missing command parameters!");
                        } else {
                            int index = Integer.valueOf(inputSplit[1]) - 1;
                            if (index < 0 || index >= tasks.getTasks().size()) {
                                throw new OutOfRangeException();
                            } else {
                                Task task = tasks.getTasks().get(index);
                                task.markAsDone();
                                Ui.printMessage("Nice! I've marked this task as done:\n\t" + task.toString());
                                storage.storeTasks(tasks.getTasks());
                            }
                        }
                        break;
                    case DELETE:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! Missing command parameters!");
                        } else {
                            int index = Integer.valueOf(inputSplit[1]) - 1;
                            if (index < 0 || index >= tasks.getTasks().size()) {
                                throw new OutOfRangeException();
                            } else {
                                Task taskToDelete = tasks.getTasks().get(index);
                                tasks.getTasks().remove(index);
                                Ui.printMessage("Noted! I've removed this task:\n\t\t" + taskToDelete.toString() +
                                        "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");

                                storage.storeTasks(tasks.getTasks());
                            }
                        }
                        break;
                    case DEADLINE:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! The description of a deadline cannot " +
                                    "be empty!");
                        } else {
                            String[] arguments = inputSplit[1].split(" /by ", 2);
                            if (arguments.length < 2) {
                                throw new InsufficientArgumentsException("☹ OOPS!!! Missing deadline parameters!");
                            } else {
                                try {
                                    Task newTask = new Deadline(arguments[0], LocalDate.parse(arguments[1]));
                                    tasks.getTasks().add(newTask);
                                    Ui.printMessage("Got it! I've added the task:\n\t\t" + newTask.toString() +
                                            "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
                                } catch (DateTimeParseException ex) {
                                    Ui.printMessage("☹ OOPS!!! Please enter a valid date: YYYY-MM-DD");
                                }

                                storage.storeTasks(tasks.getTasks());
                            }
                        }
                        break;
                    case EVENT:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! The description of an event cannot" +
                                    " be empty");
                        } else {
                            String[] arguments = inputSplit[1].split(" /at ", 2);
                            if (arguments.length < 2) {
                                throw new InsufficientArgumentsException("☹ OOPS!!! Missing event parameters!");
                            } else {
                                try {
                                    Task newTask = new Event(arguments[0], LocalDate.parse(arguments[1]));
                                    tasks.getTasks().add(newTask);
                                    Ui.printMessage("Got it! I've added the task:\n\t\t" + newTask.toString() +
                                            "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
                                } catch (DateTimeParseException ex) {
                                    Ui.printMessage("☹ OOPS!!! Please enter a valid date: YYYY-MM-DD");
                                }

                                storage.storeTasks(tasks.getTasks());
                            }
                        }
                        break;
                    case TODO:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! The description of a to-do cannot be " +
                                    "empty");
                        } else {
                            Task newTask = new Todo(inputSplit[1]);
                            tasks.getTasks().add(newTask);
                            Ui.printMessage("Got it! I've added the task:\n\t\t" + newTask.toString() +
                                    "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");

                            storage.storeTasks(tasks.getTasks());
                        }
                        break;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (DukeException ex) {
                Ui.printMessage(ex.getMessage());
            }
        }
    }
}
