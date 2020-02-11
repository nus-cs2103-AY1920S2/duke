import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


/*
 * Handler
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 11 February 2020
 *
 */

/**
 * The Ui class handles the response
 * for the duke chat platform.
 * @author Daniel Alfred Widjaja
 */
public class Handler {

    String fileLoc;

    /**
     * Initialize the handler class.
     * @param fileLoc The location of the database file.
     */
    public Handler(String fileLoc) {
        this.fileLoc = fileLoc;
    }

    /**
     * Handles the user input and get the response.
     * @param userText The text inputted by the user.
     * @param listing The ArrayList contains the current Tasks.
     * @return The response for the user.
     */
    public DukeResponse getResponse(String userText, ArrayList<Task> listing) {

        int needExit = 0;

        boolean isTodo = false;
        boolean isDeadline = false;
        boolean isEvent = false;

        String respText;

        try {
            if (userText.equals("bye")) {

                //          exit the program

                respText = "Bye. Hope to see you again soon!";
                needExit = 1;

            } else if (userText.equals("list")) {

                //            query the list of task

                respText = "Here are the tasks in your list:\n";
                for (int i = 0; i < listing.size(); i++) {
                    respText += (i + 1);
                    respText += ". " + listing.get(i) + '\n';
                }

            } else if (isSubstringEqual(userText, "done")) {

                //            done doing task

                int taskNum = Integer.parseInt(userText.substring(4).trim()) - 1;
                respText = "Nice! I've marked this task as done:\n";
                respText += listing.get(taskNum).done();

            } else if ((isTodo = isSubstringEqual(userText, "todo")) ||
                (isDeadline = isSubstringEqual(userText, "deadline")) ||
                (isEvent = isSubstringEqual(userText, "event"))) {

                //          add task to do

                Task tmp = new Task(userText);
                if (isTodo) {
                    try {
                        userText = userText.substring(5).trim();
                        tmp = new Task(userText);
                        if (userText.equals("")) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! The description of a " + tmp.getClass().getSimpleName() + " is not well formatted.");
                    }
                } else {
                    try {
                        String[] parts = userText.split("/");
                        String description = parts[0].split(" ", 2)[1];
                        String connector = parts[1].split(" ", 2)[0];
                        String datetime = parts[1].split(" ", 2)[1];
                        if (isDeadline) {
                            tmp = new Deadline(description, connector, datetime);
                        } else if (isEvent) {
                            tmp = new Event(description, connector, datetime);
                        }
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! The description of a " + tmp.getClass().getSimpleName() + " is not well formatted.");
                    }
                }
                listing.add(tmp);
                respText = "Got it. I've added this task:\n";
                respText += tmp + "\n";
                respText += "Now you have " + listing.size() + " tasks in the list.";
            } else if (isSubstringEqual(userText, "delete")) {
                int taskNum = Integer.parseInt(userText.substring(6).trim()) - 1;
                respText = "Noted. I've removed this task:\n";
                respText += listing.get(taskNum) + "\n";
                listing.remove(taskNum);
                respText += "Now you have " + listing.size() + " tasks in the list.\n";
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (DukeException e) {
            respText = e.getMessage();
        } catch (Exception e) {
            respText = e.getMessage();
            //dialogContainer.getChildren().add(DialogBox.getDukeDialog(getDialogLabel(e.getMessage()), new ImageView(duke)));
        } finally {
            try {
                FileWriter fileWriter = new FileWriter(fileLoc);
                for (Task i : listing) {
                    fileWriter.write(i.getFileString() + "\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                respText = e.getMessage();
            }
        }

        return new DukeResponse(respText, needExit);
    }

    /**
     * Checks if the original prefix substring is equals
     * to the checkString.
     * @param oriString The original String.
     * @param checkString The String to compare.
     * @return True if they are equals, False otherwise
     */
    private boolean isSubstringEqual(String oriString, String checkString) {
        return (oriString.substring(0, Math.min(oriString.length(), checkString.length())).equals(checkString));
    }
}
