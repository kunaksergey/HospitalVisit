package ua.shield.domen;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateRange {
    private LocalDate start;
    private LocalDate end;

    public DateRange(LocalDate start,LocalDate end) {
        this.start=start;
        this.end=end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public long between(){
        if(start.isAfter(end)) return 0;
        return DAYS.between(start,end);
    }
}
