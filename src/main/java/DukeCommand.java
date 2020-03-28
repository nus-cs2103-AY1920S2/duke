import java.lang.String;
import java.time.format.DateTimeParseException;

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
        public String execute(String s1, TaskList list, Ui ui) {
            return list.getSize() != 0 ? ui.listCommand(list) :
                    "It looks like there are no more tasks in the list!";
        }
    },
    DONE {
        @Override
        public String execute(String s1, TaskList list, Ui ui) {
            Storage storage = list.getStorage();
            // Split the string to get the
            // index of the task to be done
            String[] arr = s1.split("\\s+");
            int pos = Integer.parseInt(arr[1]) - 1;

            Task taskToBeCompleted = list.getListOfTask().get(pos);
            taskToBeCompleted.taskIsDone(); // Mark this task as done

            // Asserting the task, check if it is done
            assert taskToBeCompleted.getStatusIcon() == "Y" : "Task is not made done";

            // Update file
            storage.updateFile(list, ui);
            // Show message to user
            // Indicate that task is done
            return ui.finishMessage(taskToBeCompleted);
        }
    },
    TODO {
        @Override
        public String execute(String s1, TaskList list, Ui ui) {
            String[] arr = s1.split("\\s+", 2);
            Todo todotask = new Todo(arr[1]);
            list.add(todotask);
            return ui.addedMessage((list.getListOfTask()).size(), todotask);
        }
    },
    DEADLINE {
        @Override
        public String execute(String s1, TaskList list, Ui ui) {
            String DukeMessage = "";

            // Manipulating the String by separating the actual command
            // with the word '/by' to get the description and date/time
            int limit = s1.lastIndexOf("/by") - 1;
            String[] arr = s1.split("\\s+", 2);
            int dateindex = arr[1].lastIndexOf("/by");
            String substr = arr[1].substring(dateindex);
            String[] strarr = substr.split("\\s+", 2);


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

                Deadline FormattedDeadline = new Deadline(s1.substring(9, limit), newstr);

                if (list.hasDuplicates(FormattedDeadline)) {
                    throw new DukeException("OOPS!!! There is a same task already added into the list " +
                            "\n" + "or there is a clash of timing with one of the tasks in your list!");
                }

                list.add(FormattedDeadline);
                DukeMessage = ui.addedMessage((list.getListOfTask()).size(), FormattedDeadline);

            } catch (DateTimeParseException exception){
                DukeMessage = exception.getMessage() + "\n" +
                        "Input date in the form of yyy-mm-dd and " +
                        "\n" + "remember to add time in 24-hour format";
            } catch (DukeException ex) {
                DukeMessage = ex.getMessage();
            } finally {
                return DukeMessage;
            }

        }
    },
    EVENT {
        public String execute(String s1, TaskList list, Ui ui){
            String DukeMessage = "";

            // Manipulating the String by separating the actual command
            // with the word '/at' to get the description and date/time
            int limit = s1.lastIndexOf("/at") - 1;
            String[] arr = s1.split("\\s+", 2);
            int dateindex = arr[1].lastIndexOf("/at");
            String substr = arr[1].substring(dateindex);

            // Separating the description and the date & time
            String[] strarr = substr.split("\\s+", 2);

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

                Event FormattedEvent = new Event(s1.substring(6, limit), newstr);

                if (list.hasDuplicates(FormattedEvent)) {
                    throw new DukeException("OOPS!!! There is a same task already added into the list " +
                            "\n" + "or there is a clash of timing with one of the tasks in your list!");
                }


                list.add(FormattedEvent);
                DukeMessage =  ui.addedMessage((list.getListOfTask()).size(), FormattedEvent);

            } catch (DateTimeParseException exception){
                DukeMessage = exception.getMessage() + "\n" +
                        "Input date in the form of yyy-mm-dd and " +
                        "\n" + "remember to add time in 24-hour format";
            } catch (Exception ex) {
                DukeMessage = ex.getMessage();
            } finally {
                return DukeMessage;
            }



        }
    },
    DELETE {
        @Override
        public String execute(String s1, TaskList list, Ui ui) {
            // Split the string to get the
            // index of the task to be deleted
            String[] arrdel = s1.split("\\s+");
            int pos = Integer.parseInt(arrdel[1]) - 1;
            Task deletedTask = (list.getListOfTask()).get(pos);
            list.delete(pos);
            return ui.deletedMessage(list.getSize(), deletedTask);
        }
    },
    FIND {
      @Override
      public String execute(String s1, TaskList list, Ui ui) {
          String[] commandarray = s1.split("\\s+", 2);
          String keyword = commandarray[1];

          TaskList filteredList = list.find(keyword);
          return filteredList.getSize() != 0 ? ui.findMessage() + "\n" +
                  ui.listCommand(filteredList) : "Oh no! There are no tasks " +
                  "that matches the keyword that you have given";
      }
    };

    public abstract String execute(String s1, TaskList list, Ui ui);
}