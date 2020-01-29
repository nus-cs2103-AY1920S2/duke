import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void printWelcome() {
        String logo = "\t _____             _\n"
                + "\t|  __ \\           | |\n"
                + "\t| |  | |_   _  ___| |__   ___  ___ ___\n"
                + "\t| |  | | | | |/ __| '_ \\ / _ \\/ __/ __|\n"
                + "\t| |__| | |_| | (__| | | |  __/\\__ \\__ \\\n"
                + "\t|_____/ \\__,_|\\___|_| |_|\\___||___/___/";
        this.print("Hello from\n" + logo);
        this.print("My name is Duchess, as you can see above.");
        this.print("How may I help you?");
        this.printLine();
    }

    public void printGoodbye() {
        this.print("Bye, is it? Shoo shoo then.");
    }

    public void printLine() {
        System.out.println("\t" + new String(new char[65]).replace("\0", "\u2501"));
    }

    public void print(String output) {
        System.out.println("\t" + output);
    }

    public void printTask(String taskInString) {
        System.out.println("\t\t" + taskInString);
    }

    public void printTask(Task task) {
        System.out.println("\t\t" + task);
    }

    public void printTaskList(TaskList taskList) {
        if (taskList.size() > 0) {
            this.print("Sighs... you never remember what you say, don't you.");
            this.print("You said these:");
            for (int i = 0; i < taskList.size(); i++) {
                this.printTask((i + 1) + ".\t" + taskList.getTask(i));
            }
        } else {
            this.print("Is this a trick question? You have not told me anything about 'tasks'.");
        }
    }

    public void printTaskAdded(Task task, int size) {
        this.print("As always, needing someone to keep track of things for you...");
        this.printTask(task);
        this.print("I've already tracked " + size + " " + (size == 1 ? "task" : "tasks") + " for you.");
    }

    public void printTaskDeleted(Task task, int size) {
        this.print("Great! One less thing for me to track for you.");
        this.printTask(task + " [DELETED]");
        this.print("Now I'm tracking " + size + " " + (size == 1 ? "task" : "tasks") + " for you.");
    }

    public void printError(String errorMessage) {
        System.out.println("\tStop causing me trouble...");
        System.out.println("\t" + errorMessage);
    }

    public void printLoadingError() {
        System.out.println("Failed to load saved tasks. I'll just start anew.");
    }
}
