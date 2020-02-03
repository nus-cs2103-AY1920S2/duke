import java.util.Scanner;

public class Ui {

    String showLoadingError() {
        return "Loading error";
    }

    String showWelcome() {
        return "____________________________________________________________\n "
                + "Hello! I'm Duke\n What can I do for you?\n"
                + "____________________________________________________________";
    }

    String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    String showLine() {
        return "____________________________________________________________";
    }

    String showError(String s) {
        return s;
    }

    String printTask(int number, TaskList tasks) {
        Task ob = tasks.getList().get(number - 1);
        return ob.toString();
    }

}