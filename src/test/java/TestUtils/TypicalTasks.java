package testutils;

public class TypicalTasks {

    public static final ToDoTaskStub TODO_TOPICS = new ToDoTaskStub().withDescription(
            "go through topics for this week"
    );

    public static final ToDoTaskStub TODO_IP = new ToDoTaskStub().withDescription("submit week 2 ip");

    public static final EventTaskStub EVENT_W3LECTURE = new EventTaskStub().withDescription("week 3 lecture")
            .withTime("week 3 Friday");
    public static final EventTaskStub EVENT_W4LECTURE = new EventTaskStub().withDescription("week 4 lecture")
            .withTime("week 4 Friday");

    public static final DeadLineTaskStub DL_ASSIGNMENT_1 = new DeadLineTaskStub().withDescription("assignment 1")
            .withTime("week 4");
    public static final DeadLineTaskStub DL_ASSIGNMENT_2 = new DeadLineTaskStub().withDescription("assignment 2")
            .withTime("week 5");

}
