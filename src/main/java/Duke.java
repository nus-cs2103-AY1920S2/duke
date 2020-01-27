import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printLogo();
        printGreeting();

        while (true) {
            try {
                //Split the input line into description and time portions
                String[] input = sc.nextLine().split("/");
                if (input.length == 0) {
                    throw new EmptyInputAelitaException();
                }
                //Further split the description for command checking
                String[] descriptionTokens = input[0].split(" ");

                printDivider();
                if (input.length < 2) {  //Regular commands and Todo
                    if (descriptionTokens[0].toLowerCase().equals("bye")) {
                        break;

                    } else if (descriptionTokens[0].toLowerCase().equals("list")) {
                        //List all task on the specified date
                        if (descriptionTokens.length == 2) {
                            //The command consist of 2 parts: the command and the date
                            try {
                                LocalDate date = LocalDate.parse(descriptionTokens[1]);
                                boolean hasTask = false;
                                for (int i = 0; i < taskList.size(); i++) {
                                    if (taskList.get(i) instanceof Deadline && ((Deadline) taskList.get(i)).by.equals(date)) {
                                        if (!hasTask) {
                                            System.out.println("> Here's your list:");
                                        }
                                        System.out.println("  " + (i + 1) + "." + taskList.get(i));
                                        hasTask = true;
                                    } else if (taskList.get(i) instanceof Event && ((Event) taskList.get(i)).date.equals(date)) {
                                        if (!hasTask) {
                                            System.out.println("> Here's your list:");
                                        }
                                        System.out.println("  " + (i + 1) + "." + taskList.get(i));
                                        hasTask = true;
                                    }
                                }
                                if (!hasTask) {
                                    System.out.println("> You have nothing to do on that day.");
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("> Sorry. I only recognize date in the format YYYY-MM-DD");
                            }
                        } else {
                            if (taskList.size() == 0) {
                                throw new EmptyListAelitaException();
                            }
                            System.out.println("> Here's your list:");
                            for (int i = 0; i < taskList.size(); i++) {
                                System.out.println("  " + (i + 1) + "." + taskList.get(i));
                            }
                        }
                    } else if (descriptionTokens[0].toLowerCase().equals("done")) {
                        if (descriptionTokens.length < 2) {
                            throw new InsufficientArgumentAelitaException("done");
                        }

                        int index = Integer.parseInt(descriptionTokens[1]) - 1;
                        if (index >= taskList.size() || index < 0) {
                            throw new InvalidListItemAelitaException();
                        }
                        taskList.get(index).markAsDone();
                        System.out.println("> Another task off the list. Good job!");
                        System.out.println("  " + taskList.get(index));

                    } else if (descriptionTokens[0].toLowerCase().equals("delete")) {
                        if (descriptionTokens.length < 2) {
                            throw new InsufficientArgumentAelitaException("delete");
                        }

                        int index = Integer.parseInt(descriptionTokens[1]) - 1;
                        if (index >= taskList.size() || index < 0) {
                            throw new InvalidListItemAelitaException();
                        }

                        System.out.println("> The task has been removed.");
                        System.out.println("  " + taskList.get(index));
                        taskList.remove(index);
                        Task.setTotalTaskCount(Task.getTotalTaskCount() - 1);
                        if (Task.getTotalTaskCount() == 0) {
                            System.out.println("  You have no more task today.");
                        } else {
                            System.out.println("  Now you've " + Task.getTotalTaskCount() + " task(s) in your list");
                        }

                    } else if (descriptionTokens[0].toLowerCase().equals("todo")) {
                        if (descriptionTokens.length == 1) {
                            throw new InsufficientArgumentAelitaException("description");
                        }
                        StringBuilder builder = new StringBuilder(descriptionTokens[1]);
                        for (int i = 2; i < descriptionTokens.length; i++) {
                            builder.append(" ");
                            builder.append(descriptionTokens[i]);
                        }

                        taskList.add(new Todo(builder.toString()));
                        System.out.println("> I've got your back. Adding the new task:");
                        System.out.println("  " + taskList.get(taskList.size() - 1).toString());
                        System.out.println("  Now you've " + Task.getTotalTaskCount() + " task(s) in your list");

                    } else if (descriptionTokens[0].toLowerCase().equals("deadline") || descriptionTokens[0].toLowerCase().equals("event")) {
                        if (descriptionTokens.length == 1) {
                            //Description is missing
                            throw new InsufficientArgumentAelitaException("description");
                        } else {
                            //Timing is missing
                            throw new InsufficientArgumentAelitaException("time");
                        }

                    } else {
                        throw new InvalidCommandAelitaException();

                    }
                } else {
                    if (descriptionTokens.length < 2) {
                        throw new InsufficientArgumentAelitaException("description");
                    }
                    //Concat the description tokens back to one string
                    StringBuilder description = new StringBuilder(descriptionTokens[1]);
                    for (int i = 2; i < descriptionTokens.length; i++) {
                        description.append(" ");
                        description.append(descriptionTokens[i]);
                    }

                    //Check what type of task
                    if (descriptionTokens[0].toLowerCase().equals("deadline")) {
                        try {
                            LocalDate date = LocalDate.parse(input[1].substring(3));
                            taskList.add(new Deadline(description.toString(), date));
                            System.out.println("> I've got your back. Adding the new task:");
                            System.out.println("  " + taskList.get(taskList.size() - 1).toString());
                            System.out.println("  Now you've " + Task.getTotalTaskCount() + " task(s) in your list");
                        } catch (DateTimeParseException e) {
                            System.out.println("> Sorry. I only recognize date in the format YYYY-MM-DD");
                        }

                    } else if (descriptionTokens[0].toLowerCase().equals("event")) {

                        String[] dateTime = input[1].split(" ");  //Get the end timing
                        if (dateTime.length < 3) {
                            throw new InsufficientArgumentAelitaException("date-time");
                        }
                        String[] splitTime = dateTime[2].split("-"); //Seperate start time and end time
                        if (splitTime.length < 2) {
                            throw new InsufficientArgumentAelitaException("end time");
                        }

                        try {
                            LocalDate date = LocalDate.parse(dateTime[1]);
                            taskList.add(new Event(description.toString(), date, splitTime[0], splitTime[1]));
                            System.out.println("> I've got your back. Adding the new task:");
                            System.out.println("  " + taskList.get(taskList.size() - 1).toString());
                            System.out.println("  Now you've " + Task.getTotalTaskCount() + " task(s) in your list");
                        } catch (DateTimeParseException e) {
                            System.out.println("> Sorry. I only recognize date in the format YYYY-MM-DD");
                        }

                    } else {
                        throw new InvalidCommandAelitaException();
                    }
                }
            } catch (EmptyInputAelitaException e) {
                System.out.println("> Aren't you a quiet type.");
            } catch (InvalidCommandAelitaException e) {
                System.out.println("> I don't understand your request.");
            } catch (InsufficientArgumentAelitaException e) {
                switch (e.getMessage()) {
                    case "date-time":
                        System.out.println("> I don't recognize the date or time.");
                        break;
                    case "delete":
                        System.out.println("> Which task do you want to delete?");
                        break;
                    case "description":
                        System.out.println("> What is the task about?");
                        break;
                    case "done":
                        System.out.println("> Which task have you completed?");
                        break;
                    case "end time":
                        System.out.println("> When is the deadline?");
                        break;
                    case "time":
                        System.out.println("> The time is missing.");
                        break;
                }
            } catch (EmptyListAelitaException e) {
                System.out.println("> You have nothing to do today.");
            } catch (DuplicateMarkAelitaException e) {
                System.out.println("> You have already done that task.");
            } catch (InvalidListItemAelitaException e) {
                System.out.println("> That item is not on your list.");
            }
            printDivider();
        }
        System.out.println("> It's nice talking to you. See you soon! ;)");
    }

    private static void printDivider() {
        System.out.println("--------------------------------------------");
    }

    private static void printGreeting() {
        System.out.println("> Hi! I'm Aelita, guardian of Lyoko.");
        System.out.println("  How can I help you?");
        printDivider();
    }

    private static void printLogo() {
        final String logo = "     __             _   _     _\n"
                + "    /  \\           | | / \\   | |\n"
                + "   / /\\ \\     ___  | | \\_/ __| |__   ___ _\n"
                + "  / /__\\ \\   / _ \\ | |  _ |__   __| / _ | |\n"
                + " / ______ \\ |  __/ | | | |   | |   | |_|  |\n"
                + "/_/      \\_\\ \\___\\ |_| |_|   |_|    \\___/\\_\\";
        System.out.println(logo);
        System.out.println("============================================");
    }
}
