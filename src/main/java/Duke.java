import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public final static String NEWLINE =  System.lineSeparator();
    public final static String INDENT = "    ";
    public final static String BORDER = INDENT + "____________________________________________________________";
    public final static String EXIT = "bye";
    public final static String GOODBYE_MESSAGE = INDENT + "  Goodbye and have a beautiful time!";

    public static void main(String[] args) {
        String logo = INDENT + "  _____  __    __  _____" + NEWLINE
                + INDENT + " |  ___||  \\  /  ||  ___|" + NEWLINE
                + INDENT + " | |__   \\  \\/  / | |__" + NEWLINE
                + INDENT + " |  __|   |    |  |  __|" + NEWLINE
                + INDENT + " | |___  /  /\\  \\ | |___" + NEWLINE
                + INDENT + " |_____||__/  \\__\\|_____|" + NEWLINE;
        System.out.println(BORDER);
        System.out.println(logo);
        System.out.println(INDENT + "  Hello! I'm EXE, I'll execute anything on your command! :)");
        System.out.println(INDENT + "  What do you want to exe?");
        System.out.println(BORDER);

        Scanner scanner = new Scanner(System.in);
        List<Task> listOfTask = new ArrayList<>();

        String userInput = scanner.nextLine();
        String replyMessage;

        while (!userInput.equals(EXIT)) {
            String[] inputArr = userInput.split(" ");
            String command = inputArr[0];
            if (userInput.length() > 0) {
                CommandList commandValue;
                try {
                    commandValue = CommandList.valueOf(command.toUpperCase());
                } catch (IllegalArgumentException exception) {
                    try {
                        throw new DukeException("Invalid command", DukeErrorType.INVALID_COMMAND);
                    } catch (DukeException dukeException) {
                        System.out.println(addBorder(dukeException.toString()));
                    } finally {
                        userInput = scanner.nextLine();
                        continue;
                    }
                }
                switch (commandValue) {
                    case TODO:
                        try {
                            String[] todoArr = userInput.split("todo");
                            if (todoArr.length == 0 || todoArr[1].trim().length() == 0) {
                                throw new DukeException("Empty ToDo description",
                                        DukeErrorType.EMPTY_DESCRIPTION, command);
                            } else {
                                String todoDescription = todoArr[1].trim();
                                ToDo currentToDo = new ToDo(todoDescription);
                                listOfTask.add(currentToDo);
                                replyMessage = customiseMessage(currentToDo.toString(), listOfTask.size());
                                System.out.println(addBorder(replyMessage));
                            }
                        } catch (DukeException exception){
                            System.out.println(addBorder(exception.toString()));
                        }finally {
                            break;
                        }
                    case EVENT:
                        String[] eventDetails = userInput.split("/at");
                        if (eventDetails.length > 1) {
                            // removing white spaces, getting eventTime
                            String eventTime = eventDetails[1].trim();
                            String eventDescription = "";
                            String[] descriptionArr = eventDetails[0].split("event");
                            if (descriptionArr.length > 1) {
                                eventDescription = descriptionArr[1].trim();
                                try {
                                    if (eventDescription.length() == 0) {
                                        throw new DukeException("Empty Event description",
                                                DukeErrorType.EMPTY_DESCRIPTION,
                                                command);
                                    }
                                } catch (DukeException exception) {
                                    System.out.println(addBorder(exception.toString()));
                                }
                            } else {
                                // throw own class exception here also
                                try {
                                    throw new DukeException("Empty Event description", DukeErrorType.EMPTY_DESCRIPTION,
                                            command);
                                } catch (DukeException exception) {
                                    System.out.println(addBorder(exception.toString()));
                                }
                            }
                            Event currentEvent = new Event(eventDescription, eventTime);
                            listOfTask.add(currentEvent);
                            replyMessage = customiseMessage(currentEvent.toString(), listOfTask.size());
                            System.out.println(addBorder(replyMessage));

                        } else {
                            // Do my own exception class here
                            try {
                                throw new DukeException("Empty Event time", DukeErrorType.EMPTY_TIME, command);
                            } catch (DukeException exception) {
                                System.out.println(addBorder(exception.toString()));
                            }
                        }
                        break;
                    case DEADLINE:
                        String[] deadlineDetails = userInput.split("/by");
                        String deadlineTime = deadlineDetails[1].trim();
                        String deadlineDescription = "";
                        String[] descriptionArr = deadlineDetails[0].split("deadline");
                        deadlineDescription = descriptionArr[1].trim();
                        Deadline currentDeadline = new Deadline(deadlineDescription, deadlineTime);
                        listOfTask.add(currentDeadline);
                        replyMessage = customiseMessage((currentDeadline.toString()), listOfTask.size());
                        System.out.println(addBorder(replyMessage));
                        break;
                    case DELETE:
                        int deleteTaskNumber = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task removedTask = listOfTask.remove(deleteTaskNumber);
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("      Noted. I've removed this task:\n");
                            stringBuilder.append(INDENT + "   " + removedTask.toString() + "\n");
                            stringBuilder
                                    .append(String
                                            .format("      Now you have %d task(s) in the list.", listOfTask.size()));
                            System.out.println(addBorder(stringBuilder.toString()));
                        } catch (IndexOutOfBoundsException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case DONE:
                        int taskNumber = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task currentTask = listOfTask.get(taskNumber);
                            currentTask.markAsDone();
                            System.out.println(formatAddedTaskReply(currentTask.getStatusIcon(),
                                    currentTask.getTask()));
                        } catch (IndexOutOfBoundsException exception) {
                            // do own class exception here
                            System.out.println(exception.getMessage());
                        }
                        break;
                    case LIST:
                        replyMessage = formatListReply(listOfTask);
                        System.out.println(addBorder(replyMessage));
                        break;
                }
            } else {
                System.out.println("Please give me something to exe :D");
            }
            userInput = scanner.nextLine();
        }

        System.out.println(addBorder(GOODBYE_MESSAGE));

        scanner.close();
    }

    // for bye and adding stuff into list
    public static String addBorder(String message) {
        String reply = BORDER + "\n" + message + "\n" + BORDER;
        return reply;
    }

    // for printing list
    public static String formatListReply(List<Task> listOfTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + "  Here are the tasks in your list:\n");
        Task currentTask;
        for (int i = 0; i < listOfTask.size(); i++) {
            currentTask = listOfTask.get(i);
            stringBuilder.append(INDENT + "  ");
            stringBuilder.append(String.format(" %d.%s\n", i + 1, currentTask.toString()));
        }
        stringBuilder
                .append(String.format("%s  In case you can't count! you have %d task(s) in your list",
                        INDENT, listOfTask.size()));
        return stringBuilder.toString();
    }

    public static String customiseMessage(String message, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " ");
        stringBuilder.append(" Got it. I've added this task:\n");
        stringBuilder.append(INDENT + " ");
        stringBuilder.append("  " + message + "\n");
        stringBuilder.append(INDENT + " ");
        stringBuilder.append(String.format(" Now you have %d tasks in list.", size));
        return stringBuilder.toString();
    }

    public static String formatAddedTaskReply(String markedIcon, String taskName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BORDER + "\n");
        stringBuilder.append(INDENT);
        stringBuilder.append("  Very productive! I've slayed this task:\n");
        stringBuilder.append(INDENT);
        stringBuilder.append(String.format("   [%s]%s\n", markedIcon, taskName));
        stringBuilder.append(BORDER);
        return stringBuilder.toString();
    }
}
