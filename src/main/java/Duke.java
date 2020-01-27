import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> mainList;
    private static int maxLength;

    public static void main(String[] args) {
        mainList = new ArrayList<>();
        maxLength = 60;
        String logo = " _________                        \n" +
                " __  ____/_______________________ \n" +
                " _  / __ _  __ \\  __ \\_  ___/  _ \\  >(o )___\n" +
                " / /_/ / / /_/ / /_/ /(__  )/  __/   ( ._> /\n" +
                " \\____/  \\____/\\____//____/ \\___/     `---'";

        System.out.println("Hello it's\n" + logo);

        String greeting = "Honk, this Goose. Do you need help with something?";
        System.out.println("\n" + wrapLine(greeting));

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputArr;

        while (!input.equalsIgnoreCase("bye")) {
            try {
                inputArr = input.split(" ");
                checkInput(inputArr);

                if (inputArr[0].equals("list")) {
                    // print list
                    System.out.println(wrapLine("Honk! Here's your task list: \n" + printList()));
                    input = scanner.nextLine();

                } else if (inputArr[0].equals("done")) {
                    // extract list number of task to be marked as done
                    int taskIndex = Integer.parseInt(inputArr[1]) - 1;

                    markDone(taskIndex);
                    input = scanner.nextLine();
                } else if (inputArr[0].equals("delete")) {
                    // extract list number of task to delete
                    int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                    deleteTask(taskIndex);
                    input = scanner.nextLine();
                } else {
                    try {
                        Task inputTask;

                        if (inputArr[0].equals("event")) {
                            inputTask = createEvent(input);

                        } else if (inputArr[0].equals("deadline")) {
                            inputTask = createDeadline(input);

                        } else {
                            inputTask = createTodo(inputArr);
                        }

                        mainList.add(inputTask);
                        if (inputTask.toString().length() > maxLength) {
                            maxLength = inputTask.toString().length() + 2;
                        }

                        String count = mainList.size() == 1
                                ? "\n\n          Now you have " + mainList.size() + " task in the list."
                                : "\n\n          Now you have " + mainList.size() + " tasks in the list.";
                        System.out.println("\n" + wrapLine("Honk! Okay added the task:\n            " +
                                inputTask +
                                count));
                        input = scanner.nextLine();

                    } catch (GooseEmptyDescriptionException | GooseIllegalFormatException e) {
                        System.err.println(wrapLine(e.getMessage()));
                        input = scanner.nextLine();
                    }
                }

            } catch (GooseUnrecognisedException | GooseTaskExistenceException e) {
                System.err.println(wrapLine(e.getMessage()));
                input = scanner.nextLine();
            }
        }
        // when "bye"
        System.out.println(wrapLine("Honk honk!"));
    }

    public static Event createEvent(String input) throws GooseEmptyDescriptionException, GooseIllegalFormatException {
        String[] eventArr = input.split(" /at ");
        String[] descriptionSplit = eventArr[0].split(" ");
        String description = "";
        for (int i = 1; i < descriptionSplit.length; i++) {
            description += " " + descriptionSplit[i];
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of an event cannot be empty.");
        } else {
            if (eventArr.length == 1) {
                throw new GooseIllegalFormatException("Honk! No event date specified.");
            }

            eventArr = eventArr[1].split(" ");
            String date = parseDate(eventArr[0]);
            String time = eventArr[1];
            return new Event(description, date, time);
        }
    }

    public static Deadline createDeadline(String input) throws GooseEmptyDescriptionException, GooseIllegalFormatException {
        String[] deadlineArr = input.split(" /by ");
        String[] descriptionSplit = deadlineArr[0].split(" ");
        String description = "";
        for (int i = 1; i < descriptionSplit.length; i++) {
            description += " " + descriptionSplit[i];
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of a deadline cannot be empty.");
        } else {
            if (deadlineArr.length == 1) {
                throw new GooseIllegalFormatException("Honk! No deadline specified.");
            }

            deadlineArr = deadlineArr[1].split(" ");
            String date = parseDate(deadlineArr[0]);
            String time = deadlineArr[1];
            return new Deadline(description, date, time);
        }
    }

    public static Todo createTodo(String[] inputArr) throws GooseEmptyDescriptionException {
        String description = "";
        for (int i = 1; i < inputArr.length; i++) {
            description += " " + inputArr[i];
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of a todo cannot be empty.");
        } else {
            return new Todo(description);
        }
    }

    public static void deleteTask(int index) throws GooseTaskExistenceException {
        if (index >= mainList.size() || index < 0) {
            throw new GooseTaskExistenceException("You trick Goose? This task doesn't exist. Honk...");
        }

        Task selected = mainList.get(index);
        mainList.remove(index);
        String count = mainList.size() == 1
            ? "\n\n          Now you have " + mainList.size() + " task in the list."
            : "\n\n          Now you have " + mainList.size() + " tasks in the list.";
        System.out.println(wrapLine("Honk! Removed this task from the list:\n" + "           " +
                selected + count));
    }

    public static void markDone(int index) throws GooseTaskExistenceException {
        if (index >= mainList.size() || index < 0) {
            throw new GooseTaskExistenceException("You trick Goose? This task doesn't exist. Honk...");
        }

        Task selected = mainList.get(index);
        selected.markAsDone();

        String reply = "Good job! I've honked it as done:\n";
        System.out.println(wrapLine(reply + "           " + selected));
    }

    public static void checkInput(String[] inputArr) throws GooseUnrecognisedException {
        if (!inputArr[0].equals("list") && !inputArr[0].equals("done") && !inputArr[0].equals("deadline") &&
                !inputArr[0].equals("event") && !inputArr[0].equals("todo") && !inputArr[0].equals("delete") ||
                    inputArr[0].equals("list") && inputArr.length > 1) {
            throw new GooseUnrecognisedException("Honk honk??");
        }
    }

    public static String printList() {
        String formattedList = "";
        if (mainList.size() == 0) {
            formattedList = "           You haven't added any tasks. Honk...";
        } else {
            for (int i = 0; i < mainList.size(); i++) {
                if (mainList.get(i) == null) {
                    break;
                }
                int indexNum = i + 1;
                String item = "           " + indexNum + "." + mainList.get(i);
                if (i != mainList.size() - 1) {
                    item += "\n";
                }
                formattedList += item;
            }
        }

        return formattedList;
    }

    public static String parseDate(String date) {
        String[] dateArr = date.split("/");
        String day = dateArr[0];
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        String month = dateArr[1];
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String year = dateArr[2];

        return year + "-" + month + "-" + day;
    }

    public static String wrapLine(String msg) {
        String top = "         ";
        for (int i = 0; i < maxLength; i++) {
            top += "_";
        }
        top += "\n";

        String bottom = "         ";
        String bottomArrow = "         ";
        for (int i = 0; i < maxLength - 4; i++) {
            bottom += "_";
            bottomArrow += " ";
        }
        bottomArrow += "\\ \n";
        bottom += "  __" + "\n" + bottomArrow;

        return top + "\n" + "          " + msg + "\n" + bottom;
    }
}


