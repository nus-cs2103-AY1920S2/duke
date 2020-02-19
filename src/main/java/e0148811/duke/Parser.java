package e0148811.duke;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Parser {
    String currentLine;
    Scanner sc;

    public Parser(Ui ui) {
        sc = new Scanner(System.in);
    }

    public String readInputLine() {
        currentLine = sc.nextLine();
        return currentLine;
    }

    public String[] breakIntoWords() {
        return currentLine.split(" ");
    }

    public String[] parse(String input) {
        return input.split(" ");
    }

    public String getCurrentLine() {
        return currentLine;
    }
}
