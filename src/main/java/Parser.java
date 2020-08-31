import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 * This class parses user's input to a command.
 */

public class Parser {
    public Parser() {
    }

    /**
     * Returns the appended string without the first word in the input.
     */
    public String append(String[] tmp) {
        String task = "";
        assert tmp.length >= 1;
        for (int i = 1; i < tmp.length; i++) {
            task += tmp[i];
            if (i != tmp.length - 1) {
                task += " ";
            }
        }
        return task;
    }

    /**
     * This method parses user input and executes the required instruction.
     */
    public String parse(Ui ui, TaskList list, Storage storage, String temp) {
        try {
            String[] tmp = temp.split(" ");
            String task = append(tmp);
            if (temp.equals("list")) {
                return list.toString();
            } else if (temp.equals("bye")) {
                return ui.bye();
            } else if (temp.equals("sortAsc")) {
                return list.sortAsc().toString();
            } else if (temp.equals("sortDes")) {
                return list.sortDes().toString();
            } else if (tmp[0].equals("done") || tmp[0].equals("delete")) {
                if (tmp.length < 2) {
                    return ("OOPS!!! The index of a task cannot be empty.");
                } else if (Integer.parseInt(tmp[1]) > list.items.size()) {
                    return ("OOPS!!! The index of a task is out of range.");
                }
                assert tmp.length >= 2;
                int index = Integer.parseInt(tmp[1]);
                if (tmp[0].equals("done")) {
                    list.items.get(index - 1).markDone();
                    storage.updateTxt(list.items.get(index - 1).tobeReplaced(),
                            list.items.get(index - 1).currentString(), ui);
                    assert list.items.get(index - 1).getDone() == true;
                    return ui.markDone(list.items.get(index - 1));
                } else {
                    storage.updateTxt(list.items.get(index - 1).currentString(), "", ui);
                    list.delete(index - 1);
                    return ui.removeTask(list, index);
                }
            } else if (tmp[0].equals("todo")) {
                if (task.equals("")) {
                    return "Description cannot be empty";
                }
                Todo todo = new Todo(task);
                list.addItem(todo);
                storage.addTxt(todo.currentString(), ui);
                return ui.addTask(list);
            } else if (tmp[0].equals("event")) {
                if (task.equals("")) {
                    return "Description cannot be empty";
                }
                String[] e = task.split(" /at ");
                if (e.length < 2) {
                    return "Time cannot be empty";
                }
                Event event = new Event(e[0], LocalDate.parse(e[1]));
                list.addItem(event);
                storage.addTxt(event.currentString(), ui);
                return ui.addTask(list);
            } else if (tmp[0].equals("deadline")) {
                if (task.equals("")) {
                    return "Description cannot be empty";
                }
                String[] d = task.split(" /by ");
                if (d.length < 2) {
                    return "Time cannot be empty";
                }
                Deadline ddl = new Deadline(d[0], LocalDate.parse(d[1]));
                list.addItem(ddl);
                storage.addTxt(ddl.currentString(), ui);
                return ui.addTask(list);
            }  else if (tmp[0].equals("find")) {
                return ui.searchTask() + list.search(task);
            } else {
                return ("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalInstructionException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printIndexErr();
        } catch (IOException e) {
            ui.printIOerr();
        } catch (DateTimeParseException e) {
            ui.printDateErr();
        }
        return "Task completed";
    }
}
