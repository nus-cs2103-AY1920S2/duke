import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String welcome = "OwO Hello! I am your neckbeard chatbot! \n" + "What can I do for you senpai? *sweats profusely*";
        System.out.println(welcome);

        ArrayList<Task> taskList = new ArrayList<>();

        while(input.hasNextLine()) {
            String taskDescription = input.nextLine();
            String[] taskDesc = taskDescription.split(" ");



            // BYE will terminate the program with a message
            if (taskDescription.equals("bye")) {

                System.out.println("UwU gone so fast? You're a fat bitch anyway.");
                break;



            // LIST will list out all the tasks and show their done status
            } else if (taskDescription.equals("list")) {

                System.out.println("UwU you got some nice tasks:");

                for (int i = 0; i < taskList.size(); i++) {
                    int indexNumber = i + 1;

                    if (taskList.get(i).getStatus() == 0) {
                        System.out.println(indexNumber + "." + taskList.get(i).toString());
                    } else {
                        System.out.println(indexNumber + "." + taskList.get(i).toString());
                    }

                }



            // DONE will mark task as done with a ticked checkbox
            } else if (taskDesc[0].equals("done")) {

                String indexString = taskDesc[1];
                int index = Integer.parseInt(indexString);
                Task oldTask = taskList.get(index - 1);
                if (oldTask.getType() == "todo") {

                    ToDo newTask = new ToDo(oldTask.getDescription());
                    newTask.setStatusDone();
                    taskList.set(index - 1, newTask);
                    System.out.println("I... I've marked this as done... notice me pls: \n" + newTask.toString());
                } else if (oldTask.getType() == "deadline") {

                    Deadline newTask = new Deadline(oldTask.getDescription(), oldTask.getBy());
                    newTask.setStatusDone();
                    taskList.set(index - 1, newTask);
                    System.out.println("I... I've marked this as done... notice me pls: \n" + newTask.toString());
                } else if (oldTask.getType() == "event") {

                    Event newTask = new Event(oldTask.getDescription(), oldTask.getBy());
                    newTask.setStatusDone();
                    taskList.set(index - 1, newTask);
                    System.out.println("I... I've marked this as done... notice me pls: \n" + newTask.toString());
                }



            // TODO will add a new todo task in the list, checkbox will automatically be [✗]
            } else if (taskDesc[0].equals("todo")) {

                String taskDescString = "";
                for (int i = 1; i < taskDesc.length; i++) {
                    taskDescString += " " +  taskDesc[i];
                }
                ToDo newTask = new ToDo(taskDescString);
                taskList.add(newTask);
                System.out.println("Senpai I have added this task: \n" + "[T][✗] " + newTask.getDescription() + "\n"
                + "Now you have " + taskList.size() + " number of tasks in the list.");



            // DEADLINE will add a new deadline task in the list, with a date and/or time attached to it
            } else if (taskDesc[0].equals("deadline")) {

                String taskDescString = "";
                String deadlineString = "";
                int indexOfSlash = 0;

                // For loop to get the task descriptions until /by is reached
                for (int i = 1; i < taskDesc.length; i++) {

                    if(taskDesc[i].equals("/by")) {
                        indexOfSlash += i;
                        break;
                    }
                    taskDescString += " " + taskDesc[i];
                }

                // For loop to get the due date
                for (int i = indexOfSlash + 1; i < taskDesc.length; i++) {

                    deadlineString += " " + taskDesc[i];
                }

                Deadline newDeadline = new Deadline(taskDescString, deadlineString);
                taskList.add(newDeadline);
                System.out.println("Senpai I have added this event: \n" + "[D][✗]" +
                        newDeadline.getDescription() + " (by:" + deadlineString + ")" + "\n"
                        + "Now you have " + taskList.size() + " number of tasks in the list.");



            // EVENT will add a new Event task to the list with a date and/or time attached to it
            } else if (taskDesc[0].equals("event")) {

                String taskDescString = "";
                String eventString = "";
                int indexOfSlash = 0;

                // For loop to get the task descriptions until /by is reached
                for (int i = 1; i < taskDesc.length; i++) {

                    if(taskDesc[i].equals("/at")) {
                        indexOfSlash += i;
                        break;
                    }
                    taskDescString += " " + taskDesc[i];
                }

                // For loop to get the due date
                for (int i = indexOfSlash + 1; i < taskDesc.length; i++) {

                    eventString += " " + taskDesc[i];
                }

                Event newEvent = new Event(taskDescString, eventString);
                taskList.add(newEvent);
                System.out.println("Senpai I have added this event: \n" + "[E][✗]" +
                        newEvent.getDescription() + " (by:" + eventString + ")" + "\n"
                        + "Now you have " + taskList.size() + " number of tasks in the list.");

            }

        }


    }
}
