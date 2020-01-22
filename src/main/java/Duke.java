import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
            //Intialise chatbot and scanner objects
            Chatbot bot = new Chatbot("bot");
            Scanner sc = new Scanner(System.in);

            //Set greeting and greet
            String intro = "--------------------------------------------------\n" +
                    "Wassup! Wo shi " + bot.getName() + "\n" +
                    "You want what? \n" +
                    "--------------------------------------------------\n";
            bot.setGreeting(intro);
            bot.greet();

            //Set goodbye
            String goodbye = "--------------------------------------------------\n" +
                    "Why you so ba...bot has been killed\n" +
                    "--------------------------------------------------\n";
            bot.setGoodbye(goodbye);

            //read first input
            String input = sc.nextLine();
            String[] arr = input.split(" ");
            String command = arr[0];
            String description = "";


            //add inputs, list record and mark as done when asked
            while(!command.equals("bye")) {
                try {
                    if(command.equals("list")) {
                        bot.listRecord();
                    } else if(command.equals("done")) {
                        int num = Integer.parseInt(arr[1]);
                        bot.setDone(num);
                    } else if(command.equals("todo")) {
                        if(arr.length == 1) {
                            throw new DukeException("todo");
                        } else {
                            //get task description
                            for(int i = 1; i < arr.length - 1; i++) {
                                description = description + arr[i] + " ";
                            }
                            description = description + arr[arr.length - 1];

                            //add to do
                            bot.addToDo(description);
                        }
                    } else if(command.equals("deadline")) {
                        if(arr.length == 1) {
                            throw new DukeException("deadline");
                        } else {
                            //get task description
                            for(int i = 1; i < arr.length - 1; i++) {
                                description = description + arr[i] + " ";
                            }
                            description = description + arr[arr.length - 1];
                            String[] array = description.split(" /by ");

                            //add deadline
                            bot.addDeadline(array[0], array[1]);
                        }
                    } else if(command.equals("event")) {
                        if(arr.length == 1) {
                            throw new DukeException("event");
                        } else {
                            //get task description
                            for(int i = 1; i < arr.length - 1; i++) {
                                description = description + arr[i] + " ";
                            }
                            description = description + arr[arr.length - 1];
                            String[] array = description.split(" /at ");

                            //add event
                            bot.addEvent(array[0], array[1]);
                        }
                    } else {
                        throw new DukeException("");
                    }

                } catch(Exception e){
                    System.out.println(e);
                }
                    //get new inputs
                    input = sc.nextLine();
                    arr = input.split(" ");
                    command = arr[0];
                    description = "";
            }

            //exit
            bot.goodbye();
    }
}