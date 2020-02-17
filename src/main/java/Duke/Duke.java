package Duke;

import commands.CommandHub;
import commands.CommandInvoker;
import commands.UserCommand;
import exceptions.DukeException;

import java.util.Scanner;

public class Duke {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        DukeInit.initBot();
        CommandInvoker invoker = new CommandInvoker();

        while(!CommandInvoker.checkOff()){
            String userInput = getUserInput();
            try {
                UserCommand c = CommandHub.fetchCommand(userInput);
                invoker.invoke(c);
            }
            catch(DukeException e){
                System.out.println(e.getMessage());
            }

        }

    }

    private static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String input = sc.nextLine();

        return input;
    }
}


