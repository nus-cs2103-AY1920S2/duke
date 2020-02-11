package duke.util;

import duke.mission.AddMission;
import duke.mission.DeleteMission;
import duke.mission.ExitMission;
import duke.mission.FindMission;
import duke.mission.HelpMission;
import duke.mission.InvalidMission;
import duke.mission.ListMission;
import duke.mission.MarkDoneMission;
import duke.mission.TagMission;
import duke.mission.Mission;

public class Parser {

    /**
     * Generates correspond Mission based on user inputs.
     *
     * @param input User input string.
     * @return a Mission that Duke needs to run.
     */
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
            case "tag":
                return new TagMission(input);
            case "todo":
            case "deadline":
            case "event":
                return new AddMission(input);
            default:
                return new InvalidMission();
        }
    }
}
