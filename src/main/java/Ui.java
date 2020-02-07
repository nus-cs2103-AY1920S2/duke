import java.util.Scanner;

public class Ui {
    public Ui() {

    }

    public static void showWelcome(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showLoadingError() {
    }

    public void printLine() {
        System.out.println("--------------------------------");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTodoComplete(Task t, int i) {
        System.out.println("Got it. I've added this task");
        System.out.println(t.toString());
        System.out.println("Now you have " + i + " tasks in the list");
    }


}
