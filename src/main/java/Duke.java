import java.util.Scanner;

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
        String command = sc.nextLine();

        //echo inputs
        while(!command.equals("bye")) {
            bot.echo(command);
            command = sc.nextLine();
        }

        //exit
        bot.goodbye();
    }
}

