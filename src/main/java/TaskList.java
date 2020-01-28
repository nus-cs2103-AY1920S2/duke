import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    // load from storage
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // create new empty list
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Event createEvent(String input) throws GooseEmptyDescriptionException, GooseIllegalFormatException {
        String[] eventArr = input.split(" /at ");
        String[] descriptionSplit = eventArr[0].split(" ");
        String description = "";
        for (int i = 1; i < descriptionSplit.length; i++) {
            if (i == descriptionSplit.length - 1) {
                description += descriptionSplit[i];
            } else {
                description += descriptionSplit[i] + " ";
            }
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
            taskList.add(new Event(description, date, time));
            return (Event) taskList.get(taskList.size() - 1);
        }
    }

    public Deadline createDeadline(String input) throws GooseEmptyDescriptionException, GooseIllegalFormatException {
        String[] deadlineArr = input.split(" /by ");
        String[] descriptionSplit = deadlineArr[0].split(" ");
        String description = "";
        for (int i = 1; i < descriptionSplit.length; i++) {
            if (i == descriptionSplit.length - 1) {
                description += descriptionSplit[i];
            } else {
                description += descriptionSplit[i] + " ";
            }
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
            taskList.add(new Deadline(description, date, time));
            return (Deadline) taskList.get(taskList.size() - 1);
        }
    }

    public Todo createTodo(String[] inputArr) throws GooseEmptyDescriptionException {
        String description = "";
        for (int i = 1; i < inputArr.length; i++) {
            if (i == inputArr.length - 1) {
                description += inputArr[i];
            } else {
                description += inputArr[i] + " ";
            }
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of a todo cannot be empty.");
        } else {
            taskList.add(new Todo(description));
            return (Todo) taskList.get(taskList.size() - 1);
        }
    }

    // returns the updated tasklist, ui handles msg
    public ArrayList<Task> deleteTask(int index) throws GooseTaskExistenceException {
        if (index >= taskList.size() || index < 0) {
            throw new GooseTaskExistenceException("You trick Goose? Task " + taskList.size() + " doesn't exist. Honk...");
        }

        taskList.remove(index);
        return this.taskList;

//        String count = taskList.size() == 1
//                ? "\n\n  Now you have " + taskList.size() + " task in the list."
//                : "\n\n  Now you have " + taskList.size() + " tasks in the list.";
//        System.out.println(wrapLine("Honk! Removed this task from the list:\n" + "           " +
//                selected + count));
    }

    // returns the updated tasklist, ui handles msg
    public ArrayList<Task> markDone(int index) throws GooseTaskExistenceException {
        if (index >= taskList.size() || index < 0) {
            throw new GooseTaskExistenceException("You trick Goose? This task doesn't exist. Honk...");
        }

        Task selected = taskList.get(index);
        selected.markAsDone();
        return this.taskList;
//        String reply = "Good job! I've honked it as done:\n";
//        System.out.println(wrapLine(reply + " " + selected));
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
}