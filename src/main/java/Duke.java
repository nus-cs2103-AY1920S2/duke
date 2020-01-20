public class Duke {
    public static String bot_name = "Duke";
    private String padding = "       ";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void greet() {
        System.out.println(padding + "Greetings! This is Duke, and I am your friend!");
        System.out.println(padding + "You don't have to be formal. Relax and tell me how I can help you");
    }


    public void echo(String str) {
        System.out.println(padding + str);
    }
}
