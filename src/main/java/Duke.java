import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private ArrayList<Task> listing = new ArrayList<>();

    //private Image user = new Image(this.getClass().getResourceAsStream("/src/main/resources/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/main/resources/images/DaDuke.png"));

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    @Override
    public void start(Stage stage) {

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //step 2
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        dialogContainer.getChildren().add(getDialogLabel("Hello! I'm Duke\nWhat can I do for you?"));

        //step 3
        sendButton.setOnMouseClicked((event) -> {
            if (handleUserInput() == 1) {
                stage.close();
            }
        });

        userInput.setOnAction((event) -> {
            if (handleUserInput() == 1) {
                stage.close();
            }
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private int handleUserInput() {

        String curText = userInput.getText();
        int needExit = 0;

        boolean isTodo = false, isDeadline = false, isEvent = false;

        dialogContainer.getChildren().add(getDialogLabel(curText));
        try {
            if (curText.equals("bye")) {

    //          exit the program

                curText = "Bye. Hope to see you again soon!";
                needExit = 1;

            } else if (curText.equals("list")) {

    //            query the list of task

                curText = "Here are the tasks in your list:\n";
                for (int i = 0; i < listing.size(); i++) {
                    curText += (i + 1);
                    curText += ". " + listing.get(i) + '\n';
                }

            } else if (isSubstringEqual(curText, "done")) {

    //            done doing task

                int taskNum = Integer.parseInt(curText.substring(4).trim()) - 1;
                curText = "Nice! I've marked this task as done:\n";
                curText += listing.get(taskNum).done();

            } else if ((isTodo = isSubstringEqual(curText, "todo")) ||
                    (isDeadline = isSubstringEqual(curText, "deadline")) ||
                    (isEvent = isSubstringEqual(curText, "event"))) {

                //          add task to do

                Task tmp = new Task(curText);
                if (isTodo) {
                    try {
                        curText = curText.substring(5).trim();
                        tmp = new Task(curText);
                        if (curText.equals("")) {
                            throw new Exception();
                        }
                    } catch (Exception e){
                        throw new DukeException("☹ OOPS!!! The description of a " + tmp.getClass().getSimpleName() + " is not well formatted.");
                    }
                } else {
                    try {
                        String[] parts = curText.split("/");
                        String description = parts[0].split(" ", 2)[1];
                        String connector = parts[1].split(" ", 2)[0];
                        String datetime = parts[1].split(" ", 2)[1];
                        if (isDeadline) {
                            tmp = new Deadline(description, connector, datetime);
                        } else if (isEvent) {
                            tmp = new Event(description, connector, datetime);
                        }
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! The description of a " + tmp.getClass().getSimpleName() + " is not well formatted.");
                    }
                }
                listing.add(tmp);
                curText = "Got it. I've added this task:\n";
                curText += tmp + "\n";
                curText += "Now you have " + listing.size() + " tasks in the list.";
            } else if (isSubstringEqual(curText, "delete")) {
                int taskNum = Integer.parseInt(curText.substring(6).trim()) - 1;
                curText = "Noted. I've removed this task:\n";
                curText += listing.get(taskNum) + "\n";
                listing.remove(taskNum);
                curText += "Now you have " + listing.size() + " tasks in the list.\n";
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            dialogContainer.getChildren().add(getDialogLabel(curText));
            userInput.clear();
        } catch (DukeException e) {
            dialogContainer.getChildren().add(getDialogLabel(e.getMessage()));
            userInput.clear();
        } catch (Exception e) {
            dialogContainer.getChildren().add(getDialogLabel(e.getMessage()));
            userInput.clear();
        }
        return needExit;
    }

    private boolean isSubstringEqual(String oriString, String checkString){
        return (oriString.substring(0, Math.min(oriString.length(), checkString.length())).equals(checkString));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

}

