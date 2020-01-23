import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String version = "4LC3N v0.0.1";
        System.out.println(version);

        String load = "=======================================================\n" +
                "                  LOADING ... ... ...\n" +
                "=======================================================";
        String logo = "      _.-'''''-._\n" +
                "   k /=_.-~-~-~-._=\\      .-.  _\n" +
                "   :    _     _    :     | | / )\n" +
                "  /    (o)   (o)    \\    | |/ /\n" +
                "  |  _ _ _ _ _ _ _  |   _|__ /_\n" +
                "  |  \\           /  |  / __)-' )\n" +
                "   \\  '.       .'  /   \\  `(.-')\n" +
                "    '.  `'---'`  .'     > ._>-'\n" +
                "      '-._____.-'      / \\/";
        String initialMessage = "4LC3N-BOT initialised.";

        System.out.println(logo);
        System.out.println(load);

        Scanner input = new Scanner(System.in);
        CommandParser parser = new CommandParser();

        System.out.println(initialMessage);

        while(input.hasNext()) {
            String command = input.next();
            // parse the command
            Instruction next = parser.parse(command);
            if (next == Instruction.TERMINATE) {
                break;
            } else {
                // next == Instruction.AWAIT
                continue;
            }
        }
    }
}
