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
            for (int j = 0; j < Duke.count; j++) {
                System.out.println(j + 1 + "." + Duke.listOfTask[j]);
            }
            Duke.HorizontalLine();
        }
    },
    DONE {
        @Override
        public void execute(String s1) {
            // Split the string to get the
            // index of the task to be done
            String[] arr = s1.split(" ");
            int pos = Integer.parseInt(arr[1]) - 1;

            Duke.HorizontalLine();
            System.out.println("Nice! I've marked this as done:");
            Duke.listOfTask[pos].taskIsDone(); // Mark this task as done
            System.out.println(Duke.listOfTask[pos]);
            Duke.HorizontalLine();
        }
    };
    public abstract void execute(String s1);
}