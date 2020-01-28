import java.util.Scanner;

public class Ui {

    void showLoadingError() {
       System.out.println("Loading error");
    }

    void showWelcome() {
        System.out.println("____________________________________________________________\n Hello! I'm Duke\n What can I do for you?\n____________________________________________________________");
    }

    String readCommand() {
      Scanner sc = new Scanner(System.in);
      return sc.nextLine();
    }

    void printString(String s) {
        System.out.println(s);
    }

    void showLine() {
        System.out.println("____________________________________________________________");
    }

    void showError(String s) {
        System.out.println(s);
    }
    void printTask(int number, TaskList tasks) {
        Task ob = tasks.getList().get(number - 1);
        System.out.println(ob.toString());
    }

}
