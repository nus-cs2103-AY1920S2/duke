public class Ui {
    public Ui() {

    }
    public void showLoadingError() {
        System.out.println("The input file is empty. A new list will be created");
    }

    public void showInputError() {
        System.out.println("The input is invalid. Please try again");
    }

    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void gotIt(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + size + " tasks in the list");
    }
}

