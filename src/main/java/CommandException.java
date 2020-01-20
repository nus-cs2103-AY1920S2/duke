package main.java;

public class CommandException extends Exception {
    public CommandException() {
        System.out.println("The command is not recognised, sir/madam.");
    }
}