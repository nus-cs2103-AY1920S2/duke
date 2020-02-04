public class Ui {
    public Ui() {
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(Tracker tracker) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tracker.getTotalTasks(); i++) {
            int itemNo = i + 1;
            Task task = tracker.showList().get(i);
            System.out.println(itemNo + "." + task);
        }
    }

    public void showDone(Tracker tracker, int index) {
        System.out.println("Nice! I've marked this task as done");
        tracker.markDone(index);
        System.out.println("  " + tracker.showList().get(index));
    }

    public void showDelete(Tracker tracker, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tracker.showList().get(index));
        tracker.delete(index);
    }

    public void showAddedTask(Tracker tracker, Parser content) {
        tracker.add(content.getTask());
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + content.getTask());
        System.out.println("Now you have " + tracker.getTotalTasks() + " task(s) in the list.");
    }

    public void showSearchResults(Tracker tracker, String keyword) {
        Tracker results = tracker.find(keyword);

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < results.getTotalTasks(); i++) {
            int itemNo = i + 1;
            Task task = results.showList().get(i);
            System.out.println(itemNo + "." + task);
        }
    }

    public void showError(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
