package jiachen.duke;

public class Ui {
    public void printHeader() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public void print(String message) {
        printSeparator();
        System.out.println("\t" + message);
        printSeparator();
    }

    public void printError(String errorMessage) {
        print(errorMessage);
    }

    public void printRemoveTask(Task task) {
        printSeparator();
        System.out.println("\t Noted. I've removed this task: ");
        System.out.println("\t\t" + task);
        printSeparator();
    }

    public void printDoneTask(Task task) {
        printSeparator();
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.println("\t\t" + task);
        printSeparator();
    }

    public void printTasks(TaskList tasks) {
        printSeparator();
        for (int i = 1; i <= tasks.getList().size(); i++) {
            System.out.println("\t " + i + ". " + tasks.get(i - 1));
        }
        printSeparator();
    }

    public void printNewTask(Task task, int numOfTasks) {
        printSeparator();
        System.out.println("\t Got it. I've added this task: \n" +
                "\t\t" + task + "\n" +
                "\t Now you have " + numOfTasks + " tasks in the list.");
        printSeparator();
    }

    public void printLoadingError() {
        System.out.println("\tERR: unable to load file from disk!\n");
    }

    private void printSeparator() {
        System.out.println("\t____________________________________________________________");
    }
}
