import java.util.Scanner;

public class Ui {
    public Ui() {

    }

    public void showLoadingError() {
        System.out.println("There is an error in loading the file.");
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("Is that anything that I can do for you?");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printMarkDone(TaskList t, int num) {
        System.out.println("Got it. I've marked task #" + num + ": 「" + t.getDukeList().get(num - 1) + "」 as done.");
        System.out.println("You currently have " + t.getSize() + " task(s) in your list.");
    }

    public void printTaskAdded(TaskList tList, Task t) {
        System.out.println("Got it. I've added 「" + t + "」 to your task(s).");
        System.out.println("You currently have " + tList.getSize() + " task(s) in your list.");

    }

    public void printTaskRemoved(TaskList t, int num) {
        System.out.println("Got it. I've removed task #" + num + ": 「" + t.getDukeList().get(num - 1) + "」.");
        System.out.println("You currently have " + (t.getSize()-1) + " task(s) in your list.");
    }

    public void goodbye() {
        System.out.println("さらbye. Hope to see you again soon! ( ﾟ▽ﾟ)/");
    }

}