public class Ui {
    private String dukeLogo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

    public void welcomeUser() {
        System.out.println("Hello, I am \n" + logo + "your personal buddy. What's up?\n"
        + "____________________________________________________________\n");
    }

    public void farewellUser() {
        divider("Aww, see you again soon!");
        return;
    }

    public void divider(String s) {
        if(s.length() > 0) {
            System.out.println("____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" + s + "\n"
                    + "____________________________________________________________\n");
            return;
        }
    }


}
