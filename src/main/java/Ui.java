public class Ui {

    public void printOpeningScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "__________________________";
        System.out.println(line + "\nWhat can i do for you?\n" + line);
    }

    public void commandBreak() {
        String line = "____________________________________";
        System.out.println (line + "\n");
    }

    public void closingScreen() {
        String wave = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println (wave +"\nCya soon:)\n" + wave);
    }
}
