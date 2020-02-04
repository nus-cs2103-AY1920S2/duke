public class Ui {
    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void greetDuke() {
        System.out.println("Hey there, Red. Anything I can do for you?");
    }

    public void listTasks(TaskList taskList) {
        taskList.tasks.forEach(task -> System.out.println(String.format(
                "%d. %s",
                (taskList.tasks.indexOf(task) + 1),
                task.toString())));
        System.out.println(String.format(
                "That's %d in the list.", taskList.tasks.size()));
    }


    public void exitDuke() {
        System.out.println("Guess that's enough for now. I've got your back, so you keep me close.");
    }



}
