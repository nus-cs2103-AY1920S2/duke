package duke.pack;

public class Parser {
    public Parser() {
    }

    public Command parseCommand(String command) {
        String[] comm = command.split(" ");

        if (command.equals("list")) {
            // list

        } else if (comm[0].equals("done")) {
            // done

        } else if (comm[0].equals("delete")) {
            // delete

        } else if (comm[0].equals("todo")) {
            // to-do

        } else if (comm[0].equals("event")) {
            // event

        } else if (comm[0].equals("deadline")) {
            // deadline

        } else {
            // invalid command

        }


    }


}
