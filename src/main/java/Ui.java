/**
 *  deals with interactions with the user
 */
public class Ui {

    public String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Says hi to the user and includes the logo
     */
    public void start() {
        System.out.println("Hello from\n" + logo);
        dukePrint("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public String horizontalLines() {
        return "__________________________________________________________________________________________________________\n";
    }

    /**
     * prints output in a more user friendly format
     * @param input the string to be outputted
     */
    public void dukePrint (String input) {
        System.out.print(horizontalLines());
        System.out.print(input);
        System.out.print(horizontalLines());
    }
}