import java.util.*;

public class Duke {
    private enum  possibleTasks { todo, deadline, event }
    private static Scanner sc = new Scanner(System.in);

    private static Task taskHandler(String taskType) throws InvalidInputException{
        boolean isAllowedTask = false;
        for(possibleTasks task : possibleTasks.values()) {
            if(taskType.equals(task.name())) {
                isAllowedTask = true;
            };
        }

        if(!isAllowedTask) {
            throw new UnknownTaskTypeException();
        }

        String taskDescription = sc.nextLine();
        if(taskDescription.equals("")) {
            throw new EmptyTaskException();
        }
        String[] taskAndTiming = taskDescription.split("/");
        switch(taskType) {
            case "todo":
                return new ToDoTask(taskDescription);
            case "deadline":
                if(taskAndTiming.length < 2) { throw new InvalidInputException();}
                return new DeadlineTask(taskAndTiming[0], taskAndTiming[1]);
            case "event":
                if(taskAndTiming.length < 2) { throw new InvalidInputException(); }
                return new EventTask(taskAndTiming[0], taskAndTiming[1]);
        }
        throw new InvalidInputException();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        List<Task> usrInputs = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        while(true) {
            String command = sc.next();


            boolean wantToQuit = false;
            boolean nothingToAdd = false;
            boolean invalidIndex = false;

            switch (command) {
                case "bye":
                    wantToQuit = true;
                    System.out.println("Goodbye!! See you some time soon.");
                    break;

                case "list":
                    nothingToAdd = true;
                    int count = 1;
                    for (Task usrTask : usrInputs) {
                        System.out.println(count + ". " + usrTask);
                        count++;
                    }
                    break;

                case "done":
                    int index = sc.nextInt();
                    try {
                        if(index > usrInputs.size()) {
                            throw new InvalidIndexException();
                        }
                    } catch(InvalidIndexException e) {
                        System.out.println(e);
                        invalidIndex = true;
                        break;
                    }
                    Task completedTask = usrInputs.get(index - 1);
                    completedTask.setDone();
                    System.out.println("Good job, mate. I have marked the following task as done.\n" + completedTask);
                    nothingToAdd = true;
                    break;

                case "delete":
                    int indice = sc.nextInt();
                    try {
                        if(indice > usrInputs.size()) {
                            throw new InvalidIndexException();
                        }
                    } catch(InvalidIndexException e) {
                        System.out.println(e);
                        invalidIndex = true;
                        break;
                    }
                    Task removedTask = usrInputs.remove(indice - 1);
                    System.out.println("I have removed the following task\n" + removedTask);
                    nothingToAdd = true;
                    break;


            }

            if(wantToQuit) {
                break;
            } else if(nothingToAdd || invalidIndex) {
                continue;
            }

            try {
                Task currentTask = taskHandler(command);
                usrInputs.add(currentTask);
                System.out.println("Got it! I've added the following task \n" + currentTask +
                        "\nNow you have " + usrInputs.size() + " tasks");
            } catch (InvalidInputException e) {
                System.out.println(e);
            }
        }




    }
}
