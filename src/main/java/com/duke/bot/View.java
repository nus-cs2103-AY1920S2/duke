package com.duke.bot;

import java.util.List;

public class View {
    /**
     * Formats and prints the lines of strings supplied into the standard output.
     * 
     * @param lines Lines of strings to be printed to the standard output.
     */
    public void print(List<String> lines) {
        System.out.println("    ____________________________________________________________");
        lines.forEach(line -> System.out.println("     " + line));
        System.out.println("    ____________________________________________________________");
    }
}
