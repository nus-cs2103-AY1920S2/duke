package duke.commands;

import duke.tasks.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FindDateCommand implements Command {
    private TaskList tasklist;
    private LocalDate date;

    public FindDateCommand(TaskList taskList, List<String> details) {
        this.tasklist = taskList;
        this.date = LocalDate.parse(details.get(0), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String execute() {
        TaskList successList = tasklist.searchDateTask(date);
        return successList.orderedToString();
    }
}
