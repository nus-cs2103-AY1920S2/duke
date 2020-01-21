public class Duke {
    public static final String botName = "Duke";
    public static final String byeCommand = "bye";

    private String padding = "       ";
    private String uselessLine = "--------------------------------------";

    public Duke() {
        // Place-holder constructor, may need to extend later
    }

    public void greet() {
        System.out.println(padding + uselessLine +
                padding + "Greetings! This is " + botName + ", and I am your friend!\n" +
                padding + "You don't have to be formal. Relax and tell me how I can help you\n" +
                padding + uselessLine);
    }


    public void echo(String str) {
        System.out.println(padding + uselessLine + '\n' +
                padding + str + '\n' +
                padding + uselessLine);
    }

    public void byeBye() {
        System.out.println(padding + uselessLine + '\n' +
                padding + "Bye-bye. It was nice talking to you. See ya soon!\n" +
                padding + uselessLine);
    }
}
