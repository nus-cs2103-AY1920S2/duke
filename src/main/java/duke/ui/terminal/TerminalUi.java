package duke.ui.terminal;

import duke.ui.UiText;

import java.io.InputStream;
import java.util.Scanner;

import java.util.Arrays;
import java.util.List;

public class TerminalUi implements UiText {
    protected Scanner sc;

    public TerminalUi(InputStream is) {
        this.sc = new Scanner(is);
    }

    public TerminalUi(Scanner inputSc) {
        this.sc = inputSc;
    }

    /**
     * User has next input line?
     *
     * @return User has next input line?
     */
    public boolean hasNextLine() {
        return this.sc.hasNextLine();
    }

    /**
     * Similar to Scanner nextLine();
     *
     * @return Next line from input
     */
    public String nextLine() {
        return this.sc.nextLine();
    }

    public void clearUserInput() {
    }


    private boolean isOpen = false;

    /**
     * To start responding to user, open the responding window
     *
     * @param initials = optional imitial respond to user
     */
    public void startRespond(String... initials) {
        assert !this.isOpen : "illegal usage of responder";
        System.out.print(resSpace);
        System.out.println(line);
        Scanner sc2;
        for (String str : initials) {
            sc2 = new Scanner(str);
            while (sc2.hasNext()) {
                System.out.print(resSpace + " ");
                System.out.println(sc2.nextLine());
            }
        }
        this.isOpen = true;
    }

    /**
     * Respond the lines to user, need to start responding first
     *
     * @param respondStr = lines to respond
     */
    public void respondLine(String... respondStr) {
        respondLine(Arrays.asList(respondStr));
    }

    public void respondLine(List<String> respondStr) {
        assert this.isOpen : "illegal usage of responder";
        Scanner sc2;
        for (String str : respondStr) {
            sc2 = new Scanner(str);
            while (sc2.hasNext()) {
                System.out.print(resSpace + " ");
                System.out.println(sc2.nextLine());
            }
        }
    }

    /**
     * To stop responding to user, close the respond window
     *
     * @param remarks = any ending respond before closing
     */
    public void over(String... remarks) {
        Scanner sc2;
        for (String str : remarks) {
            sc2 = new Scanner(str);
            while (sc2.hasNext()) {
                System.out.print(resSpace + " ");
                System.out.println(sc2.nextLine());
            }
        }
        System.out.print(resSpace);
        System.out.println(line);
        System.out.println();
        this.isOpen = false;
    }

    public void respond(String... respondStr) {
        respond(Arrays.asList(respondStr));
    }

    /**
     * Shortcut function to open respond window, respond to user with the strings given, then close window
     *
     * @param respondStr = strings to respond
     */
    public void respond(List<String> respondStr) {
        startRespond();
        respondLine(respondStr);
        over();
    }
}