public class Duke {
    public static void main(String[] args) {
        System.out.println(createGreeting());
    }

    private static String createGreeting() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lineBreak = "____________________________________________________________\n";
        sb.append("Hello from\n");
        sb.append(logo);
        sb.append(lineBreak);
        sb.append("Hello! I'm Duke\nWhat can I do for you?\n");
        sb.append(lineBreak);
        return sb.toString();
    }
}
