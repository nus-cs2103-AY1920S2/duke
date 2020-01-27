import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Constants;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String reply = "";

        if (inputArr.length == 1) {
            for (int i = 0; i < tasks.size(); i++) {
                int numbering = i + 1;
                reply += (numbering + ".");
                reply += (tasks.getTask(i) + "\n" + SPACE);
            }
            reply += "\n" + SPACE + "I told you save liao loh........";
        } else {
            String dateS = inputArr[1];
            LocalDate date = LocalDate.parse(dateS, inputFormatter);
            int numbering = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.getTask(i);
                if (currentTask instanceof Deadline || currentTask instanceof Event) {
                    if (currentTask.compareDate(date)) {
                        reply += (numbering++ + ".");
                        reply += (currentTask + "\n" + SPACE);
                    }
                }
            }
            reply += ("\n" + SPACE + "This are all the tasks with that date");
        }
        ui.reply(reply);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}