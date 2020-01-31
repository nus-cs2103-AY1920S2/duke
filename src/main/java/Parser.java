import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public String input;
    public String command;
    public String[] inputs;

    public Parser(String input) {
        this.input = input;
    }



    public String getCommand() {
        inputs = input.split(" ");
        command = inputs[0];
        return command;
    }

    public String getTask() {
        switch (command) {
        case "todo":
            case "find":
                return inputs[1];
        case "deadline":
            return input.split(" /by")[0].split(" ", 2)[1];
        case "event":
            return input.split(" /at")[0].split(" ", 2)[1];
            default:
            return "";
        }
    }

    public LocalDateTime getDate() {
        if (inputs[0].equals("deadline")) {
            String time = input.split(" /by")[1].substring(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(time, formatter);
        } else if (inputs[0].equals("event")) {
            String time = input.split(" /at")[1].substring(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(time, formatter);
        } else {
            return null;
        }
    }

    public int getIndex(TaskList tasks) throws EmptyDescriptionException {
        if (input.split(" ").length == 1) {
            throw new EmptyDescriptionException("You forgot to mention the index!");
        }
        int idx = Integer.parseInt(input.split(" ")[1]) - 1;
        return idx;
    }
}
