package dude.component;

import java.util.Scanner;

/**
 * Console-based implementation of IUserInterface interface.
 * Input is obtained via a Scanner(System.in) and output uses System.out.println under the hood.
 */
public class UI implements IUserInterface {
    private Scanner input;

    /**
     * Initializes the Scanner used to read user input from System.in and greets the user.
     */
    public UI() {
        this.input = new Scanner(System.in);
        respond("Wassup dude!");
    }

    /**
     * Closes the Scanner used to read user input to prevent an resource leak.
     */
    @Override
    public void close() {
        this.input.close();
    }

    /**
     * Reads user input from stdin.
     *
     * @return command user gives to Dude as a String to be parsed by Parser.
     */
    @Override
    public String readCommand() {
        return this.input.nextLine();
    }

    /**
     * Displays Dude's response to the user.
     * Accepts a Runnable that can execute arbitrary code to give maximum flexibility in calling code,
     * such as being able to have a for loop for indeterminate number of lines of responses.
     * However, the only primitive exposed to actually display output within respond is speak(String str).
     *
     * @param r Runnable that allows caller to execute arbitrarily complex code while creating a response.
     */
    @Override
    public void respond(Runnable r) {
        String line = "    ____________________________________________________________";
        System.out.println(line);
        r.run();
        System.out.println(line);
    }

    /**
     * Displays variable number of sentences to the user.
     * Exposes a more pleasant API than respond(Runnable r) when full flexibility is not needed.
     *
     * @param responses a variable number of sentences for Dude to tell the user.
     */
    @Override
    public void respond(String... responses) {
        respond(() -> {
            for (String response : responses) {
                speak(response);
            }
        });
    }

    /**
     * Displays an error message to the user when an incorrect command is given.
     * Tells the user what was wrong and gives the proper usage of the command.
     *
     * @param errorMsg A message describing the problem with the user's input.
     * @param usageMsgs variable number of strings describing the correct format of input.
     */
    @Override
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

    /**
     * Speaks a single sentence to the user.
     * The only primitive exposed to construct a response to pass to respond(Runnable r).
     *
     * @param str the sentence to speak to the user.
     */
    @Override
    public void speak(String str) {
        System.out.println("     " + str);
    }
}
