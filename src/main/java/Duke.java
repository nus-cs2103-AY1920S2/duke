import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        //Intialise chatbot and scanner objects
        Chatbot bot = new Chatbot("bot");
        Scanner sc = new Scanner(System.in);

        //Set greeting and greet
        String intro = "--------------------------------------------------\n" +
                "Wassup! Wo shi " + bot.getName() + "\n" +
                "You want what? \n" +
                "--------------------------------------------------\n";
        bot.setGreeting(intro);
        bot.greet();

        //Set goodbye
        String goodbye = "--------------------------------------------------\n" +
                "Why you so ba...bot has been killed\n" +
                "--------------------------------------------------\n";
        bot.setGoodbye(goodbye);

        //read first input
        String input = sc.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(input);
        String command = tokenizer.nextToken();

        //add inputs, list record and mark as done when asked
        while(!command.equals("bye")) {
            if(command.equals("list")) {
                bot.listRecord();
            } else if(command.equals("done")) {
                int num = Integer.parseInt(tokenizer.nextToken());
                bot.setDone(num);
            } else {
                bot.addRecord(input);
            }

            input = sc.nextLine();
            tokenizer = new StringTokenizer(input);
            command = tokenizer.nextToken();
        }

        //exit
        bot.goodbye();
    }
}