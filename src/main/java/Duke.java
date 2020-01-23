import java.util.*;

public class Duke {
    ArrayList<Task> list;
    int latest_index = 0;

    public void printList() {

        for (int i = 1; i < latest_index + 1; i++) {
            System.out.println(i + ". " + list.get(i-1).toString());
        }
    }


    public void run() {

        System.out.println("Hello ! I'm Ashley Bot\nOi What u want\n");

        list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();

            if (str.isEmpty()) {
                continue;
            }

            System.out.println("--------------------------------------------------------------");
            if (str.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                return;

            } else if (str.equals("list")) {
                printList();

            } else if (str.contains("done")) {
                done(str);

            } else if (str.contains("todo")) {
                todo(str);

            } else if (str.contains("deadline")) {
                deadline(str);

            } else if (str.contains("event")) {
                event(str);

            } else if (str.contains("delete")) {
                delete(str);

            } else {
                System.out.println("OOPs Idk what that means :/\n Try 'todo', 'event' or 'deadline' commands instead!");

            }
            System.out.println("--------------------------------------------------------------\n\n");
        }
    }

    public void delete(String string) {
        string = string.replace("delete","");
        string = string.trim();
        try {
            int i = Integer.parseInt(string) - 1;

            if (!list.get(i).isDone) {
                System.out.println("I have removed an unfinished task:\n" + list.get(i).toString());
            } else {
                System.out.println("I have removed a finished task:\n" + list.get(i).toString());
            }

            list.remove(i);
            latest_index--;

            System.out.println("\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such number on your List! :/");

        } catch (NumberFormatException e) {
            System.out.println("Please input a number instead!");
        }
    }


    public void todo(String string) {

        int repeat = checkRepeats(string,"todo");

        if (repeat > 1) {
            System.out.println("Please use 'todo' only once!");
            return;
        }

        string = string.replace("todo", "");
        string = string.trim();


        if (checkEmpty(string)) {
            return;
        }
        Todo todo = new Todo(string);
        list.add(todo);
        latest_index++;

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
            System.out.println("There is no such number on your List! :/");

        } catch (NumberFormatException e) {
            System.out.println("Please input a number insead!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void deadline(String string) {

        int repeat = checkRepeats(string,"/by");

        if (repeat > 1) {
            System.out.println("Please use '/by' only once!");
            return;
        }

        string = string.replace("deadline","");
        string = string.trim();

        String[] strings = string.split("/by");

        try {
            if (checkEmpty(strings[0])) {
                return;
            }

            Deadline deadline = new Deadline(strings[0], strings[1]);
            list.add(deadline);
            latest_index++;

            System.out.println("Got it. I have added this task:\n" + deadline.toString() +
                    "\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please give me an approximate timing using '/by'!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void event(String string) {

        int repeat = checkRepeats(string,"/at");

        if (repeat > 1) {
            System.out.println("Please use '/at' only once!");
            return;
        }

        string = string.replace("event","");
        string = string.trim();

        String[] strings = string.split("/at");

        try {
            if (checkEmpty(strings[0])) {
                return;
            }

            Event event = new Event(strings[0],strings[1]);
            list.add(event);
            latest_index++;

            System.out.println("Got it. I have added this task:\n" + event.toString() +
                    "\nNow you have a total of " + latest_index + " Tasks in your list");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please give me an approximate timing using '/at'!");

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
            System.out.println("Please do not give an empty command :(");
            return true;
        }
        return false;
    }
}