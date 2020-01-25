package main;

import processor.DukeProcessor;

import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DukeProcessor processor = new DukeProcessor();
        while(processor.isActive()) {
            String input = sc.nextLine();
            processor.processInput(input);
        }
    }
}
