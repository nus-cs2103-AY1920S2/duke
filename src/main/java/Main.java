import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DukeException{

        Scanner sc = new Scanner(System.in);
        List list = new List();

        System.out.println("Hello Master\nWhat can I do for you today?");

        while(sc.hasNext()){

            try{
                String word = sc.nextLine();

                if(word.equals("bye")){
                    System.out.println("Have a nice day sir!");
                    break;
                }

                else if(word.equals("list")){
                    list.printList();
                }

                else if(word.contains("done")){
                    String[] split = word.split(" ");
                    int num = Integer.parseInt(split[1]);
                    Task toComplete = list.getTask(num);
                    toComplete.setCompleted(true);
                    System.out.println("I shall mark task " + num + " as completed");
                    System.out.println(toComplete);
                }

                else if(word.contains("deadline")){
                    String[] retrieveDateArray = word.split(" /by ");
                    if(retrieveDateArray.length < 2){
                        throw new DukeException("Sorry, the description of a deadline cannot be empty");
                    }
                    String[] retrieveTaskArray = retrieveDateArray[0].split(" ", 2);
                    Deadline deadline = new Deadline(retrieveTaskArray[1], retrieveDateArray[1]);
                    list.setListArray(deadline);
                    list.printCounter();
                }

                else if(word.contains("event")){
                    String[] retrieveDateArray = word.split(" /at ");
                    if(retrieveDateArray.length < 2){
                        throw new DukeException("Sorry, the description of an event cannot be empty");
                    }
                    String[] retrieveTaskArray = retrieveDateArray[0].split(" ", 2);
                    Event event = new Event(retrieveTaskArray[1], retrieveDateArray[1]);
                    list.setListArray(event);
                    list.printCounter();
                }

                else if(word.contains("todo")){
                    String[] retrieveTaskArray = word.split(" ", 2);
                    if(retrieveTaskArray.length < 2){
                        throw new DukeException("Sorry, the description of a todo cannot be empty");
                    }
                    Todo todo = new Todo(retrieveTaskArray[1]);
                    list.setListArray(todo);
                    list.printCounter();
                }

                else {
                    throw new DukeException("I'm sorry, but I do not understand what you mean");
                }
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        sc.close();

    }
}
