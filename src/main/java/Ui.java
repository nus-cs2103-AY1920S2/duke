
import java.util.Scanner;

public class Ui {

    TaskList mylist;
    Scanner sc = new Scanner(System.in);

    public Ui(TaskList tasks) {

        this.mylist = tasks;

    }

    public void showWelcome() {

        System.out.println("Hello! I'm Duke\n"
                + "     What can I do for you?");

    }

    public String readLine() {

        String response = sc.nextLine();
        return response;

    }

    public void printFormatting() {
        System.out.println("     ____________________________________________________________");
    }

    public void showError(DukeException ex) {

        System.out.println(ex.getMessage());


    }



}
