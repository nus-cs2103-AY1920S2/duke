import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // ui.showLine(); // show the divider line ("_______")
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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
/*
    private static void deleteTask(ArrayList<Task> tasks, String userInput) throws DukeException, IOException {
        if (userInput.equals("delete")) {
            throw new DukeException(userInput);
        }
        int taskNumber = Character.getNumericValue(userInput.charAt(7));
        if (taskNumber > tasks.size()) {
            throw new DukeException("List size");
        }
        Task t = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        System.out.println(INDENT + HOR_LINE);
        System.out.println(INDENT + "This task has successfully been deleted:");
        System.out.println(INDENT + "  " + t);
        System.out.println(INDENT + "You have " + tasks.size() + " tasks remaining.");
        System.out.println(INDENT + HOR_LINE);
        //saveTasks(tasks);
    }

    private static void completeTask(ArrayList<Task> tasks, String userInput) throws DukeException, IOException {
        if (userInput.equals("done")) {
            throw new DukeException(userInput);
        }
        int taskNumber = Character.getNumericValue(userInput.charAt(5));
        if (taskNumber > tasks.size()) {
            throw new DukeException("List size");
        }
        Task t = tasks.get(taskNumber - 1);
        t.markAsDone();
        System.out.println(INDENT + HOR_LINE);
        System.out.println(INDENT + "This task is marked as completed:");
        System.out.println(INDENT + "  " + t);
        System.out.println(INDENT + HOR_LINE);
        //saveTasks(tasks);
    }

    private static void printList(ArrayList<Task> tasks) {
        System.out.println(INDENT + HOR_LINE);
        System.out.println(INDENT + "This is your list of tasks:");

        if (tasks.size() == 0) {
            System.out.println(INDENT + "Your list is currently empty.");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(INDENT + (i+1) + "." + t);
        }
        System.out.println(INDENT + HOR_LINE);
    }

     */

    private static Date parseDate(String date) throws ParseException {
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return simpleDateFormat.parse(date);
    }

    private static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        String dateTimePattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return localDateTime;
    }
}