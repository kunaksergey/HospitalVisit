package ua.shield.domen;

import ua.shield.enum_.StatusTicket;

import java.time.LocalDate;

public class Condition {
    private StatusTicket status;
    private DateRange dateRange;

    public Condition(StatusTicket status, DateRange dateRange) {
        this.status=status;
        this.dateRange=dateRange;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }
}
