public class Duke {
    public static String bot_name = "Duke";
    private String padding = "       ";

    public Duke() {
        // Place-holder constructor, may need to extend later
    }

    public void greet() {
        System.out.println(padding + "Greetings! This is " + bot_name + ", and I am your friend!");
        System.out.println(padding + "You don't have to be formal. Relax and tell me how I can help you");
    }


    public void echo(String str) {
        System.out.println(padding + str);
    }
}
