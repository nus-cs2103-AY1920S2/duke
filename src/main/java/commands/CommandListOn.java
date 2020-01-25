package commands;

import exceptions.DukeException;
import processor.DukeProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommandListOn implements Command {
    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);

        try {
            LocalDate searchDate = LocalDate.parse(argsArray[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            processor.getTaskList().printTasksOn(searchDate);
        } catch(Exception e) {
            e.printStackTrace();
            throw new DukeException("You've entered an incorrect date! Please follow this format: liston dd/MM/yyyy");
        }


    }
}
