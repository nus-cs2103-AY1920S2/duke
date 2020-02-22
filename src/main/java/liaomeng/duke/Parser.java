package liaomeng.duke;

import java.util.Scanner;

public class Parser {
    Scanner sc;

    public Parser() {
        sc = new Scanner(System.in);
    }

    public String[] parse(String input) {
        return input.split(" ");
    }
}
