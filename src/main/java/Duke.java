import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String version = "4LC3N v0.0.1";
        System.out.println(version);

        String load = "=======================================================\n" +
                "                  LOADING ... ... ...\n" +
                "=======================================================";
        String greetings = "      _.-'''''-._\n" +
                "    /=_.-~-~-~-._=\\      .-.  _\n" +
                "   :    _     _    :     | | / )\n" +
                "  /    (o)   (o)    \\    | |/ /\n" +
                "  |  _ _ _ _ _ _ _  |   _|__ /_\n" +
                "  |  \\           /  |  / __)-' )\n" +
                "   \\  '.       .'  /   \\  `(.-')\n" +
                "    '.  `'---'`  .'     > ._>-'\n" +
                "      '-._____.-'      / \\/";
        String initialMessage = "4LC3N-BOT initialised.\nGreetings, humans!";
        String awaitingMessage = "\n> ENTER your input:";
        String storeMessage = "I have stored this task in my memory. Use" +
                " \"list\" to retrieve it!";
        String doneMessage = "You have completed: ";

        System.out.println(greetings);
        System.out.println(load);

        Scanner input = new Scanner(System.in);
        CommandParser parser = new CommandParser();
        Storage store = new Storage();

        System.out.println(initialMessage);
        System.out.println(awaitingMessage);

        // main bot system loop
        while(input.hasNext()) {
            String command = input.nextLine();
            // parse the command
            Instruction next = parser.parse(command);
            if (next == Instruction.MARK_DONE) {
                int index = Integer.parseInt(command.split("\\s+")[1]);
                store.markAsDone(index);
                System.out.println(doneMessage);
                System.out.println(store.retrieve(index));
            } else if (next == Instruction.READ_STORAGE) {
                store.readStorage();
            } else if (next == Instruction.STORE_DDL) {
                store.store(command);
                System.out.println(storeMessage);
            } else if (next == Instruction.STORE_EVENT) {
                store.store(command);
                System.out.println(storeMessage);
            } else if (next == Instruction.STORE_TODO) {
                store.store(command);
                System.out.println(storeMessage);
            } else if (next == Instruction.TERMINATE) {
                // terminate the bot program
                break;
            } else {
                // next == Instruction.AWAIT
                parser.echo(command);
            }
            System.out.println(awaitingMessage);
        }
        input.close();
        String goodbye = "\nGoodbye! You will be missed" +
                "\n      _.-'''''-._  \n" +
                "    /=_.-~-~-~-._=\\\n" +
                "   :    _     _    :\n" +
                "  /    (o)   (o)    \\\n" +
                "  |           `     |\n" +
                "  |     .-----.     |\n" +
                "   \\   :       :   /\n" +
                "    '.           .'\n" +
                "      '-._____.-'";
        System.out.println(goodbye);

    }
}
