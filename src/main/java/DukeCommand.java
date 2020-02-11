import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * The enum class for all Duke commands. User will input the command and
 * this class will make sense of the input.
 *
 * @author Amos Cheong
 */
public enum DukeCommand {
    /**
     * Enum Class for all Duke Commands.
     */
    LIST {
        @Override
        public void execute(String s1, TaskList list, Ui ui, Storage storage) {
            ui.HorizontalLine();
            ui.listAllTasks(list);
        }
    },
    DONE {
        @Override
        public void execute(String s1, TaskList list, Ui ui, Storage storage) {
            // Split the string to get the
            // index of the task to be done
            String[] arr = s1.split("\\s+");
            int pos = Integer.parseInt(arr[1]) - 1;

            Task taskToBeCompleted = list.getListOfTask().get(pos);
            taskToBeCompleted.taskIsDone(); // Mark this task as done

            // Asserting the task, check if it is done
            assert taskToBeCompleted.getStatusIcon() == "Y" : "Task is not made done";

            storage.store(list, ui);
            // Show message to user
            // Indicate that task is done
            ui.doneMessage(taskToBeCompleted);
        }
    },
    TODO {
        @Override
        public void execute(String s1, TaskList list, Ui ui, Storage storage) {
            String[] arr = s1.split("\\s+", 2);
            list.add(new Todo(arr[1]));
        }
    },
    DEADLINE {
        @Override
        public void execute(String s1, TaskList list, Ui ui, Storage storage) {
            // Manipulating the String by separating the actual command
            // and the word '/by' to get the description and date/time
            int limit = s1.lastIndexOf("/by") - 1;
            String[] arr = s1.split("\\s+", 2);
            int dateindex = arr[1].lastIndexOf("/by");
            String substr = arr[1].substring(dateindex);
            String[] strarr = substr.split("\\s+", 2);

            DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("uuuu-MM-dd");

            DukeTimeFormatter timeformat = new DukeTimeFormatter();
            DateTimeValidator validator = new DateTimeValidator();
            try {
                String[] inputdate = (strarr[1]).split("\\s+", 2);

                inputdate[0] = validator.DateValidator(strarr[1]);

                // Time Validation
                if (validator.isTwentyFourHourFormat(inputdate[1]))
                    throw new DukeException("Please enter time in the form of 24 hour format!");
                int dateInt = Integer.parseInt(inputdate[1]);
                int timeTest = dateInt % 100;

                // Check if the minute is valid
                if (validator.isValidTime(timeTest, dateInt))
                    throw new DukeException("Invalid time!");

                // Convert time to AM/PM format
                inputdate[1] = timeformat.toAMPMFormat(timeTest, dateInt);

                String newstr = String.join(" ", inputdate);
                list.add(new Deadline(s1.substring(9, limit), newstr));

            } catch (DateTimeParseException exception){
                ui.showErrorMessage(exception.getMessage() + "\n" +
                        "Input date in the form of yyy-mm-dd and " +
                        "remember to add time in 24-hour format");
            } catch (DukeException ex) {
                ui.showErrorMessage(ex.getMessage());
            }

        }
    },
    EVENT {
        public void execute(String s1, TaskList list, Ui ui, Storage storage){
            // Manipulating the String by separating the actual command
            // and the word '/at' to get the description and date/time
            int limit = s1.lastIndexOf("/at") - 1;
            String[] arr = s1.split("\\s+", 2);
            int dateindex = arr[1].lastIndexOf("/at");
            String substr = arr[1].substring(dateindex);

            // Separating the description and the date & time
            String[] strarr = substr.split("\\s+", 2);

            DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            DukeTimeFormatter timeformat = new DukeTimeFormatter();
            DateTimeValidator validator = new DateTimeValidator();
            try {
                String[] inputdate = (strarr[1]).split("\\s+", 2);

                inputdate[0] = validator.DateValidator(strarr[1]);

                // Time Validation
                if (validator.isTwentyFourHourFormat(inputdate[1]))
                    throw new DukeException("Please enter time in the form of 24 hour format!");
                int dateInt = Integer.parseInt(inputdate[1]);
                int timeTest = dateInt % 100;

                // Check if the minute is valid
                if (validator.isValidTime(timeTest, dateInt))
                    throw new DukeException("Invalid time!");

                // Convert time to AM/PM format
                inputdate[1] = timeformat.toAMPMFormat(timeTest, dateInt);

                String newstr = String.join(" ", inputdate);
                list.add(new Event(s1.substring(6, limit), newstr));

            } catch (DateTimeParseException exception){
                ui.showErrorMessage(exception.getMessage() + "\n" +
                        "Input date in the form of yyy-mm-dd and " +
                        "remember to add time in 24-hour format");
            } catch (Exception ex) {
                ui.showErrorMessage(ex.getMessage());
            }

        }
    },
    DELETE {
        @Override
        public void execute(String s1, TaskList list, Ui ui, Storage storage) {
            // Split the string to get the
            // index of the task to be deleted
            String[] arrdel = s1.split("\\s+");
            int pos = Integer.parseInt(arrdel[1]) - 1;

            list.delete(pos, storage);
        }
    },
    FIND {
      @Override
      public void execute(String s1, TaskList list, Ui ui, Storage storage) {
          String[] commandarray = s1.split("\\s+", 2);
          String keyword = commandarray[1];

          list.find(keyword, storage);
      }
    };

    public abstract void execute(String s1, TaskList list, Ui ui, Storage storage);
}