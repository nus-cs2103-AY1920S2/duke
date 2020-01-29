/**
 * Ui:
 * Deals with receiving input and returning output to the user
 * Receives instructions from user, and return an output accordingly
 * inputLoop()
 * Wait for Parser to return a Command
 * Print according to Command
 *
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

    public void inputLoop() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] inputAsArray = input.split(" ");
            //Command cmd = Command.convert(inputAsArray[0]);
            //will add actual task
            Command cmd = Parser.of(inputAsArray);
            switch(cmd){
                case TODO:
                    System.out.println("Affirmative. Adding a to-do task :)");
                    break;
                case DEADLINE:
                    System.out.println("Affirmative. Adding a task with a deadline. :)");
                    break;
                case EVENT:
                    System.out.println("Affirmative. Adding an event. :)");
                    break;
                case DONE:
                    System.out.println("Completed the task");
                    break;
                case LIST:
                    System.out.println("printing List");
                    break;
                case DELETE:
                    System.out.println("Deleting task");
                    break;
                case BYE:
                    System.out.println("Goodbye. See you soon");
                    break;
                default:
                    System.out.println("What you talking? Me no understand your command :(");
                    break;
            }
            if(cmd == Command.BYE){break;}
        }
    }
}