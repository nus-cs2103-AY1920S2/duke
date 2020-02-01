import java.time.DateTimeException;
import java.util.Scanner;

/** Main class. */
public class Duke {

    private Storage storage;
    private TaskList lst;
    private Ui ui;
    private Parser parser;

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

        while (true) {
            try {
                String command = parser.parse(getInput);

                if (command.equals("bye")) {
                    ui.showBye();
                    break;

                } else if (command.equals("list")) {
                    ui.showList(lst);

                } else if (command.equals("find")) {
                    String toFind = sc.nextLine();
                    TaskList tempLst = lst.findMatchingTasks(toFind);
                    ui.showList(tempLst);

                } else if (command.equals("add")) {
                    String line = sc.nextLine();
                    if (getInput.equals("todo")) {
                        Todo todo = new Todo(line);
                        lst.addTask(todo);
                        ui.showAddTask(todo, lst.getSize());
                    } else if (getInput.equals("deadline")) {
                        int indexCut = line.indexOf("/by");
                        String desc = line.substring(0, indexCut - 1);
                        String by = line.substring(indexCut + 4);
                        TaskDate td = new TaskDate(by);
                        Deadline deadline = new Deadline(desc, td);
                        lst.addTask(deadline);
                        ui.showAddTask(deadline, lst.getSize());
                    } else {
                        TaskDate tdEnd = null;

                        boolean isValid = false;

                        while (!isValid) {
                            try {
                                System.out.println("Event end date and time: ");
                                String endDate = sc.nextLine();
                                tdEnd = new TaskDate(endDate);
                                isValid = true;

                                int indexCut = line.indexOf("/at");
                                String desc = line.substring(0, indexCut - 1);
                                String at = line.substring(indexCut + 4);
                                TaskDate tdStart = new TaskDate(at);
                                Event event = new Event(desc, tdStart, tdEnd);
                                lst.addTask(event);
                                ui.showAddTask(event, lst.getSize());

                            } catch(ArrayIndexOutOfBoundsException e) {
                                System.err.println("Invalid input, please follow the format {dd/mm/yyyy hhmm}");
                            } catch(DateTimeException e) {
                                System.err.println("Invalid date!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }



                    }
                    storage.save(lst);

                } else if (command.equals("done")) {
                    String getNumberString = sc.next();
                    int getNumber = Integer.valueOf(getNumberString);
                    if (lst.doneTask(getNumber - 1)) {
                        Task task = lst.getTask(getNumber - 1);
                        ui.showDoneTask(task);
                    };
                    storage.save(lst);

                } else if (command.equals("delete")) {
                    String getNumberString = sc.next();
                    int getNumber = Integer.valueOf(getNumberString);

                    Task task = lst.getTask(getNumber - 1);
                    if (lst.deleteTask(getNumber - 1)) {
                        ui.showDeleteTask(task, lst.getSize());
                    }
                    storage.save(lst);

                } else {
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
            getInput = sc.next();
        }
    }
}
