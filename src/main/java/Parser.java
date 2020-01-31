import java.util.ArrayList;

public class Parser {

    // method to make sense of command and then act on that command

    /**
     * Function that makes sense of the command.
     * Also carries out the necessary actions for each command in the database.
     * @param command command inputted by user.
     * @param ui the UI class that handles all the aesthetics of the chatbot.
     * @param list Tasklist class that keeps track of tasks in the list.
     * @return boolean True when the user has not "closed" the chatbot else False.
     */
    public boolean respondToUser(String command, UI ui, TaskList list) {
        // split the string
        String[] inputCommand = command.trim().split(" ");
        StringBuilder taskName = new StringBuilder();
        StringBuilder dateTime = new StringBuilder();
        int indexFound = 0;
        switch (inputCommand[0]) {
        case "todo":
            try {
                if (inputCommand.length == 1) {
                    // means empty to-do message - need to throw exception!
                    throw new DukeException("OOPS! :( The description of to-do cannot be empty!");
                }
                for (int i = 1; i < inputCommand.length; i++) {
                    taskName.append(inputCommand[i]);
                    if (i != inputCommand.length - 1) {
                        taskName.append(" ");
                    }
                }
                ToDo newToDo = new ToDo(taskName.toString());
                list.addTask(newToDo);
                ui.prettyPrinting(taskName.toString() + " added!");
            } catch (DukeException e) {
                ui.prettyPrinting(e.toString());
            }
            break;
        case "bye":
            ui.prettyPrinting("Bye. Hope to see you again soon!");
            return false;
        case "event":
            try {
                if (inputCommand.length == 1) {
                    throw new DukeException("Event description cannot be empty!");
                }
                if (!command.contains("/at")) {
                    throw new DukeException("Event command must contain [/at] as stated!");
                }
                indexFound = this.grabTaskName(taskName, inputCommand, "/at");
                this.grabDateTime(indexFound, inputCommand, dateTime);
                Event event = new Event(taskName.toString(), dateTime.toString());
                list.addTask(event);
                ui.prettyPrinting(taskName.toString() + " added!");
            } catch (DukeException e) {
                ui.prettyPrinting(e.toString());
            }
            break;
        case "deadline":
            try {
                if (inputCommand.length == 1) {
                    throw new DukeException("Deadline description cannot be empty! :(");
                }
                if (!command.contains("/by")) {
                    //means incorrect input of the deadline command as stated, throw exception
                    throw new DukeException("Deadline command must contain [/by] as stated!");
                }
                indexFound = this.grabTaskName(taskName, inputCommand, "/by");
                this.grabDateTime(indexFound, inputCommand, dateTime);
                // validate date inputted
                Deadline.validDate(dateTime.toString());
                Deadline deadline = new Deadline(taskName.toString(), dateTime.toString());
                list.addTask(deadline);
                ui.prettyPrinting(taskName.toString() + " added!");
            } catch (DukeException e) {
                ui.prettyPrinting(e.toString());
            } catch (Exception e) {
                ui.prettyPrinting("Incorrect date format! Please refer to following example: 31-12-2020 23:59");
            }
            break;
        case "done":
            try {
                Task task = list.getTask(Integer.parseInt(inputCommand[1]));
                if (task.getDone()) {
                    ui.prettyPrinting("Task already set done!");
                } else {
                    task.setDone();
                    ui.prettyPrinting("Task set to done!");
                }
            } catch (IndexOutOfBoundsException e) {
                ui.prettyPrinting("I believe you gave an incorrect task number! Please try again!");
            }
            break;
        case "delete":
            int taskToBeDeleted = Integer.parseInt(inputCommand[1]); //task to be deleted
            try {
                Task taskDeleted = list.deleteTask(taskToBeDeleted);
                ui.prettyPrinting(taskDeleted.toString() + " has been removed from the tasklist!");
            } catch (IndexOutOfBoundsException e) {
                ui.prettyPrinting("I believe you gave an incorrect task number! Please try again!");
            }
            break;
        case "list":
            int counter = 1;
            String listings = "";
            for (Task task : list.getTaskList()) {
                listings += counter + "." + task.toString();
                if (counter != list.getSize()) {
                    listings += "\n\t";
                }
                counter++;
            }
            ui.prettyPrinting(listings);
            break;
        case "find":
            try {
                if (inputCommand.length == 1) {
                    throw new DukeException("Find description cannot be empty!");
                }
                String keyWord = inputCommand[1].trim(); //trim all the unnecessary whitespaces.
                ArrayList<Task> matchingTasks = list.findTasks(keyWord);
                String result = "";
                if (matchingTasks.size() == 0) {
                    // no matches
                    result += "No results were found :(";
                } else {
                    result += "Here are the results your search!\n\t";
                    int count = 1;
                    for (Task t: matchingTasks) {
                        result += count + "." + t.toString();
                        if (count != matchingTasks.size()) {
                            result += "\n\t";
                        }
                        count++;
                    }

                }
                ui.prettyPrinting(result);
            } catch (DukeException e) {
                ui.prettyPrinting(e.toString());
            }
            break;
        default:
            ui.prettyPrinting("Invalid command! Please try again!");
        }
        return true;
    }

    /**
     * Function to split the input query and grab the task name, also returns the index where the at/by will be at.
     * @param taskName name of the task.
     * @param inputCommand command inputted by user (array).
     * @param delimiter delimiter which is either /by or /at.
     * @return index for the by/at depending on the type of task, not applicable for to-do tasks.
     */
    public int grabTaskName(StringBuilder taskName, String[] inputCommand, String delimiter)  throws DukeException {
        int indexFound = 0; //find the index for the delimiter
        for (int i = 1; i <= inputCommand.length - 1; i++) {
            if (inputCommand[i].equals(delimiter)) {
                indexFound = i;
                break;
            } else {
                taskName.append(inputCommand[i]);
                if (inputCommand[i + 1].equals(delimiter)) {
                    indexFound = i + 1;
                    break;
                } else {
                    taskName.append(" ");
                }
            }
        }
        // if the inputCommand array index 1 == delimiter, means no description was given, throw exception
        if (indexFound == 1) {
            throw new DukeException("Description of deadline/event cannot be empty!");
        }
        return indexFound;
    }

    /**
     * Function to grab and get the date time for the event/deadline.
     * @param indexFound index where the /at or /by is found.
     * @param inputCommand command inputted by user (array).
     * @param dateTime object to hold the result.
     */
    public void grabDateTime(int indexFound, String[] inputCommand, StringBuilder dateTime)  throws DukeException {
        if (indexFound == inputCommand.length - 1) {
            // means that there is no description of date of task after the delimiter
            throw new DukeException("Date and time of the event/deadline cannot be empty!");
        }
        for (int i = indexFound + 1; i < inputCommand.length; i++) {
            dateTime.append(inputCommand[i]);
            if (i != inputCommand.length - 1) {
                dateTime.append(" ");
            }
        }
    }

}
