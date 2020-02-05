package duke;

public class Parser {
    protected String input;
    protected TaskList taskList;
    protected Ui ui = new Ui();

    public Parser(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    public void readCommand() {
        String arr[] = this.input.split(" ", 2);

        if (input.equals("bye")) {
            Storage storage = new Storage("data/duke.txt");
            storage.store(taskList);
            ui.showGoodbye();
            System.exit(0);

        } else if (input.equals("list")) {
            ui.showList(taskList);

        } else if (arr[0].equals("done")) {

            try {
                checkNum(arr.length);
                checkValid(arr[1]);
                int taskNum = Integer.parseInt(arr[1]) - 1;

                if (taskList.tasks.size() > taskNum) {
                    Task current = taskList.tasks.get(taskNum);
                    current.markDone();
                    ui.showDone(current);

                } else {
                    ui.showTaskError();
                }

            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (arr[0].equals("delete")) {
            taskList.delete(arr);

        } else {
            try {
                checkAction(arr[0]);
                taskList.add(arr);
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void checkValid(String input) throws DukeException {
        String[] arr = input.split(" ");
        Ui ui = new Ui();

        if (arr.length > 1) {
            throw new DukeException(ui.showValidError());
        }
    }

    public static void checkNum(int size) throws DukeException {
        Ui ui = new Ui();

        if (size < 2) {
            throw new DukeException(ui.showNumError());
        }
    }

    public static void checkAction(String action) throws DukeException {
        Ui ui = new Ui();

        if (!action.equals("todo") && !action.equals("deadline") && !action.equals("event")) {
            throw new DukeException(ui.showActionError());
        }
    }
}
