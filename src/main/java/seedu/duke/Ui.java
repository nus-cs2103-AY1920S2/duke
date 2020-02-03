package seedu.duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    public Ui() {

    }

    protected void greet() {
        System.out.println("Hi there, I'm Dodo!\nHow may I help you today?");
    }

    protected void showLoadingError() {
        System.out.println("Loading error. Try again!");
    }

    protected void print(String str) {
        System.out.println(str);
    }





}
