public class Ui {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
    }

    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println("I am here to help you with anything you need!");
    }

    public void showLineOfUnderscores() {
        System.out.println("____________________________________________________________");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showEmptyDescription(String type) {
        System.out.println("☹ OOPS!!! The description of a " + type + " cannot be empty.");
    }

    public void showInvalidRemoval() {
        System.out.println("This item is not valid to remove.");
    }

    public void showCommandNotFound() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showMissingParemeters() {
        System.out.println("☹ OOPS!!! Either filter criterion (date/month/year) or its value is missing. Please try again.");
    }
}
