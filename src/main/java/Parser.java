import java.time.*;

public class Parser {
    private String[] inputs;
    private String command;
    private String description;
    private String timing;


    public Parser(String input) {
        this.inputs = input.split(" ");
        this.command = inputs[0];
        this.description = "";
        /*if(inputs.length > 1) {
            description = inputs[1];
        }*/
        this.timing = "";
        String splitter = " /";
        if(command.equals("deadline")) {
            splitter = " /by ";
        }
        else if (command.equals("event")) {
            splitter = " /at ";
        }
        String[] descriptionSplit;
            descriptionSplit = input.split(splitter);

            if(descriptionSplit.length > 1) {
                this.timing = descriptionSplit[1];
            }

        String splitterforDesc = command;
        splitterforDesc += " ";
        String[] y = descriptionSplit[0].split(splitterforDesc);
        if(y.length > 1) {
            description = y[1];
        }

    }

    public String getCommand() {
        return command;
    }

    public String getDescription() throws DukeException {
        if(description.equals("")) {
            throw new DukeException("It appears that no description was provided for this " + command + "!");
        }
        return description;
    }

    public String getTiming() throws DukeException{
        if(timing.equals("")) {
            throw new DukeException("It appears that no due date was provided for this " + command + "!");
        }
        return timing;
    }

    public int getIndex() throws DukeException {
        if(description.equals("")) {
            throw new DukeException("It appears that no index was provided for the command " + command + "!");
        }

        try {
            return (Integer.parseInt(description));
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Index provided for command " + command + " is not an integer!");
        }
    }

}
