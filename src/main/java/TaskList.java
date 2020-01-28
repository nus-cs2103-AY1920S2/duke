import java.util.*;

public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;
    private int latest_index;
    private Storage storage;

    public TaskList(ArrayList<Task> list, int latest_index, Storage storage) {
        this.list = list;
        this.ui = new Ui();
        this.latest_index = latest_index;
        this.storage = storage;
    }

    public int getLatest_index() {
        return latest_index;
    }

    public void updateIndex() {
        int count = 1;
        for (Task task : list) {
            task.set_Index(count++);
        }
    }

    public void delete(String string) {
        string = string.replace("delete","");
        string = string.trim();

        if (string.equals("all")) {
            list.clear();
            latest_index = 0;
            System.out.println(ui.deleteAll());
            return;
        }

        try {
            int i = Integer.parseInt(string) - 1;

            if (!list.get(i).isDone) {
                System.out.println(ui.unfinishedTask() + list.get(i).toString());
            } else {
                System.out.println(ui.finishedTask() + list.get(i).toString());
            }

            list.remove(i);
            updateIndex();
            latest_index--;

            System.out.println("\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (IndexOutOfBoundsException e) {
            System.out.println(ui.nosuchNumber());

        } catch (NumberFormatException e) {
            System.out.println(ui.inputNumber());
        }
    }


    public void todo(String string) {

        int repeat = checkRepeats(string,"todo");

        if (repeat > 1) {
            System.out.println(ui.oneCommand());
            return;
        }

        string = string.replace("todo", "");
        string = string.trim();



        if (checkEmpty(string)) {
            return;
        }
        Todo todo = new Todo(string, ++latest_index);
        list.add(todo);


        System.out.println("Got it. I have added this task:\n" + todo.toString() +
                "\nNow you have a total of " + latest_index + " Tasks in your list");
    }


    public void done(String string) {
        string = string.replace("done","");
        string = string.trim();
        try {
            int i = Integer.parseInt(string) - 1;

            if (!list.get(i).isDone) {
                list.get(i).isDone = true;
                System.out.println("Nice! You have done this:\n" + list.get(i).toString());
            } else {
                System.out.println("You have already done\n" + list.get(i).toString() + "\nNo need to do it again!");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ui.nosuchNumber());

        } catch (NumberFormatException e) {
            System.out.println(ui.inputNumber());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void deadline(String string) {

        int repeat = checkRepeats(string,"/by");

        if (repeat > 1) {
            System.out.println(ui.oneCommand());
            return;
        }

        string = string.replace("deadline","");
        string = string.trim();

        String[] strings = string.split("/by");

        try {
            if (checkEmpty(strings[0])) {
                return;
            }

            Deadline deadline = new Deadline(strings[0], strings[1],++latest_index);
            list.add(deadline);


            System.out.println("Got it. I have added this task:\n" + deadline.toString() +
                    "\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ui.inputByCmd());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void find(String desc) {
        desc = desc.replace("find","");
        desc = desc.trim();
        boolean at_least_one = false;
        int count = 1;
        StringBuilder sb = new StringBuilder();

        for (Task task : list) {
            if (task.description.contains(desc)) {
                at_least_one = true;
                sb.append(task.get_Index() + ". " + task.toString());
                sb.append("\n");
            }
        }

        if (!at_least_one) {
            System.out.println(ui.noMatchingTasks());
        } else {
            System.out.println(ui.matchingTasks());
            System.out.println(sb.toString());
        }
    }


    public void event(String string) {

        int repeat = checkRepeats(string,"/at");

        if (repeat > 1) {
            System.out.println(ui.oneCommand());
            return;
        }

        string = string.replace("event","");
        string = string.trim();

        String[] strings = string.split("/at");

        try {
            if (checkEmpty(strings[0])) {
                return;
            }

            Event event = new Event(strings[0],strings[1],++latest_index);
            list.add(event);


            System.out.println("Got it. I have added this task:\n" + event.toString() +
                    "\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ui.inputAtCmd());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public int checkRepeats(String string, String repeat) {

        int count = 0;
        String[] strings = string.split(" ");
        for (String s : strings) {
            if (s.equals(repeat)) {
                count++;
            }
        }
        return count;
    }


    public boolean checkEmpty(String cmd) {

        if (cmd.isEmpty()) {
            System.out.println(ui.emptyCmd());
            return true;
        }
        return false;
    }

    public void bye() throws Exception {
        // store
        System.out.println(ui.sayBye());
        storage.writeFile(list);
    }

    public void printList() {

        if (latest_index == 0) {
            System.out.println(ui.emptyList());
        }

        for (Task task : list) {
            System.out.println(task.get_Index() + ". " + task.toString());
        }
    }
}