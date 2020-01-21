public class Task {
        protected String description;
        protected boolean isDone;
        protected static int taskNum = 0;
        protected int thisTaskNum = 0;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
            taskNum++;
            thisTaskNum = taskNum;
        }

        public String getStatusIcon() {
            return (isDone ? "[Done] " : "[Not Done] "); //return tick or X symbols
        }

        public void markDone(){
            this.isDone = true;
        }

        public void printDone(){
            System.out.println("       " + "[Done] " + description);
        }

        @Override
        public String toString(){
            return "     " + Integer.toString(thisTaskNum) + "." + getStatusIcon() + description;

        }
}
