public class Ui {
    //deals with interactions with the user

    public String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void start() {
        System.out.println("Hello from\n" + logo);
        dukePrint("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public String horizontalLines() {
        return "__________________________________________________________________________________________________________\n";
    }

    public void dukePrint (String input) {
        System.out.print(horizontalLines());
        System.out.print(input);
        System.out.print(horizontalLines());
    }
}