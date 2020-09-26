import java.io.IOException;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Arrays;

/**
 * Interpret the commands given by user and act accordingly.
 */
public class Parser {
    private String curr;
    private StringTokenizer st;

    /**
     * Constructor of an instance of Parser, convert the user command to Enum Command.
     *
     * @param text The line of text input by user.
     */
    public Parser(String text) {
        curr = text;
        st = new StringTokenizer(curr);
    }
/*
    /**
     * Edit the list of tasks according to the user command.
     * Reflect to the user through user interface.
     * Always return true unless user key in "bye".
     *
     * @param ui    The user interface.
     * @param tasks The list of tasks.
     * @return Whether the user wants to continue the program.
     * @throws IOException If an I/O error occurs.
     */
 /*   public boolean canContinue(Ui ui, TaskList tasks) {
        Task newTask;

        try {
            Command cmd = Command.valueOf(st.nextToken());
            switch (cmd) {
            case bye:
                caseBye(ui);
                return false;
            case list:
                caseList(ui, tasks);
                return true;
            case done:
                caseDone(ui, tasks);
                return true;
            case delete:
                caseDelete(ui, tasks);
                return true;
            case todo:
                caseTodo(ui, tasks);
                return true;
            case deadline:
                caseDeadline(ui, tasks);
                return true;
            case event:
                caseEvent(ui, tasks);
                return true;
            case find:
                caseFind(ui, tasks);
                return true;
            }
        } catch (IllegalArgumentException e) {
            caseException(ui);
        }
    }*/

    public boolean canContinue(Ui ui) {
        try {
            Command cmd = Command.valueOf(st.nextToken());
            switch(cmd) {
            case bye:
                return false;
            default:
                return true;
            }
        } catch(IllegalArgumentException e) {
            return true;
        }
    }

    public String dukeReply(Ui ui, TaskList tasks) {
        try {
            Command cmd = Command.valueOf(st.nextToken());
            switch(cmd) {
            case bye:
                return caseBye(ui);
            case list:
                return caseList(ui, tasks) ;
            case done:
                return caseDone(ui, tasks);
            case delete:
                return caseDelete(ui, tasks);
            case todo:
                return caseTodo(ui, tasks);
            case deadline:
                return caseDeadline(ui, tasks);
            case event:
                return caseEvent(ui, tasks);
            case find:
                return caseFind(ui, tasks);
            default:
                return null;
            }
        } catch(IllegalArgumentException e) {
            return caseException(ui);
        }
    }

    public String caseDelete(Ui ui, TaskList tasks) {
        String message = ui.line;
        try {
            String index = st.nextToken();
            int[] deleteList = new int[1000];
            int numDelete = 0;
            while (true) {
                try {
                    int endIdx = index.indexOf(';');
                    if (endIdx == -1) {
                        deleteList[numDelete] = Integer.parseInt(index) - 1;
                        numDelete++;
                        break;
                    } else {
                        deleteList[numDelete] = Integer.parseInt(index.substring(0, endIdx)) - 1;
                        numDelete++;
                    }
                } catch (NoSuchElementException e) {
                    break;
                } catch (NumberFormatException e) {
                    return ui.exp("Please enter a valid number.");
                }
                index = st.nextToken();
            }
            if (numDelete == 0) {
                return ui.exp("Numbers entered are all invalid. No tasks are deleted.");
            }
            Arrays.sort(deleteList, 0, numDelete);
            if(deleteList[0] >= tasks.list.size()) {
                return ui.exp("Number(s) entered are larger than size of task list. No tasks are deleted.");
            }
            message += ui.delete();
            for (int i = 0; i < numDelete; i++ ) {
                try {
                    message += tasks.delete(deleteList[i]-i).readyToPrint() + "\n";
                } catch (IndexOutOfBoundsException e) {
                    return ui.exp(String.valueOf(deleteList[i] + 1) + "is larger than size of task list");
                }
            }
            message += ui.currTask(tasks.list.size());
            message += "\n" + ui.line;
            return message;
        } catch (NoSuchElementException e1) {
            return ui.exp("Please enter at least one number");
        }
    }

    public String caseDone(Ui ui, TaskList tasks) {
        String message = ui.line;
        try {
            String index = st.nextToken();
            int endIdx = index.indexOf(';');
            int idx;
            if (endIdx == -1) {
                idx = Integer.parseInt(index) - 1;
            } else {
                idx = Integer.parseInt(index.substring(0, endIdx)) - 1;
            }
            tasks.done(idx);
            message += ui.done() + tasks.list.get(idx).readyToPrint()+"\n";
            while (true) {
                try {
                    index = st.nextToken();
                    endIdx = index.indexOf(';');
                    if (endIdx == -1) {
                        idx = Integer.parseInt(index) - 1;
                    } else {
                        idx = Integer.parseInt(index.substring(0, endIdx)) - 1;
                    }
                    tasks.done(idx);
                    message += tasks.list.get(idx).readyToPrint()+"\n";
                } catch (NoSuchElementException e) {
                    break;
                }
            }
            message += "\n" + ui.line;
            return message;
        } catch (NoSuchElementException e1) {
            return ui.exp("Please enter at least one number");
        } catch (NumberFormatException e2) {
            return ui.exp("Please enter a valid number to be done");
        } catch (IndexOutOfBoundsException e3) {
            return ui.exp("Number entered is larger than size of task list");
        }
    }

