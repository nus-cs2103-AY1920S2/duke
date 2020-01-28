import java.util.Scanner;

public class Ui {

    public final static String NEWLINE = System.lineSeparator();
    public final static String INDENT = "    ";
    public final static String BORDER = INDENT + "____________________________________________________________";

    public Ui() { }

    public void showWelcome() {
        String logo = INDENT + "  _____  __    __  _____" + NEWLINE
                + INDENT + " |  ___||  \\  /  ||  ___|" + NEWLINE
                + INDENT + " | |__   \\  \\/  / | |__" + NEWLINE
                + INDENT + " |  __|   |    |  |  __|" + NEWLINE
                + INDENT + " | |___  /  /\\  \\ | |___" + NEWLINE
                + INDENT + " |_____||__/  \\__\\|_____|" + NEWLINE;
        System.out.println(BORDER);
        System.out.println(logo);
        System.out.println(INDENT + "  Hello! I'm EXE, I'll execute anything on your command! :)");
        System.out.println(INDENT + "  What do you want to exe?");
        System.out.println(BORDER);
    }

    public void showStorageError() {
        System.out.println(addBorder("Unable to create storage file/directory\n" +
                "Please create a data directory and Duke.txt in it."));
    }

    public void showLoadingError(DukeException e) {
        System.out.println(addBorder(e.toString()));
    }

    public void showDateTimeException() {
        System.out.println(addBorder(INDENT + "Please type date in this format yyyy-MM-dd," + " including dashes"));
    }

    public void showIndexOutOfBoundException(int listSize) {
        System.out.println(addBorder(INDENT + "List only have " + listSize + " of tasks, please choose within range."));
    }

    public void showStandardError(DukeException dukeEx) {
        System.out.println(addBorder(dukeEx.toString()));
    }

    public void showDeleteMessage(Task deletedTask, int listSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("      Noted. I've removed this task:\n");
        stringBuilder.append(INDENT + "   " + deletedTask.toString() + "\n");
        stringBuilder.append(String.format("      Now you have %d task(s) in the list.", listSize));
        System.out.println(addBorder(stringBuilder.toString()));
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // for bye and adding stuff into list
    public String addBorder(String message) {
        return BORDER + NEWLINE + message + NEWLINE + BORDER;
    }

    // for printing list
    public void showListMessage(TaskList listOfTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + "  Here are the tasks in your list:" + NEWLINE);
        Task currentTask;
        for (int i = 0; i < listOfTask.getSize(); i++) {
            currentTask = listOfTask.getTask(i);
            stringBuilder.append(INDENT + "  ");
            stringBuilder.append(String.format(" %d. %s" + NEWLINE, i + 1, currentTask.toString()));
        }
        stringBuilder
                .append(String.format("%s  In case you can't count! you have %d task(s) in your list",
                        INDENT, listOfTask.getSize()));
        System.out.println(addBorder(stringBuilder.toString()));
    }

    public void showCustomiseMessage(String message, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " ");
        stringBuilder.append(" Got it. I've added this task:" + NEWLINE);
        stringBuilder.append(INDENT + " ");
        stringBuilder.append("  " + message + NEWLINE);
        stringBuilder.append(INDENT + " ");
        stringBuilder.append(String.format(" Now you have %d tasks in list.", size));
        System.out.println(addBorder(stringBuilder.toString()));
    }

    public void showDoneMessage(String markedIcon, Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BORDER + NEWLINE);
        stringBuilder.append(INDENT);
        stringBuilder.append("  Very productive! I've slayed this task:" + NEWLINE);
        stringBuilder.append(INDENT);
        stringBuilder
                .append(String.format("   [%s] %s" + NEWLINE, markedIcon, task.getDescription() + " " + task.getTime()));
        stringBuilder.append(BORDER);
        System.out.println(stringBuilder.toString());
    }

    public void showGoodByeMessage() {
        System.out.println(addBorder(INDENT + "  Goodbye and have a beautiful time!"));
    }



}
