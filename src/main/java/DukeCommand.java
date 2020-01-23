import java.lang.String;
public enum DukeCommand {
    /**
     * Enum Class for all Duke Commands.
     */
    LIST {
        @Override
        public void execute(String s1) {
            Duke.HorizontalLine();
            // Print all the tasks added

            int counter = 1;
            for (Task currtask : Duke.listOfTask) {
                System.out.println(counter++ + "." + currtask);
            }

            Duke.HorizontalLine();
        }
    },
    DONE {
        @Override
        public void execute(String s1) {
            // Split the string to get the
            // index of the task to be done
            String[] arr = s1.split("\\s+");
            int pos = Integer.parseInt(arr[1]) - 1;

            Duke.HorizontalLine();
            System.out.println("Nice! I've marked this as done:");
            (Duke.listOfTask).get(pos).taskIsDone(); // Mark this task as done
            System.out.println((Duke.listOfTask).get(pos));
            Duke.HorizontalLine();
        }
    },
    TODO {
        @Override
        public void execute(String s1) {
            String[] arr = s1.split("\\s+", 2);
            Duke.add(new Todo(s1));
        }
    },
    DEADLINE {
        @Override
        public void execute(String s1) {
            // Manipulating the String by separating the actual command
            // and the word '/by' to get the description and date/time
            int limit = s1.lastIndexOf("/by") - 1;
            String[] arr = s1.split("\\s+", 2);
            int dateindex = arr[1].lastIndexOf("/by");
            String substr = arr[1].substring(dateindex);
            String[] strarr = substr.split("\\s+", 2);

            Duke.add(new Deadline(s1.substring(9, limit), strarr[1]));
        }
    },
    EVENT {
        public void execute(String s1){
            // Manipulating the String by separating the actual command
            // and the word '/at' to get the description and date/time
            int limit = s1.lastIndexOf("/at") - 1;
            String[] arr = s1.split("\\s+", 2);
            int dateindex = arr[1].lastIndexOf("/at");
            String substr = arr[1].substring(dateindex);
            String[] strarr = substr.split("\\s+", 2);

            Duke.add(new Event(s1.substring(6, limit), strarr[1]));
        }
    },
    DELETE {
        @Override
        public void execute(String s1) {
            // Split the string to get the
            // index of the task to be deleted
            String[] arrdel = s1.split("\\s+");
            int pos = Integer.parseInt(arrdel[1]) - 1;

            Duke.HorizontalLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println((Duke.listOfTask).get(pos));
            (Duke.listOfTask).remove(pos); // Mark this task as done

            System.out.println("Now you have " + Duke.listOfTask.size() + " tasks in the list.");
            Duke.HorizontalLine();
        }
    };

    public abstract void execute(String s1);
}