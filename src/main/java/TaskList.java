import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void doTask(int idx) {
        Task task = this.tasks.get(idx);
        task.setIsDone(true);
        Ui.printLines("Good job, you completed a task!\n" + "     " + task.toString());
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        Ui.printLines("Added: " + task.toString() + "\n     Now you have " + this.tasks.size() + " task(s) in the list.");
    }

    public void deleteTask(int idx) {
        try {
            Task task = this.tasks.get(idx);
            this.tasks.remove(idx);
            Ui.printLines("Noted. I've removed this task:\n     "
            + task.toString()
            + "\n     Now you have " + this.tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            Ui.printLines("Task index is invalid. Try again!");
        }
    }

    public void manageTodo(Storage storage, String input, String fileName) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("todo");
            } else {
                String description = input.substring(input.indexOf(" ") + 1);
                Todo todo = new Todo(description, false);
                addTask(todo);
                String result = "T~0~" + description;
                storage.writeToFile(fileName, result);
            }
        } catch (EmptyDescriptionException e) {
            Ui.printLines("Oops! The description of a " + e.getMessage() + " cannot be empty.");
        }
    }

    public void manageEvent(Storage storage, String input, String fileName) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("event");
            } else {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);

                String remaining = input.substring(input.indexOf("/") + 1);
                String[] split = remaining.split(" ");

                if (split[0].compareTo("at") == 0) {
                    String time = Parser.parseTime(input.substring(input.indexOf("/") + 4));
                    Event event = new Event(description, false, time);
                    addTask(event);
                    String result = "E~0~" + description + "~" + time;
                    storage.writeToFile(fileName, result);
                } else {
                    throw new InvalidTimeFormatException();
                }
            }
        } catch (EmptyDescriptionException e) {
            Ui.printLines("Oops! The description of an " + e.getMessage() + " cannot be empty.");
        } catch (InvalidTimeFormatException e) {
            Ui.printLines("Your time format is incorrect. Try: /at yyyy-mm-dd 2300");
        } catch (Exception e) {
            Ui.printLines("Sorry, invalid syntax or command. Please try again!");
        }
    }

    public void manageDeadline(Storage storage, String input, String fileName) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("deadline");
            } else {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                
                String remaining = input.substring(input.indexOf("/") + 1);
                String[] split = remaining.split(" ");

                if (split[0].compareTo("by") == 0) {
                    String time = Parser.parseTime(input.substring(input.indexOf("/") + 4));
                    Deadline deadline = new Deadline(description, false, time);
                    addTask(deadline);
                    String result = "D~0~" + description + "~" + time;
                    storage.writeToFile(fileName, result);
                } else {
                    throw new InvalidTimeFormatException();
                }
            }
        } catch (EmptyDescriptionException e) {
            Ui.printLines("Oops! The description of a " + e.getMessage() + " cannot be empty.");
        } catch (InvalidTimeFormatException e) {
            Ui.printLines("Your time format is incorrect. Try: /by [time]");
        } catch (Exception e) {
            Ui.printLines("Sorry, invalid syntax or command. Please try again!");
        }
    }

    @Override
    public String toString() {
        String result = "";
        result += "    ____________________________________________________________";
        if (this.tasks.isEmpty()) {
            result += "\n     Sorry, your list is currently empty!";
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            String str = "\n     " + (i+1) + ".";
            str += task.toString();
            result += str;
        }
        result += "\n    ____________________________________________________________";
        return result;
    }
}