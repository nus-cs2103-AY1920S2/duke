public class TaskHandler {
    private enum  possibleTasks { todo, deadline, event }

    /**
     * Returns a Task whose type is determined by taskType and the description of which is given by TaskDescription
     * @param taskType should be a permitted taskType
     * @param taskDescription depends on the task but should generally be "TASKDESCRIPTION / TIMING
     * @return a Task if the format is valid
     * @throws InvalidInputException
     */
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

    /**
     * Takes two Strings from the file and then interprets them to return a task following a specific format
     * @param taskType should be a keyword describing the tasktype
     * @param taskDescription should contain taskdescription / timing(if applicable) / isDone
     * @return a Task if the file to load from has a valid storage format
     * @throws InvalidInputException
     */
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
