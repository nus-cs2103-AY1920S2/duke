package duke.commands;

import duke.tasks.TaskList;

import java.time.LocalDate;

public class FindDateCommand implements Command{
    private TaskList tasklist;
    private LocalDate date;

    public FindDateCommand(TaskList taskList, LocalDate date) {
        this.tasklist = taskList;
        this.date = date;
    }

    public String execute() {
        TaskList successList = tasklist.searchDateTask(date);
        return successList.orderedToString();
    }
}
