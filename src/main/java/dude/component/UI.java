package dude.component;

import java.util.Scanner;

public class UI implements IUserInterface {
    private Scanner input;

    public UI() {
        this.input = new Scanner(System.in);
        respond("Wassup dude!");
    }

    public void close() {
        this.input.close();
    }

    public String readCommand() {
        return this.input.nextLine();
    }

    public void respond(String ...responses) {
        respond(() -> {
            for (String response : responses) {
                speak(response);
            }
        });
    }

    public void respondParsingError(String errorMsg, String ...usageMsgs) {
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

    public void respond(Runnable r) {
        String line = "    ____________________________________________________________";
        System.out.println(line);
        r.run();
        System.out.println(line);
    }

    public void speak(String str) {
        System.out.println("     " + str);
    }
}
