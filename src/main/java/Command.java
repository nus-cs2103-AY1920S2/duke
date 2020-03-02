package main.java;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 *
 * Class to handle commands given by user
 * */
public class Command {
    private ArrayList<Task> Tasks = new ArrayList<>();
    private final String FILEPATH = "list.txt";
    private final File FILE = new File(FILEPATH);
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Storage storage = new Storage(FILEPATH);
    private TaskList tl = new TaskList(FILEPATH, Tasks);
    private boolean isTaskListLoaded = false;


    /**
     *
     * Constructor
     */
    public Command(){}

    /**
     *
     * Loads tasks into Duke
     */
    protected void loadList() {
        if(isTaskListLoaded) {
        } else {
            isTaskListLoaded = true;
            try {
                tl.loadFromStorage();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * Deals with individual commands given by user
     * @param input
     */
    protected String getResponse(String input) throws DukeException{
        assert FILEPATH.equals("list.txt") : "wrong filepath";
        Ui ui = new Ui(input);
        String command = ui.getCommand();
        String response = "";
        if (command.equals("todo")) {
            try {
                assert isTaskListLoaded : "TaskList not loaded from storage";
                Todo todo = new Todo(ui.getDescription());
                tl.TL.add(todo);
                response = ui.addedTask(todo);
            } catch (Exception e) {
                response = e.getMessage();
            }
            try {
                String S = "";
                for (int i = 0; i < tl.getTL().size(); i++) {
                    S += tl.TL.get(i).toString() + '\n';
                }
                storage.writeToFile(FILEPATH, S);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (command.equals("deadline")) {
            try {
                assert isTaskListLoaded : "TaskList not loaded from storage";
                String desc = ui.getDescription();
                String date = (desc.split("/by "))[1];
                LocalDateTime ldt = LocalDateTime.parse(date, dateTimeFormatter);
                String sldt = ldt.format(dateTimeFormatter);
                Deadline deadline = new Deadline(desc, sldt);
                tl.TL.add(deadline);
                response = ui.addedTask(deadline);
            } catch (Exception e) {
                response = e.getMessage();
            }
            try {
                String S = "";
                for (int i = 0; i < tl.getTL().size(); i++) {
                    S += tl.TL.get(i).toString() + '\n';
                }
                storage.writeToFile(FILEPATH, S);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (command.equals("event")) {
            try {
                assert isTaskListLoaded : "TaskList not loaded from storage";
                String desc = ui.getDescription();
                String date = (desc.split("/at "))[1];
                LocalDateTime ldt = LocalDateTime.parse(date, dateTimeFormatter);
                String sldt = ldt.format(dateTimeFormatter);
                Event event = new Event(desc, sldt);
                tl.TL.add(event);
                response = ui.addedTask(event);
            } catch (Exception e) {
                response = e.getMessage();
            } try {
                String S = "";
                for (int i = 0; i < tl.getTL().size(); i++) {
                    S += tl.TL.get(i).toString() + '\n';
                }
                storage.writeToFile(FILEPATH, S);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (command.equals("done")) {
            try {
                assert isTaskListLoaded : "TaskList not loaded from storage";
                int taskNum = Integer.parseInt(ui.getNumber());
                Task t = tl.TL.get(taskNum - 1);
                response = t.markAsDone();
            } catch (Exception e) {
                response = e.getMessage();
            } try {
                String S = "";
                for (int i = 0; i < tl.getTL().size(); i++) {
                    S += tl.TL.get(i).toString() + '\n';
                }
                storage.writeToFile(FILEPATH, S);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (command.equals("delete")) {
            try {
                assert isTaskListLoaded : "TaskList not loaded from storage";
                int taskNum = Integer.parseInt(ui.getNumber());
                Task t = tl.TL.get(taskNum - 1);
                response = ui.deleteTask(t.toString());
                tl.TL.remove(taskNum - 1);
            } catch (Exception e) {
                response = e.getMessage();
            } try {
                String S = "";
                for (int i = 0; i < tl.getTL().size(); i++) {
                    S += tl.TL.get(i).toString() + '\n';
                }
                storage.writeToFile(FILEPATH, S);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (command.equals("find")) {
            try {
                assert isTaskListLoaded : "TaskList not loaded from storage";
                String rest = ui.getDescription();
                response = ("Here are the tasks in your list that matches:" + rest + "\n");
                response += (tl.getTaskFromKeyword(rest));
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("bye")) {
            try {
                String S = "";
                for (int i = 0; i < tl.getTL().size(); i++) {
                    S += tl.TL.get(i).toString() + '\n';
                }
                storage.writeToFile(FILEPATH, S);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (command.equals("list")) {
            try {
                assert isTaskListLoaded : "TaskList not loaded from storage";
                response = (ui.showList()+"\n");

                for (int i = 0; i < tl.TL.size(); i++) {
                    int j = i + 1;
                    response += (j + tl.TL.get(i).toString() + "\n");
                }
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("viewschedule")) {
            try {
                assert isTaskListLoaded : "TaskList not loaded from storage";
                String rest = ui.getDescription();
                String date = (rest.split("/at "))[1];
                LocalDate ldt = LocalDate.parse(date, dateFormatter);
                String sldt = ldt.format(dateFormatter);
                response = ("Here are the tasks scheduled for:" + sldt + "\n");
                response += (tl.getTaskFromKeyword(sldt));
            } catch (Exception e) {
                response = e.getMessage();
            }
        } else if (command.equals("help")) {
            response = ui.showHelp();
        }
        else {
            try {
                throw new  DukeException("IDK what you mean");
            } catch (Exception E) {
                response = E.getMessage();
            }
        }
        return response;
    }
}
