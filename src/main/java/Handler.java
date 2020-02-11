import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Handler {

    String fileLoc;
//    private File file;

    public Handler(String fileLoc) {
        this.fileLoc = fileLoc;
    }

    public DukeResponse getResponse(String curText, ArrayList<Task> listing) {

        int needExit = 0;

        boolean isTodo = false;
        boolean isDeadline = false;
        boolean isEvent = false;

        String respText;

        try {
            if (curText.equals("bye")) {

                //          exit the program

                respText = "Bye. Hope to see you again soon!";
                needExit = 1;

            } else if (curText.equals("list")) {

                //            query the list of task

                respText = "Here are the tasks in your list:\n";
                for (int i = 0; i < listing.size(); i++) {
                    respText += (i + 1);
                    respText += ". " + listing.get(i) + '\n';
                }

            } else if (isSubstringEqual(curText, "done")) {

                //            done doing task

                int taskNum = Integer.parseInt(curText.substring(4).trim()) - 1;
                respText = "Nice! I've marked this task as done:\n";
                respText += listing.get(taskNum).done();

            } else if ((isTodo = isSubstringEqual(curText, "todo")) ||
                (isDeadline = isSubstringEqual(curText, "deadline")) ||
                (isEvent = isSubstringEqual(curText, "event"))) {

                //          add task to do

                Task tmp = new Task(curText);
                if (isTodo) {
                    try {
                        curText = curText.substring(5).trim();
                        tmp = new Task(curText);
                        if (curText.equals("")) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! The description of a " + tmp.getClass().getSimpleName() + " is not well formatted.");
                    }
                } else {
                    try {
                        String[] parts = curText.split("/");
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
            } else if (isSubstringEqual(curText, "delete")) {
                int taskNum = Integer.parseInt(curText.substring(6).trim()) - 1;
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

    private boolean isSubstringEqual(String oriString, String checkString) {
        return (oriString.substring(0, Math.min(oriString.length(), checkString.length())).equals(checkString));
    }
}
