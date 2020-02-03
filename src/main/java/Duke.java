import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> list;

    public Duke() {
        this.list = new ArrayList<>();
    }

    public static void promptUser(String message) {
        System.out.println(message);
    }

    public void printList() {
        promptUser("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            String stringNew = String.format("%d. %s", i + 1, this.list.get(i));
            promptUser(stringNew);
        }

    }

    public void markDone(int index) {
        promptUser("Nice! I've marked this task as done: ");
        list.get(index-1).markAsDone();
        promptUser(list.get(index-1).toString());
    }

    public void addToDo(String[] words, String input) throws BlankTodoException {
        if(words.length == 1) {
            throw new BlankTodoException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task todo = new ToDo(input.substring(5));
        this.list.add(todo);
        promptUser("Got it. I've added this task:");
        promptUser(todo.toString());
        promptUser(String.format("Now you have %d tasks in your list", list.size()));
    }

    public void addDeadline(String[] words, String input) throws BlankDeadlineException{
        if(words.length == 1) {
            throw new BlankDeadlineException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Task deadline = new Deadline(input.substring(9, input.indexOf('/')),
                input.substring(input.indexOf('/') + 4));
        this.list.add(deadline);
        promptUser("Got it. I've added this task:");
        promptUser(deadline.toString());
        promptUser(String.format("Now you have %d tasks in your list", list.size()));
    }

    public void addEvent(String[] words, String input) throws BlankEventException {
        if(words.length == 1) {
            throw new BlankEventException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Task event = new Event(input.substring(6, input.indexOf('/')),
                input.substring(input.indexOf('/') + 4));
        this.list.add(event);
        promptUser("Got it. I've added this task:");
        promptUser(event.toString());
        promptUser(String.format("Now you have %d tasks in your list", list.size()));
    }

    public void deleteTask(int index) {
        Task deleted = list.get(index - 1);
        this.list.remove(index - 1);
        promptUser("Noted. I've removed this task:");
        promptUser(deleted.toString());
        promptUser(String.format("Now you have %d tasks in your list", list.size()));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        promptUser("Hello! I'm Duke");
        promptUser("What can I do for you?");
        //initiate objects
        Scanner sc = new Scanner(System.in);
        //read user input
        while(true) {
            String input = sc.nextLine();
            String[] words = input.split(" ");
            String firstWord = words[0];
            if (input.equals("bye")){
                promptUser("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")){
                duke.printList();
            } else if (firstWord.equals("done")){
                int num = Integer.parseInt(input.split(" ")[1]);
                duke.markDone(num);
            } else if (firstWord.equals("todo")) {
                try {
                    duke.addToDo(words, input);
                } catch (BlankTodoException e) {
                    promptUser(e.getMessage());
                }
            } else if (firstWord.equals("deadline")) {
                try {
                    duke.addDeadline(words, input);
                } catch(BlankDeadlineException e) {
                    promptUser(e.getMessage());
                }
            } else if (firstWord.equals("event")) {
                try {
                    duke.addEvent(words, input);
                } catch (BlankEventException e) {
                    promptUser(e.getMessage());
                }
            } else if (firstWord.equals("delete")) {
                int deleteNum = Integer.parseInt(words[1]);
                duke.deleteTask(deleteNum);
            } else {
                promptUser("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
