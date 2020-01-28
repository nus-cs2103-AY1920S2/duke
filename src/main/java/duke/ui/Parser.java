package duke.ui;

import duke.commands.Command;

public class Parser {
   public static Command parseCommand(String input) {
        return new Command(input);
   }
}