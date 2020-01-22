public class TasksList {
    protected Task[] tasks = new Task[100];
    protected int nextIndex = 0;

    public Task getTask(int index) {
        return tasks[index];
    }

    public void markDone(String done) {
        if (done.length() <= 5) {
            throw new DukeMissingDescriptionException();
        }
        String num = done.substring(5);
        try {
            Integer.valueOf(num);
        } catch(NumberFormatException e) {
            throw new DukeUnknownInputException();
        }
        int index = Integer.valueOf(num) - 1;
        tasks[index].markDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" + tasks[index]);
        System.out.println("____________________________________________________________");
    }

    public void addTodo(String todo) {
        if (todo.length() <= 5) {
            throw new DukeMissingDescriptionException();
        }
        tasks[nextIndex] = new Todo(todo.substring(5));
        nextIndex++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:\n" + tasks[nextIndex-1]
                + "\nNow you have " + nextIndex + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void addDeadline(String deadline) {
        if (deadline.length() <= 9) {
            throw new DukeMissingDescriptionException();
        }
        String[] splitted = deadline.substring(9).split(" /by ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException();
        }
        tasks[nextIndex] = new Deadline(splitted[0], splitted[1]);
        nextIndex++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:\n" + tasks[nextIndex-1]
                + "\nNow you have " + nextIndex + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void addEvent(String event) {
        if (event.length() <= 6) {
            throw new DukeMissingDescriptionException();
        }
        String[] splitted = event.substring(6).split(" /at ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException();
        }
        tasks[nextIndex] = new Event(splitted[0], splitted[1]);
        nextIndex++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:\n" + tasks[nextIndex-1]
                + "\nNow you have " + nextIndex + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void list() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < nextIndex; i++) {
            System.out.println(i+1 + "." + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }
}
