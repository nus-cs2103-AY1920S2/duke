import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public final static String LINE = "__________________________________________";
    private static Storage storage;
    private Ui ui;
    private TaskList tasks;

    Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            // shown when current saved task list in the txt file is empty
            tasks = new TaskList();
            // therefore, there is a need to make a new task list.
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DateTimeParseException, FileNotFoundException, DukeException {
        new Duke("data/duke.txt").run();
        List<Task> list = storage.load();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            try {
                String command = scan.nextLine();
                String[] arrOfCommands = command.split(" ");
                Task task = new Task(command);

                if (command.equals("bye")) {
                    storage.save(list);
                    System.out.println(horizontalLine
                            + "\n"
                            + "Bye-bye! See you again, my friend!"
                            + "\n"
                            + horizontalLine);
                    break;
                } else if (command.equals("list")) {
                    System.out.println("list" + "\n" + horizontalLine + "\n" + "Here are your tasks!");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    System.out.println(horizontalLine);

                } else if (arrOfCommands[0].equals("done")) {
                    int num = Integer.parseInt(arrOfCommands[1]);
                    Task doneTask = list.get(num - 1);
                    storage.save(list);
                    System.out.println(horizontalLine + "\n" + "Fantastic! This task is a done-deal!" + "\n");
                    doneTask.markAsDone();
                    System.out.println(doneTask + "\n" + horizontalLine);

                } else if (command.startsWith("deadline")) {
                    String commandWithoutDeadline = command.substring(9);
                    String[] commands = commandWithoutDeadline.split("/by");
                    LocalDate localDate = LocalDate.parse(commands[1].trim());
                    Deadline deadline = new Deadline(commands[0], localDate);
                    list.add(deadline);
                    storage.save(list);
                    System.out.println(horizontalLine + "\n" + "Alright, I've added this task:" + "\n");
                    System.out.println(deadline + "\n");
                    System.out.println("You currently have " + list.size() + " task(s) in the list.");
                    System.out.println(horizontalLine);

                } else if (command.startsWith("todo")) {
                    String[] commands = command.split("todo ");
                    try {
                        if (commands.length >= 2) {
                            ToDo toDo = new ToDo(commands[1]);
                            list.add(toDo);
                            storage.save(list);
                            System.out.println(horizontalLine + "\n" + "Alright, I've added this task:" + "\n");
                            System.out.println(toDo + "\n");
                            System.out.println("You currently have " + list.size() + " task(s) in the list.");
                            System.out.println(horizontalLine);
                        } else {
                            throw new DukeException("");
                        }
                    } catch (DukeException e) {
                        System.err.println(horizontalLine);
                        System.err.println("There is no valid input after to do. Please try again.");
                        System.err.println(horizontalLine);
                    }
                } else if (command.startsWith("event")) {
                    String commandWithoutEvent = command.substring(6);
                    String[] commands = commandWithoutEvent.split("/at");
                    LocalDate localDate = LocalDate.parse(commands[1].trim());
                    Event event = new Event(commands[0], localDate);
                    list.add(event);
                    storage.save(list);
                    System.out.println(horizontalLine + "\n" + "Alright, I've added this task:" + "\n");
                    System.out.println(event + "\n");
                    System.out.println("You currently have " + list.size() + " task(s) in the list.");
                    System.out.println(horizontalLine);
                } else if (command.startsWith("delete")) {
                    String[] commands = command.split(" ");
                    Task toBeRemoved = list.get(Integer.parseInt(commands[1])-1);
                    list.remove(Integer.parseInt(commands[1])-1);
                    storage.save(list);
                    System.out.println(horizontalLine + "\n" + "Alright, I've removed this task:" + "\n");
                    System.out.println(toBeRemoved + "\n");
                    System.out.println("You currently have " + list.size() + " task(s) in the list.");
                    System.out.println(horizontalLine);
                } else { throw new DukeException("");
                }
            } catch (DukeException | IOException e) {
                System.err.println(horizontalLine);
                System.err.println("That was not a valid input. Please try again.");
                System.err.println(horizontalLine);
            } catch (DateTimeParseException e) {
                System.err.println(horizontalLine);
                System.err.println("Your date input format was invalid. " +
                        "It should be in the format of 'YYYY-MM-DD'. Please try again.");
                System.err.println(horizontalLine);
            }
        }
    }
}
