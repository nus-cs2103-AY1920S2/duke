package duke.expense;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {

    private String description;
    private double expense;
    private LocalDate date;

    /**
     * Creates an add expense.
     * @param description The description of the expense.
     * @param expense The amount of expense.
     * @param date The date of the expense.
     */
    public Expense(String description, double expense, LocalDate date) {
        this.description = description;
        this.expense = expense;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public double getExpense() {
        return expense;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return String.format("[%s] %s - $%.2f", date.format(formatter),
                description, expense);
    }
}