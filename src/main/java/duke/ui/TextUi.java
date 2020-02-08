package duke.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.exception.DukeNoSuchInputException;

public class TextUi implements Ui {
    private Scanner sc;

    public TextUi() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public void begin() {
        print(Ui.WELCOME_MESSAGE);
    }

    @Override
    public String readInput() throws DukeNoSuchInputException {
        try {
            return sc.nextLine();
        } catch (NoSuchElementException e) {
            throw new DukeNoSuchInputException();
        }
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void printException(Exception e) {
        print(e.getMessage());
    }

    @Override
    public void end() {
        print(Ui.GOODBYE_MESSAGE);
        sc.close();
    }
}
