package dude.component;

import java.util.Scanner;

/**
 * Console-based implementation of IUserInterface interface.
 * Input is obtained via a Scanner(System.in) and output uses System.out.println under the hood.
 */
public class UI implements IUserInterface {
    private Scanner input;

    /**
     * Greets the user.
     */
    public UI() {
        this.input = new Scanner(System.in);
        respond("Wassup dude!");
    }

    /**
     * Closes the Scanner used to read user input to prevent an (unlikely) resource leak.
     */
    public void close() {
        this.input.close();
    }

    /**
     * Reads user input from stdin.
     *
     * @return command user gives to Dude as a String to be parsed by Parser.
     */
    public String readCommand() {
        return this.input.nextLine();
    }

    public void respond(Runnable r) {
        String line = "    ____________________________________________________________";
        System.out.println(line);
        r.run();
        System.out.println(line);
    }

    public void respond(String... responses) {
        respond(() -> {
            for (String response : responses) {
                speak(response);
            }
        });
    }

    public void respondParsingError(String errorMsg, String... usageMsgs) {
        respond(() -> {
            speak(errorMsg);
            speak("Just tell me what you want to do like this:" + System.lineSeparator());
            for (String usageMsg : usageMsgs) {
                speak("  " + usageMsg);
            }
            speak("");
            speak("Then we're chill");
        });
    }

    public void speak(String str) {
        System.out.println("     " + str);
    }
}
