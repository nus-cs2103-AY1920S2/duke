import java.time.DateTimeException;
import java.util.Scanner;

/** Main class. */
public class Duke {

    private Storage storage;
    private TaskList lst;
    private Ui ui;
    private Parser parser;
    private Factory factory;

    /**
     * Constructor for Duke class. Ui handles user interaction. Storage stores and loads Tasklist from persistent storage.
     * TaskList stores tasks and provide functions to maintain these tasks. Parser parses input.
     *
     * @param filepath path where TaskList is stored.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.lst = storage.load();
        this.parser = new Parser();
        this.factory = new Factory();
    }

    public static void main(String[] args) {
        new Duke("src/main/data/tasks.ser").run();
    }

    /**
     * Runs the duke bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();

        String getInput = null;
        getInput = sc.next();

        boolean isRunning = true;

        while (isRunning) {
            try {
                Command command = parser.parse(getInput);
                switch (command) {
                case BYE:
                    ui.showBye();
                    isRunning = false;
                    break;
                case LIST:
                    ui.showList(lst);
                    break;
                case FIND:
                    String toFind = sc.nextLine();
                    TaskList tempLst = lst.findMatchingTasks(toFind);
                    ui.showList(tempLst);
                    break;
                case ADD:
                    Task task = null;
                    String line = sc.nextLine();
                    if (getInput.equals("todo")) {
                        task = factory.createTodo(line);
                    } else if (getInput.equals("deadline")) {
                        task = factory.createDeadline(line);
                    } else {
                        task = factory.createEvent(line);
                    }
                    lst.addTask(task);
                    ui.showAddTask(task, lst.getSize());
                    storage.save(lst);
                    break;
                case DONE:
                    String getDoneString = sc.next();
                    lst.doneTask(getDoneString);
                    ui.showDoneTask(lst.getTaskFromString(getDoneString));
                    storage.save(lst);
                    break;
                case DELETE:
                    String getDeleteString = sc.next();
                    ui.showDeleteTask(lst.getTaskFromString(getDeleteString),lst.getSize() - 1);
                    lst.deleteTask(getDeleteString);
                    storage.save(lst);
                    break;
                default:
                    throw new DukeException("Invalid Input");
                }
            } catch (DukeException e) {
                ui.showErrInvalidInput();
            } catch (StringIndexOutOfBoundsException e) {
                ui.showErrInvalidInput();
            } catch (IndexOutOfBoundsException e) {
                ui.showErrInvalidInput();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (isRunning) {
                getInput = sc.next();
            }

        }
    }
}
