public class Ui {

    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I 'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");
    }

    public void printEnding() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printLine() {
        System.out.println("________________________________________");
    }
}
