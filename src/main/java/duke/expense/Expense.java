package duke.expense;

import java.time.LocalDate;

public class Expense {

    private String description;
    private double expense;
    private LocalDate date;

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
        return String.format("%s - $%.2f", description, expense);
    }
}