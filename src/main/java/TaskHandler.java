public class TaskHandler {
    private enum  possibleTasks { todo, deadline, event }

    public static Task taskHandler(String taskType, String taskDescription) throws InvalidInputException{
        boolean isAllowedTask = false;
        for(possibleTasks task : possibleTasks.values()) {
            if(taskType.equals(task.name())) {
                isAllowedTask = true;
            }
        }
        if(!isAllowedTask) {
            throw new UnknownTaskTypeException();
        }

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

    public static Task parseFromFile(String taskType, String taskDescription) throws InvalidInputException {
        String[] inputs = taskDescription.split("/");
        Task myTask;
        if(inputs.length == 2) {
            myTask = taskHandler(taskType , inputs[0].strip());
        } else if(inputs.length == 3) {
            myTask = taskHandler(taskType, inputs[0].strip() + "/ " + inputs[1].strip());
        } else {
            throw new InvalidInputException();
        }

        if(inputs[inputs.length - 1].equals("1")) {
            myTask.setDone();
        }

        return myTask;


    }
}
