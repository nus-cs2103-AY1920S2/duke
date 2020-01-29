import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {

    Ui ui;
    Storage storage;
    TaskList taskList;

    public Duke() {
        ui = new Ui();
        storage = new Storage(Paths.get(System.getProperty("user.home"), "data", "duke.txt"));
        taskList = new TaskList();
    }

    public void run() {
        storage.loadTasks(taskList);
        ui.printLogo();
        ui.printGreeting();

        while (true) {
            try {
                //Split the input line into description and time portions
                String[] inputTokens = Parser.parse(ui.getCommand(), "/");
                ui.printDivider();
                //Further split the description for command checking
                String[] descriptionTokens = Parser.parse(inputTokens[0], " ");

                if (inputTokens.length < 2) {  //Regular commands and Todo
                    if (descriptionTokens[0].toLowerCase().equals("bye")) {
                        try {
                            storage.saveTasks(taskList);
                        } catch (IOAelitaException e) {
                            ui.printResponse(Response.IO_ERROR);
                        }
                        ui.printResponse(Response.GOODBYE);
                        break;

                    } else if (descriptionTokens[0].toLowerCase().equals("list")) {
                        //List all task on the specified date
                        if (descriptionTokens.length == 2) {
                            //The command consist of 2 parts: the command and the date
                            LocalDate date = LocalDate.parse(descriptionTokens[1]);
                            taskList.list(date, ui);
                        } else {
                            //List all the task in the list
                            taskList.list(ui);
                        }
                    } else if (descriptionTokens[0].toLowerCase().equals("done")) {
                        if (descriptionTokens.length < 2) {
                            throw new InsufficientArgumentAelitaException("done");
                        } else {
                            int index = Integer.parseInt(descriptionTokens[1]) - 1;
                            taskList.complete(index);
                            ui.printResponse(Response.DONE);
                            ui.printTask(taskList.get(index));
                        }

                    } else if (descriptionTokens[0].toLowerCase().equals("delete")) {
                        if (descriptionTokens.length < 2) {
                            throw new InsufficientArgumentAelitaException("delete");
                        }

                        int index = Integer.parseInt(descriptionTokens[1]) - 1;
                        ui.printResponse(Response.DELETE);
                        ui.printTask(taskList.remove(index));
                        ui.printResponse(Response.TASK_COUNT);

                    } else if (descriptionTokens[0].toLowerCase().equals("todo")) {
                        if (descriptionTokens.length == 1) {
                            throw new InsufficientArgumentAelitaException("description");
                        }

                        Task task = new Todo(reconstructDescription(descriptionTokens));
                        taskList.add(task);
                        ui.printResponse(Response.ADD_TASK);
                        ui.printTask(task);
                        ui.printResponse(Response.TASK_COUNT);

                    } else if (descriptionTokens[0].toLowerCase().equals("deadline") || descriptionTokens[0].toLowerCase().equals("event")) {
                        if (descriptionTokens.length == 1) {
                            //Description is missing
                            throw new InsufficientArgumentAelitaException("description");
                        } else {
                            //Date is missing
                            throw new InsufficientArgumentAelitaException("date");
                        }

                    } else {
                        //Command is not recognized
                        throw new InvalidCommandAelitaException();

                    }
                } else {
                    if (descriptionTokens.length < 2) {
                        //Not enough arguments on the description side
                        throw new InsufficientArgumentAelitaException("description");
                    }
                    //Concat the description tokens back to one string
                    String description = reconstructDescription(descriptionTokens);

                    //Check what type of task
                    if (descriptionTokens[0].toLowerCase().equals("deadline")) {

                        LocalDate date = LocalDate.parse(inputTokens[1].substring(3));
                        Task task = new Deadline(description, date);
                        taskList.add(task);
                        ui.printResponse(Response.ADD_TASK);
                        ui.printTask(task);
                        ui.printResponse(Response.TASK_COUNT);

                    } else if (descriptionTokens[0].toLowerCase().equals("event")) {

                        String[] dateTime = extractDateTime(inputTokens[1]);
                        LocalDate date = LocalDate.parse(dateTime[0]);

                        Task task = new Event(description, date, dateTime[1], dateTime[2]);
                        taskList.add(task);
                        ui.printResponse(Response.ADD_TASK);
                        ui.printTask(task);
                        ui.printResponse(Response.TASK_COUNT);

                    } else {
                        throw new InvalidCommandAelitaException();
                    }
                }
            } catch (EmptyInputAelitaException e) {
                ui.printResponse(Response.EMPTY_COMMAND);
            } catch (InvalidCommandAelitaException e) {
                ui.printResponse(Response.COMMAND_NOT_RECOGNIZED);
            } catch (InsufficientArgumentAelitaException e) {
                switch (e.getMessage()) {
                    case "date":
                        ui.printResponse(Response.MISSING_DATE);
                        break;
                    case "date-time":
                        ui.printResponse(Response.MISSING_DATE_TIME);
                        break;
                    case "delete":
                        ui.printResponse(Response.MISSING_DELETE_INDEX);
                        break;
                    case "description":
                        ui.printResponse(Response.MISSING_DESCRIPTION);
                        break;
                    case "done":
                        ui.printResponse(Response.MISSING_DONE_INDEX);
                        break;
                    case "end time":
                        ui.printResponse(Response.MISSING_END_TIME);
                        break;
                }
            } catch (EmptyListAelitaException e) {
                ui.printResponse(Response.NO_TASK);
            } catch (DuplicateMarkAelitaException e) {
                ui.printResponse(Response.TASK_COMPLETED);
            } catch (InvalidListItemAelitaException e) {
                ui.printResponse(Response.ITEM_NOT_FOUND);
            } catch (DateTimeParseException e) {
                ui.printResponse(Response.DATE_NOT_RECOGNIZED);
            } catch (NumberFormatException e) {
                ui.printResponse(Response.INDEX_NAN);
            } catch (InvalidArgumentAelitaException e) {
                ui.printResponse(Response.INVALID_ARGUMENT);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    private String[] extractDateTime(String dateTime) throws InsufficientArgumentAelitaException {

        //A local placeholder for date and time of Event {date, start time, end time}
        String[] tmp = new String[3];

        //Get the date
        String[] dateTimeTokens = dateTime.split(" ");
        tmp[0] = dateTimeTokens[1]; //Date

        if (dateTimeTokens.length < 3) {
            //One of the date-time components is missing
            throw new InsufficientArgumentAelitaException("date-time");
        }

        //Separate start time and end time
        String[] timeTokens = dateTimeTokens[2].split("-");
        if (timeTokens.length < 2) {
            throw new InsufficientArgumentAelitaException("end time");
        }

        tmp[1] = timeTokens[0]; //Start time
        tmp[2] = timeTokens[1]; //End time
        return tmp;
    }

    private String reconstructDescription(String[] descriptionTokens) {
        StringBuilder builder = new StringBuilder(descriptionTokens[1]);
        for (int i = 2; i < descriptionTokens.length; i++) {
            builder.append(" ");
            builder.append(descriptionTokens[i]);
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.run();
    }
}
