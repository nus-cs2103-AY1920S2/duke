public class Parser {
    public static Command parse(String fullcommand) {
        int spaceIndex = fullcommand.indexOf(" ");

        if (spaceIndex == -1) {
            // full command is only 1 word
            if (fullcommand.equals("list")) {
                return new ListCommand();
            } else if (fullcommand.equals("bye")) {
                return new ExitCommand();
            } else {
                // else you have some unknown command here
                System.out.println("unknown single word command being parsed, terminating");
                return new InvalidCommand();
            }
        } else {
            String firstArg = fullcommand.substring(0, spaceIndex);
            String otherArgs = fullcommand.substring(spaceIndex + 1);

            if (firstArg.equals("todo")) {
                return new AddCommand(new Todo(otherArgs));
            } else if (firstArg.equals("deadline")) {
                String[] splitby = otherArgs.split(" /by ");    //note surrounding spaces
                return new AddCommand(new Deadline(splitby[0], splitby[1]));
            } else if (firstArg.equals("event")) {
                String[] splitat = otherArgs.split(" /at ");    //note surrounding spaces
                return new AddCommand(new Event(splitat[0], splitat[1]));
            } else if (firstArg.equals("done")) {
                // assumes command is only "done" and an int
                // the input is 1-indexed. DoneCommand takes in 0-indexed
                return new DoneCommand(Integer.parseInt(otherArgs) - 1);
            } else {
                System.out.println("unknown multiple word command being parsed, terminating");
                return new InvalidCommand();
            }
        }
    }
}
