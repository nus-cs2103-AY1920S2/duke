package duke;

import java.time.LocalDate;

/**
 * Adds the specified command to the tasklist from Duke.
 */
public class AddCommand extends Command {

    AddCommand(String str) {
        super(str);
    }

    /**
     *Executes the add command and adds correct Task object to Tasklist.
     * @param lst from TaskList from Duke
     * @param storage from Storage from Duke
     * @param ui from UI from Duke from Duke
     * @param tasks from TaskNum from Duke
     * @return String containing the add command returned from ui
     * @throws DukeException might throw DukeException when user input is incorrect
     */
    @Override
    String execute(TaskList lst,Storage storage,Ui ui,TasksNum tasks) throws DukeException {
        tasks.addNum();
        String[] command = str.split(" ");
        switch (command[0]) {
        case "todo":
            String name = "";
            for (int i = 1; i < command.length; i++) {
                name += (command[i] + " ");
            }
            Todo todo = new Todo(name);
            lst.addTask(todo);
            return ui.taskAdd(todo, tasks);

        case "deadline":
            String[] commandD = setData(str);
            LocalDate dateD = setDate(commandD[1]);
            Deadline deadline = new Deadline(commandD[0], dateD);
            deadline.setTime(commandD[2]);
            lst.addTask(deadline);
            return ui.taskAdd(deadline, tasks);

        case "event":
            String[] commandE = setData(str);
            LocalDate dateE = setDate(commandE[1]);
            Event event = new Event(commandE[0], dateE);
            event.setTime(commandE[2]);
            lst.addTask(event);
            return ui.taskAdd(event, tasks);

        default:
            return ui.errormsg();
        }
    }

    /**
     * Formats command to separate info to put into Task object.
     * @param command String that contains user input
     * @return String array hat contains date and time for Deadline/Event object
     */
    String[] setData(String command) { //name and date and time
        System.out.println(command);
        int slashtask = command.indexOf("/");
        String[] datetime = command.substring(slashtask + 4,command.length()).split(" ");
        String ddate = datetime[0];
        String time = datetime[1];
        String name = command.substring(0,slashtask).split(" ")[1];
        String[] result = new String[3];
        result[0] = name;
        result[1] = ddate;
        result[2] = time;
        return result;
    }

    /**
     * Formats command to fit date into LocalDate format.
     * @param date1 String that contains date
     * @return LocalDate object or Deadline/Event object
     */
    LocalDate setDate(String date1) {
        System.out.println(date1);
        String[] date = date1.split("/");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        LocalDate localdate = LocalDate.of(year, month, day);
        return localdate;
    }
}