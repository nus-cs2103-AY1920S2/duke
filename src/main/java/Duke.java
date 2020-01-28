import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    enum Command {
        BYE, DEADLINE, DELETE, DONE, EVENT, LIST, TODO, CALENDAR, CLEAR
    }
    static String[] tokens;

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }
    public void run() {
        boolean done = false;

        String param;
        String[] params;
        Command command;
        int index;
        Task t;
        ui.start();
        while (true) {
            try {
                tokens = ui.getInput().split(" ", 2);
                command = Command.valueOf(tokens[0].toUpperCase());
                if (tokens.length > 1) {
                    param = tokens[1];
                } else {
                    param = "";
                }
                switch (command) {
                    case CLEAR:
                        if (taskList.clearTasks()) {
                            ui.tasksCleared();
                        }
                        break;
                    case TODO:
                        if (param.equals("")) {
                            throw new MissingDescriptionException();
                        }
                        t = new ToDo(param);
                        if (addTask(t)) {
                            ui.taskAdded(t, taskList);
                        }
                        break;
                    case DEADLINE:
                        if (param.equals("")) {
                            throw new MissingDescriptionException();
                        } else if (!param.contains(" /by ")) {
                            throw new MissingDeadlineParamException();
                        }
                        params = param.split(" /by ");
                        t = new Deadline(params[0], params[1]);
                        if (addTask(t)) {
                            ui.taskAdded(t, taskList);
                        }
                        break;
                    case EVENT:
                        if (param.equals("")) {
                            throw new MissingDescriptionException();
                        } else if (!param.contains(" /at ")) {
                            throw new MissingEventParamException();
                        }
                        params = param.split(" /at ");
                        t = new Event(params[0], params[1]);
                        if (addTask(t)) {
                            ui.taskAdded(t, taskList);
                        }
                        break;
                    case BYE:
                        ui.end();
                        done = true;
                        break;
                    case LIST:
                        if (taskList.size() == 0) {
                            ui.printNoTasks();
                        } else {
                            ui.printTasks(taskList.printTaskList());
                        }
                        break;
                    case DONE:
                        ui.taskMarkedAsDone(taskList.markAsDone(tokens[1]));
                        break;
                    case DELETE:
                        ui.taskDeleted(taskList.deleteTask(tokens[1]), taskList);
                        break;
                    case CALENDAR:
                        showCalendar(param);
                        break;
                    default:
                        throw new UnknownCommandException();
                }
                if (done)
                    break;
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void showCalendar(String calendarDateString) {
        LocalDate calendarDate = LocalDate.parse(calendarDateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
        List<String> calendarList = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getClass().equals(Deadline.class)) {
                LocalDate taskDate = ((Deadline) task).by.toLocalDate();
                if (taskDate.equals(calendarDate)) {
                    calendarList.add(task.toString());
                }
            } else if (task.getClass().equals(Event.class)) {
                LocalDate taskDate = ((Event) task).at.toLocalDate();
                if (taskDate.equals(calendarDate)) {
                    calendarList.add(task.toString());
                }
            }
        }
        if (calendarList.size() == 0) {
            ui.calendarNoItems();
        } else {
            ui.calendarDisplayItems(calendarDate, taskList.printTaskList());
        }
    }


    private boolean addTask(Task t) {
        return taskList.addTask(t) >= 0;
    }

}
