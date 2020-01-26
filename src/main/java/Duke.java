import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList lst;
    private Ui ui;
    private Parser parser;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.lst = storage.load();
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke("src/main/data/tasks.ser").run();
    }

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
                        int indexCut = line.indexOf("/at");
                        String desc = line.substring(0, indexCut - 1);
                        String at = line.substring(indexCut + 4);
                        TaskDate td = new TaskDate(at);
                        Event event = new Event(desc, td);
                        lst.addTask(event);
                        ui.showAddTask(event, lst.getSize());
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
                    if (lst.deleteTask(getNumber - 1)) {
                        Task task = lst.getTask(getNumber - 1);
                        ui.showDeleteTask(task, lst.getSize());
                    }
                    storage.save(lst);

                } else {
                    throw new DukeException("Invalid Input");
                }

            } catch (DukeException e) {
                ui.showErrInvalidInput();
            }
            getInput = sc.next();
        }
    }
}
