public class Parser {

    public static Mission parse(String input) {
        if (input.equals("bye")) {
            return new ExitMission();
        }

        if (input.equals("help")) {
            return new HelpMission();
        }

        if (input.equals("list")) {
            return new ListMission();
        }

        String[] words = input.split(" ");
        switch (words[0]) {
        case "done":
            return new MarkDoneMission(input);
        case "delete":
            return new DeleteMission(input);
        case "find":
            return new FindMission(input);
        case "todo":
        case "deadline":
        case "event":
            return new AddMission(input);
        default:
            return new InvalidMission();
        }
    }
}
