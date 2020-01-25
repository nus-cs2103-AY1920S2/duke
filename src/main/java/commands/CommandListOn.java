package commands;

import exceptions.DukeException;
import main.DukeProcessor;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommandListOn implements DukeCommand {
    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);

        try {
            LocalDate searchDate = LocalDate.parse(argsArray[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            List<Task> taskList = processor.getTaskList();

            List<Task> outputTaskList = new ArrayList<Task>();
            for(Task task : taskList) {
                if(task instanceof DeadlineTask) {
                    if(((DeadlineTask) task).getParsedDeadline().toLocalDate().equals(searchDate)) {
                        outputTaskList.add(task);
                    }
                } else if(task instanceof EventTask) {
                    if(((EventTask) task).getParsedStartTime().toLocalDate().equals(searchDate)) {
                        outputTaskList.add(task);
                    }
                }
            }

            if(outputTaskList.size() == 0) {
                System.out.println("Looks like you don't have any tasks entered on this date! Try entering one with " +
                        "the " +
                        "commands 'todo', 'deadline' or 'event'!");
            } else {
                System.out.println(String.format("Here are the %d tasks I've noted down for you on %s:",
                        outputTaskList.size(), searchDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                for(int i = 0; i < outputTaskList.size(); i ++) {
                    System.out.println(String.format("%d. %s", i + 1, outputTaskList.get(i)));
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
            throw new DukeException("You've entered an incorrect date! Please follow this format: liston dd/MM/yyyy");
        }


    }
}
