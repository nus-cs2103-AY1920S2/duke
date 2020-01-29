package test;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadlines extends test.Task{
    private LocalDate date;

    public Deadlines(String newDescripton, String newTime){
        super(newDescripton);
        this.date = LocalDate.parse(newTime);
    }
    public Deadlines(String newDescripton, String newTime, String status){
        super(newDescripton, status);
        this.date = LocalDate.parse(newTime);
    }
    public String getTime(){
        return Integer.valueOf(this.date.getDayOfMonth()).toString() + " "
                + this.date.getMonth().toString() + " "
                + Integer.valueOf(this.date.getYear()).toString() ;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString(){
        return " [D][" + super.getStatusIcon() + "]" + super.description + " "
                + Integer.valueOf(this.date.getDayOfMonth()).toString() + "-"
                    + this.date.getMonth().toString() + "-"
                        + Integer.valueOf(this.date.getYear()).toString() ;
    }
}
