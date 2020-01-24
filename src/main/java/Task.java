import java.util.ArrayList;

public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[Done] " : "[Not Done] "); //return tick or X symbols
        }

        public String getStatusIconInBin(){
            return (isDone ? "1" : "0"); //1 means done, 0 means not done
        }

        public void markDone(){
            this.isDone = true;
        }

    public static String toStringDukeTasks(ArrayList<Task> tasks) {
        String taskString = "";
        for (Task task : tasks) {
            if (task instanceof Todo) {
                taskString += "T/" + task.getStatusIconInBin() + "/";
                taskString += task.description + "\n";
            }
            else if (task instanceof Deadline) {
                taskString += "D/" + task.getStatusIconInBin() + "/";
                taskString += task.description + "/" + ((Deadline) task).day + "\n";
            }
            else {               //instance of event
                taskString += "E/" + task.getStatusIconInBin() + "/";
                taskString += task.description + "/" + ((Event) task).dayAndDuration + "\n";
            }
        }
        return taskString;
    }

    public String toStringTaskstxt(){
            return "";
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
