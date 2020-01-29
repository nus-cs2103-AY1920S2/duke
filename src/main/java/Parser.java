import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 * This class parses user's input to a command.
 */
public class Parser {
    public Parser() {}
    /**
     * This method returns the appended string without the first word in the input.
     */
    public String append(String[] tmp) {
        String task = "";
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
    public void parse(Ui ui, TaskList list, Storage storage, String temp) {
        try {
            String[] tmp = temp.split(" ");
            String task = append(tmp);
            if (temp.equals("list")) {
                System.out.println(ui.line + "\n" + list);
            } else if (tmp[0].equals("done") || tmp[0].equals("delete")) {
                if (tmp.length < 2) {
                    ui.throwErr("☹ OOPS!!! The index of a task cannot be empty.");
                } else if (Integer.parseInt(tmp[1]) > list.items.size()) {
                    ui.throwErr("☹ OOPS!!! The index of a task is out of range.");
                }
                int index = Integer.parseInt(tmp[1]);
                if (tmp[0].equals("done")) {
                    list.items.get(index - 1).markDone();
                    storage.updateTxt(list.items.get(index - 1).replace(), list.items.get(index - 1).now(), ui);
                } else {
                    storage.updateTxt(list.items.get(index - 1).now(), "", ui);
                    list.delete(index - 1);
                }
            } else if (tmp[0].equals("todo")) {
                if (task.equals("")) {
                    ui.throwDescriptionErr();
                }
                Todo todo = new Todo(task);
                list.addItem(todo);
                storage.addTxt(todo.now(), ui);
            } else if (tmp[0].equals("event")) {
                if (task.equals("")) {
                    ui.throwDescriptionErr();
                }
                String[] e = task.split(" /at ");
                if (e.length < 2) {
                    ui.throwTimeErr();
                }
                Event event = new Event(e[0], LocalDate.parse(e[1]));
                list.addItem(event);
                storage.addTxt(event.now(), ui);
            } else if (tmp[0].equals("deadline")) {
                if (task.equals("")) {
                    ui.throwDescriptionErr();
                }
                String[] d = task.split(" /by ");
                if (d.length < 2) {
                    ui.throwTimeErr();
                }
                Deadline ddl = new Deadline(d[0], LocalDate.parse(d[1]));
                list.addItem(ddl);
                storage.addTxt(ddl.now(), ui);
            }  else if (tmp[0].equals("find")) {
                list.search(task);
            } else {
                ui.throwErr("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalInstructionException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printIndexErr();
        } catch (IOException e) {
            ui.printIOErr();
        } catch (DateTimeParseException e) {
            ui.printDateErr();
        }
    }
}
