public class Parser {

    // method to make sense of command and then act on that command
    public boolean respondToUser(String command, UI ui, TaskList list) {
        // split the string
        String[] inputCommand = command.trim().split(" ");
        StringBuilder taskname = new StringBuilder();
        StringBuilder DateTime = new StringBuilder();
        int index_found = 0;
        switch (inputCommand[0]) {
            case "todo":
                try {
                    if (inputCommand.length == 1) {
                        // means empty to-do message - need to throw exception!
                        throw new DukeException("OOPS! :( The description of to-do cannot be empty!");
                    }
                    for (int i = 1; i < inputCommand.length; i++) {
                        taskname.append(inputCommand[i]);
                        if (i != inputCommand.length - 1) {
                            taskname.append(" ");
                        }
                    }
                    ToDo t = new ToDo(taskname.toString());
                    list.addTask(t);
                    ui.prettyPrinting(taskname.toString() + " added!");
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
                        throw new DukeException("Event command must contain [/at] as stated!"); //all the exceptions as stated in the error msg
                    }
                    index_found = this.grabTaskName(taskname, inputCommand, "/at");
                    this.grabDateTime(index_found, inputCommand, DateTime);
                    Event e = new Event(taskname.toString(), DateTime.toString());
                    list.addTask(e);
                    ui.prettyPrinting(taskname.toString() + " added!");
                } catch (DukeException e) {
                    ui.prettyPrinting(e.toString());
                }
                break;
            case "deadline":
                try {
                    if (inputCommand.length == 1) {
                        throw new DukeException("Deadline description cannot be empty! :("); // error as stated in the error msg
                    }
                    if (!command.contains("/by")) {
                        //means incorrect input of the deadline command as stated, throw exception
                        throw new DukeException("Deadline command must contain [/by] as stated!");
                    }
                    index_found = this.grabTaskName(taskname, inputCommand, "/by");
                    this.grabDateTime(index_found, inputCommand, DateTime);
                    // validate date inputted
                    Deadline.validDate(DateTime.toString());
                    Deadline d = new Deadline(taskname.toString(), DateTime.toString());
                    list.addTask(d);
                    ui.prettyPrinting(taskname.toString() + " added!");
                } catch (DukeException e) {
                    ui.prettyPrinting(e.toString());
                } catch (Exception e) {
                    ui.prettyPrinting("Incorrect date format! Please refer to following example: 31-12-2020 23:59");
                }
                break;
            case "done":
                int taskToBeDone = Integer.parseInt(inputCommand[1]); // all assuming correct input
                try {
                    Task T = list.getTask(Integer.parseInt(inputCommand[1]));
                    if (T.getDone()) {
                        ui.prettyPrinting("Task already set done!");
                    } else {
                        T.setDone();
                        ui.prettyPrinting("Task set to done!");
                    }
                } catch (IndexOutOfBoundsException E) {
                    ui.prettyPrinting("I believe you gave an incorrect task number! Please try again!");
                }
                break;
            case "delete":
                int taskToBeDeleted = Integer.parseInt(inputCommand[1]); //task to be deleted
                try {
                    Task T = list.deleteTask(taskToBeDeleted); // if index not found -> will auto throw indexoutofbounds exception
                    ui.prettyPrinting(T.toString() + " has been removed from the tasklist!");
                } catch (IndexOutOfBoundsException E) {
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
            default:
                ui.prettyPrinting("Invalid command! Please try again!"); // handle the case where the user input something not recognised
        }
        return true;
    }

    /**
     * Function to split the input query and grab the task name, also returns the index where the at/by will be at
     * @param taskname
     * @param inputCommand
     * @param delimiter
     * @return index for the by/at depending on the type of task, not applicable for to-do tasks
     */
    public int grabTaskName(StringBuilder taskname, String[] inputCommand, String delimiter)  throws DukeException {
        int index_found = 0; //find the index for the delimiter
        for (int i = 1; i <= inputCommand.length - 1; i++) {
            if (inputCommand[i].equals(delimiter)) {
                index_found = i;
                break;
            } else {
                taskname.append(inputCommand[i]);
                if (inputCommand[i + 1].equals(delimiter)) {
                    index_found = i + 1;
                    break;
                } else {
                    taskname.append(" ");
                }
            }
        }
        // if the inputCommand array index 1 == delimiter, means no description was given, throw exception
        if (index_found == 1) throw new DukeException("Description of deadline/event cannot be empty!");
        return index_found;
    }

    /**
     * Function to grab and get the date time for the event/deadline
     * @param index_found
     * @param inputCommand
     * @param DateTime
     */
    public void grabDateTime(int index_found, String[] inputCommand, StringBuilder DateTime)  throws DukeException {
        if (index_found == inputCommand.length - 1) {
            // means that there is no description of date of task after the delimiter
            throw new DukeException("Date and time of the event/deadline cannot be empty!");
        }
        for (int i = index_found + 1; i < inputCommand.length; i++) {
            DateTime.append(inputCommand[i]);
            if (i != inputCommand.length - 1) {
                DateTime.append(" ");
            }
        }
    }

}
