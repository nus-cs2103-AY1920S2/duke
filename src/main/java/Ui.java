public class Ui {
    public Ui() {
    }

    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke\n What can I do for you?";
        return logo + greeting;
    }

    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showList(Tracker tracker) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tracker.getTotalTasks(); i++) {
            int itemNo = i + 1;
            Task task = tracker.showList().get(i);
            if (i == tracker.getTotalTasks() - 1) {
                output += itemNo + "." + task;
            } else {
                output += itemNo + "." + task + "\n";
            }
        }

        return output;
    }

    public String showDone(Tracker tracker, int index, Storage storage) {
        assert index <= tracker.getTotalTasks(): "Index given exceeds total tasks";
        tracker.markDone(index);
        String output = "Nice! I've marked this task as done\n"
                + "  " + tracker.showList().get(index);
        storage.saveData(tracker.showList());

        return output;
    }

    public String showDelete(Tracker tracker, int index, Storage storage) {
        assert index <= tracker.getTotalTasks(): "Index given exceeds total tasks";
        String output = "Noted. I've removed this task:\n"
                + "  " + tracker.showList().get(index);
        tracker.delete(index);
        storage.saveData(tracker.showList());

        return output;
    }

    public String showAddedTask(Tracker tracker, Parser content, Storage storage) {
        tracker.add(content.getTask());
        String output = "Got it. I've added this task:\n"
                + "  " + content.getTask() + "\n" + "Now you have "
                + tracker.getTotalTasks() + " task(s) in the list.";
        storage.saveData(tracker.showList());

        return output;
    }

    public String showSearchResults(Tracker tracker, String keyword) {
        Tracker results = tracker.find(keyword);

        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < results.getTotalTasks(); i++) {
            int itemNo = i + 1;
            Task task = results.showList().get(i);
            if (i == results.getTotalTasks() - 1) {
                output += itemNo + "." + task;
            } else {
                output += itemNo + "." + task + "\n";
            }
        }

        return output;
    }

    public String showError(Exception exception) {
        return exception.getMessage();
    }
}
