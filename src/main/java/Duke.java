import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private int flag = 0;
    private ArrayList<task> mylist = new ArrayList<>();
    private Storage storage = new Storage();

    private static String printlist(ArrayList<task> ls) {
        String output;
        output = "Here are the tasks in your list:";

        for (int i = 0; i < ls.size(); i++) {
            output = output + "\n" + (i + 1) + "." + ls.get(i);
        }

        return output;
    }

    private String removetask (ArrayList<task> ls, int ind) throws IOException {
        String output;
        task temp = ls.remove(ind - 1);
        output = "Noted. I've removed this task:";
        output = output + "\n" + temp;
        output = output + "\nNow you have " + ls.size() + " tasks on the list.";
        storage.updateTxtFile(mylist);
        return output;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Step 1. Setting up required components

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        // more code to be added here later
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public String dukeStart() throws IOException {
        String hello = "Hello! I'm Duke\nWhat can i do for you?";
        mylist = storage.readTxtFile();

        if (mylist.size() != 0) {
            hello = hello + "\nYou have " + mylist.size() + " tasks from the previous session";
        }

        return hello;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    public String handleDone(int doneIndex) {
        try {
            task temp = mylist.get(doneIndex - 1);
            temp.markDone();

            String output = "Nice! I've marked this task as done:";
            output = output + "\n" + mylist.get(doneIndex - 1);
            storage.updateTxtFile(mylist);
            return output;
        } catch (IndexOutOfBoundsException | IOException e) {
            return "☹ OOPS!!! You seem to have entered a wrong index.";
        }
    }

    public String handleToDo(String toDoRaw) {
        try {
            String output;
            ToDo temp = new ToDo(toDoRaw.replaceFirst("todo ", ""));
            output = "Got it. I've added this task:\n";
            output = output + temp + "\n";
            output = output + "Now you have " + (mylist.size() + 1) + " tasks in the list.";
            mylist.add(temp);
            storage.updateTxtFile(mylist);
            return output;
        } catch (IndexOutOfBoundsException e) {
            return "☹ OOPS!!! You're missing some descriptions for your todo.";
        } catch (IOException e) {
            return "☹ OOPS!!! You're missing some descriptions for your todo.";
        }
    }

    public String handleDeadline(String deadlineRaw) {
        try {
            String output;
            String wodl = deadlineRaw.replaceFirst("deadline ", "");
            String[] myarr = wodl.split(" /by ");
            DeadLine temp = new DeadLine(myarr[0], myarr[1]);
            output = "Got it. I've added this task:\n";
            output = output + temp + "\n";
            output = output + "Now you have " + (mylist.size() + 1) + " tasks in the list.";
            mylist.add(temp);
            storage.updateTxtFile(mylist);
            return output;
        } catch (IndexOutOfBoundsException | IOException e) {
            return "☹ OOPS!!! You're missing some descriptions for your deadline.";
        }
    }

    public String handleEvent(String eventRaw) {
        try {
            String output;
            String woe = eventRaw.replaceFirst("event ", "");
            String[] myarr = woe.split(" /at ");
            Event temp = new Event(myarr[0], myarr[1]);
            output = "Got it. I've added this task:\n";
            output = output + temp + "\n";
            output = output + "Now you have " + (mylist.size() + 1) + " tasks in the list.";
            mylist.add(temp);
            storage.updateTxtFile(mylist);
            return output;
        } catch (IndexOutOfBoundsException | IOException e) {
            return "☹ OOPS!!! You're missing some descriptions for your event.";
        }
    }

    public String handleDelete(int deleteIndex) {
        try {
            return removetask(mylist, deleteIndex);
        } catch(IndexOutOfBoundsException | IOException e) {
            return "☹ OOPS!!! That index doesn't seems to be missing";
        }
    }

    public String handleFind(String findRaw) {
        String output;
        ArrayList<Integer> searchResults = new ArrayList<Integer>();
        String keyword = findRaw.replaceFirst("find ", "");

        for (int i = 0; i < mylist.size(); i++) {
            if (mylist.get(i).getName().contains(keyword)) {
                searchResults.add(i);
            } else {}
        }

        if (searchResults.size() == 0) {
            output = "Sorry, I can't seem to find anything related to " + keyword;
        } else {
            output = "Here are the matching tasks in your list";

            for (int i = 0; i < searchResults.size(); i++) {
                output = output + "\n" + (i + 1) + "." + mylist.get(searchResults.get(i));
            }
        }
        return output;
    }

    public String handleSnooze(int snoozeIndex, String snoozeRaw) {
        String output = "";
        try {
            task toSnooze = mylist.get(snoozeIndex - 1);
            if (toSnooze.isToDo()) {
                output = output + "☹ OOPS!!! You can't snooze a toDo Task.";
            } else {
                toSnooze.snooze(snoozeRaw);
                output = output + "YAY! Your event has been snoozed to " + snoozeRaw + "\n" + toSnooze;
                storage.updateTxtFile(mylist);
            }
        } catch (IndexOutOfBoundsException | IOException e) {
            output = output + "☹ OOPS!!! You're missing some descriptions for your Snooze.";
        }
        return output;
    }

    public String getResponse(String input) {
        while(flag == 0) {
            String[] inarr = input.split(" ");

            if (input.equals("bye")) {
            flag = 1;
            return "Bye. Hope to see you again soon!";
            } else if (input.equals("list")) {
                return printlist(mylist);
            } else if (inarr[0].equals("done")) {
                return handleDone(Integer.parseInt(inarr[1]));
            } else if (inarr[0].equals("todo")) {
                return handleToDo(input);
            } else if (inarr[0].equals("deadline")) {
                return handleDeadline(input);
            } else if (inarr[0].equals("event")) {
                return handleEvent(input);
            } else if (inarr[0].equals("delete")) {
                return handleDelete(Integer.parseInt(inarr[1]));
            } else if (inarr[0].equals("find")) {
                return handleFind(input);
            } else if (inarr[0].equals("snooze")) {
                return handleSnooze(Integer.parseInt(inarr[1]), inarr[2]);
            } else {
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        }
        return "";
    }
}
