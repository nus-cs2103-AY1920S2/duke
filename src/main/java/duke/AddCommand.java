package duke;

import java.time.LocalDate;


public class AddCommand extends Command {

    AddCommand(String str) {
        super(str);
    }

    @Override
    String execute(TaskList lst,Storage storage,Ui ui,int tasks) throws DukeException {
        tasks++;
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

    String[] setData(String command) { //name and date and time
        int slashtask = command.indexOf("/");
        String[] datetime = command.substring(slashtask + 4,command.length()).split(" "); //string containing date and time on
        String ddate = datetime[0];
        String time = datetime[1];
        int slashdate = ddate.indexOf("/");
        String name = command.substring(0,slashdate).split(" ")[1];
        String[] result = new String[3];
        result[0] = name;
        result[1] = ddate;
        result[2] = time;
        return result;
    }

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