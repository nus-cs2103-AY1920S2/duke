package duke;

public class TaskList {

    public static void printList() {
        if (Duke.commandList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Duke.commandList.size(); i++) {
                int a = i + 1;
                System.out.print(a + ". " + Duke.commandList.get(i));
            }
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, I can't find any task in your list");
        }
    }

    public static void doneTask(Task task) {
        task.setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    public static void deleteTask(Task task) {
        Duke.commandList.remove(task);
        System.out.println("Noted. I've removed this task: ");
        System.out.print(task);
        System.out.println("Now you have "+ Duke.commandList.size() + " tasks in the list.");
    }

    public static void addTask(Task task) {
        Duke.commandList.add(task);
        System.out.print("Got it. I've added this task: \n");
        System.out.print(task);
        System.out.print("Now you have " + Duke.commandList.size() + " tasks in the list.\n");
    }
}
