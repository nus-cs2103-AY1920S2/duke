package duke.main;

import java.util.Scanner;
import duke.exception.DukeException;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public String getInput() {
        return scanner.nextLine();
    }

    public String getCommand() {
        return scanner.nextLine();
    }

    public void showDukeError(DukeException e) {
        System.err.println(e);
    }

    public void showDateTimeError() {
        System.err.println(Constant.ERROR_LINE + "\n    ☹ DATE FORMAT is yyyy/mm/dd!\n" + Constant.SPACE
                + "  TIME FORMAT is HHmm!\n" + Constant.ERROR_LINE);
    }

    public void reply(String reply) {
        System.out.println(Constant.LINE);
        System.out.println(Constant.SPACE + reply);
        System.out.println(Constant.LINE);
    }

    public void goodBye() {
        System.out.println(Constant.LINE);
        System.out.println(Constant.SPACE + "Yes. FINALLY. Hope never to see you again!");
        System.out.println(Constant.LINE);
    }

    public String sayHi() {
        
        return "Arghhhh... It's you again.";
        
    }
}