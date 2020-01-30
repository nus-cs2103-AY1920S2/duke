import java.io.File;
import java.time.LocalDate;

/**
 * This is a simulation of a chat bot called Duke.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        this.tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.printLogo();
        ui.printGreet();

        String input = null;
        while (true) {
            input = ui.getInput();

            if (input.equals("bye")) {
                ui.printExit();
                break;
            }

            if (input.equals("list")) {
                ui.list(tasks);
            } else {
                String[] words = input.split(" ");
                switch (words[0]) {
                    case "done":
                        try {
                            ui.markDone(tasks.getTask(Integer.valueOf(words[1]) - 1));
                        } catch (IndexOutOfBoundsException e) {
                            ui.printException("    OOP!!! The number of tasks you have is only "
                                    + tasks.getTaskNumber());
                        }
                        break;
                    case "delete":
                        try {
                            ui.delete(tasks.getTask(Integer.valueOf(words[1]) - 1), tasks);
                        } catch (IndexOutOfBoundsException e) {
                            ui.printException("    OOP!!! The number of tasks you have is only "
                                    + tasks.getTaskNumber());
                        }
                        break;
                    case "todo":
                        try {
                            ui.add(new Todo(input.substring(5)), tasks);
                        } catch (IndexOutOfBoundsException e) {
                            ui.printException("    OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;
                    case "deadline":
                        String[] ddlDetails = input.substring(9).split(" /by ");
                        try {
                            ui.add(new Deadline(ddlDetails[0], LocalDate.parse(ddlDetails[1])), tasks);
                        } catch (Exception e) {
                            ui.printException("    Input time should be \" /by yyyy-mm-dd\"");
                        }
                        break;
                    case "event":
                        String[] eventDetails = input.substring(6).split(" /at ");
                        try {
                            ui.add(new Event(eventDetails[0], LocalDate.parse(eventDetails[1])), tasks);
                        } catch (Exception e) {
                            ui.printException("    Input time should be \" /at yyyy-mm-dd\"");
                        }
                        break;
                    default:
                        ui.printException("    OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                storage.save(tasks);
            }
        }
    }


    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String path = dir + File.separator + "data" + File.separator + "duke.txt";

        new Duke(path).run();
    }
}
