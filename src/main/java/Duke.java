import java.util.ArrayList;

public class Duke {
    public static final String botName = "Duke";
    public static final String listCommand = "list";
    public static final String byeCommand = "bye";

    private String padding = "       ";
    private String uselessLine = "----------------------------------------------------------";
    private String addedPhrase = "added: ";
    private ArrayList<String> storedItems;

    public Duke() {
        // Place-holder constructor, may need to extend later
        storedItems = new ArrayList<>();
    }

    public void greet() {
        System.out.println(padding + uselessLine + '\n' +
                padding + "Greetings! This is " + botName + ", and I am your friend!\n" +
                padding + "You don't have to be formal. Relax and tell me how I can help you\n" +
                padding + uselessLine);
    }

    public void storeUserInput(String str) {
        if (str.equals("")) {
            System.out.println(padding + uselessLine + '\n' +
                    padding + "Please type something. Don't leave it blank, plsss!\n" +
                    padding + uselessLine);
        }
        storedItems.add(str);
        System.out.println(padding + uselessLine + '\n' +
                padding + addedPhrase + str + '\n' +
                padding + uselessLine);
    }

    public void listStoredItems() {
        System.out.println(padding + uselessLine);
        if (storedItems.isEmpty()) {
            System.out.println(padding + "Your list is empty!");
        } else {
            int i = 1;
            for (String str : storedItems) {
                System.out.println(padding + i + ". " + str);
                i++;
            }
        }
        System.out.println(padding + uselessLine);
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
