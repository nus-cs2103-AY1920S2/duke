/**
 * intro()
 * showLoadingError()
 * inputLoop()
 */

import java.util.Scanner;

public class Ui{
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void intro() {
        System.out.println("Oi\n" + logo + "\n" +
                "     What you waaaaaant?\n" +
                "    _________________________________");
    }

    public void showLoadingError(){
        System.out.println("System failed to load file. Opening with a new blank file");
    }

    public String input() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public void output(String out){
        System.out.println(out);
    }


        /*
        Command cmd = Parser.of(inputAsArray);
        switch(cmd){
            case TODO:
                System.out.println("Affirmative. Adding a to-do task :)");
                return Command.TODO;
            case DEADLINE:
                System.out.println("Affirmative. Adding a task with a deadline. :)");
                return Command.DEADLINE;
            case EVENT:
                System.out.println("Affirmative. Adding an event. :)");
                return Command.EVENT;
            case DONE:
                System.out.println("Completed the task");
                return Command.DONE;
            case LIST:
                System.out.println("printing List");
                return Command.LIST;
            case DELETE:
                System.out.println("Deleting task");
                return Command.DELETE;
            case BYE:
                System.out.println("Goodbye. See you soon");
                return Command.BYE;
            default:
                System.out.println("What you talking? Me no understand your command :(");
                return input();
        }
        */
}