    public String caseTodo(Ui ui, TaskList tasks) {
        String message = ui.line;
        Task newTask;
        try {
            st.nextToken();
            message += ui.add();
            curr = curr.substring(4);
            while (true) {
                int endIdx = curr.indexOf("; ");
                if (endIdx == -1) {
                    newTask = new Task(curr);
                    tasks.add(newTask);
                    message += newTask.readyToPrint() + "\n";
                    break;
                }
                newTask = new Task(curr.substring(0, endIdx));
                tasks.add(newTask);
                message += newTask.readyToPrint() + "\n";
                try {
                    curr = curr.substring(endIdx + 1);
                    st = new StringTokenizer(curr);
                    st.nextToken();
                } catch (IndexOutOfBoundsException | NoSuchElementException e) {
                    break;
                }
            }
            message += ui.currTask(tasks.list.size()) + "\n" + ui.line;
            return message;
        } catch (NoSuchElementException e) {
            return ui.exp("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public String caseDeadline(Ui ui, TaskList tasks) {
        Task newTask;
        String message = ui.line;
        try {
            String test = st.nextToken();
            message += ui.add();
            curr = curr.substring(8);
            while (true) {
                int timeIdx = curr.indexOf(" /by ");
                int endIdx = curr.indexOf("; ");
                if (test.equals("/by")) {
                    message += new DukeException("OOPS!!! The task cannot be empty.").errMsg();
                } else if ((timeIdx == -1) || ((timeIdx > endIdx) && (endIdx !=-1))) {
                    message += new DukeException("OOPS!!! The time cannot be empty.").errMsg();
                } else {
                    if (endIdx == -1) {
                        newTask = new Deadline(curr.substring(0, timeIdx), " (by:" + curr.substring(timeIdx + 4) + ")");
                        tasks.add(newTask);
                        message += newTask.readyToPrint() + "\n";
                        break;
                    }
                    newTask = new Deadline(curr.substring(0, timeIdx), " (by:" + curr.substring(timeIdx + 4, endIdx) + ")");
                    tasks.add(newTask);
                    message += newTask.readyToPrint() + "\n";
                }
                //prepare for next task
                if (endIdx != -1) {
                    try {
                        curr = curr.substring(endIdx + 1);
                        st = new StringTokenizer(curr);
                        test = st.nextToken();
                    } catch (IndexOutOfBoundsException | NoSuchElementException e) {
                        break;
                    }
                }
            }
            message += ui.currTask(tasks.list.size()) + "\n" + ui.line;
            return message;
        } catch (NoSuchElementException e) {
            return ui.exp("OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public String caseEvent(Ui ui, TaskList tasks) {
        String message = ui.line;
        Task newTask;
        try {
            String test = st.nextToken();
            message += ui.add();
            curr = curr.substring(5);
            while (true) {
                int venueIdx = curr.indexOf(" /at ");
                int endIdx = curr.indexOf("; ");
                if (test.equals("/at")) {
                    message += new DukeException("OOPS!!! The task cannot be empty.").errMsg();
                } else if ((venueIdx == -1) || ((venueIdx > endIdx) && (endIdx !=-1))) {
                    message += new DukeException("OOPS!!! The venue cannot be empty.").errMsg();
                } else {
                    if (endIdx == -1) {
                        newTask = new Event(curr.substring(0, venueIdx), " (at:" + curr.substring(venueIdx + 4) + ")");
                        tasks.add(newTask);
                        message += newTask.readyToPrint();
                        break;
                    }
                    newTask = new Event(curr.substring(0, venueIdx), " (at:" + curr.substring(venueIdx + 4, endIdx) + ")");
                    tasks.add(newTask);
                    message += newTask.readyToPrint();
                }
                //prepare for next task
                if (endIdx != -1) {
                    try {
                        curr = curr.substring(endIdx + 1);
                        st = new StringTokenizer(curr);
                        test = st.nextToken();
                    } catch (IndexOutOfBoundsException | NoSuchElementException e) {
                        break;
                    }
                }
            }
            message += ui.currTask(tasks.list.size()) + "\n" + ui.line;
            return message;
        } catch (NoSuchElementException e) {
            return ui.exp("OOPS!!! The description of an event cannot be empty.");
        }
    }


    /**
     * Call DukeException.
     *
     * @param ui User interface.
     */
    public String caseException(Ui ui) {
        return ui.exp("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String caseBye(Ui ui) {
        return ui.bye();
    }
    public  String caseList(Ui ui, TaskList tasks) {
        return ui.line + ui.list() + tasks.print() + "\n" + ui.line;
    }

    /**
     * Search for tasks that contains the keyword and inform user.
     *
     * @param ui User interface.
     * @param tasks Stores the list of tasks.
     */
    public String caseFind(Ui ui, TaskList tasks) {
        String message = ui.line;
        try {
            String keyword = st.nextToken();
            ui.search();
            message += tasks.find(keyword) + "\n" + ui.line;
            return message;
        } catch (NoSuchElementException e1) {
            return ui.exp("OOPS!!! The description of find cannot be empty.");
        }
    }
}
