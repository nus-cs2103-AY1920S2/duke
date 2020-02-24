package duke.command;

import duke.other.Ui;
import duke.task.TaskList;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        String helpMessage =
                "Help is here! Here is a list of keywords that I understand:\n"
              + "p.s. Date & Time are in <YYYY/M/D> & <HH:MM> format respectively.\n"
              + "\n"
              + "<Command>\t<Details>\t\t\t\t\t\t<Bot's Action>\n"
              + "\n"
              + "bye\t\t\t\t\t\t\t\t\t\tTerminates DingDing\n"
              + "date\t\t\t<Date>\t\t\t\t\t\tShows tasks on Date\n"
              + "deadline\t\t<Details> /by <Date> <Time>\tCreates a Deadline with details, date\n"
              + "\t\t\t\t\t\t\t\t\t\t& time\n"
              + "deadline\t\t<Details> /by <Time>\t\t\tCreates a Deadline details, today's date \n"
              + "\t\t\t\t\t\t\t\t\t\t& time\n"
              + "deadline\t\t<Details> /by <Date>\t\t\tCreates a Deadline details & date\n"
              + "event\t\t<Details> /at <Date> <Time>\tCreates an Event task with details, date \n"
              + "\t\t\t\t\t\t\t\t\t\t& time\n"
              + "event\t\t<Details> /at <Time>\t\t\tCreates an Event task with details, today's \n"
              + "\t\t\t\t\t\t\t\t\t\tdate & time\n"
              + "event\t\t<Details> /at <Date>\t\t\tCreates an Event task with details & date\n"
              + "todo\t\t\t<Details>\t\t\t\t\t\tCreates a Todo task with Details\n"
              + "delete\t\t<Task Number>\t\t\t\tDeletes the task of task number\n"
              + "done\t\t<Task Number>\t\t\t\tMarks the corresponding task number\n"
              + "find\t\t\t<Keyword(s)>\t\t\t\t\tShows all tasks with keyword(s)\n"
              + "help\t\t\t\t\t\t\t\t\t\tShows help message\n"
              + "list\t\t\t\t\t\t\t\t\t\tShows the list of tasks";
        System.out.println(helpMessage);
        return helpMessage;
    }
}
