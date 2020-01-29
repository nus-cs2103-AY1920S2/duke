package duke.io;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {

    protected static final String OUTPUT_INDENTATION = "\t";
    protected static final String OUTPUT_HORIZONTAL_LINE = "\t________________________________________________________";

    protected Scanner scanner;

    public Ui(InputStream ioStream) {
        scanner = new Scanner(ioStream);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printWelcomeMessage() {
        stylizedPrint(
                "Hello",
                "What can I do for you?"
        );
    }

    public void printGoodbyeMessage() {
        stylizedPrint("Bye. Hope to never see you again!");
    }

    public void printError(String... error) {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        for (int i = 0; i < error.length; ++i) {
            System.out.println(getIndentation(1) + error[i]);
        }
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    public void stylizedPrint(String... lines) {
        stylizedPrint(1, lines);
    }

    public void stylizedPrint(int numTabs, String... lines) {
        numTabs = Math.max(0, numTabs);
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        for (int i = 0; i < lines.length; ++i) {
            System.out.println(getIndentation(numTabs) + lines[i]);
        }
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    protected String getIndentation(int numTabs) {
        String ret = "";

        for (int i = 0; i < numTabs; ++i) {
            ret += OUTPUT_INDENTATION;
        }

        return ret;
    }
}
