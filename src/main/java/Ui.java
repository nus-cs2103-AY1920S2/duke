public class Ui {
    public String printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    public static String greetDuke() {
        return "Hey there, Red. Anything I can do for you?";
    }

    public String exitDuke() {
        return "Guess that's enough for now. I've got your back, so you keep me close.";
    }

    public String showError(String message) {
        return message;
    }
}
