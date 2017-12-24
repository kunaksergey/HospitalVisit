package ua.shield.entity;

import org.springframework.format.annotation.DateTimeFormat;
import ua.shield.enum_.StatusTicket;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sa on 30.11.17.
 */

public class Ticket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "date__ticket", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date date;

    @Column(name = "time")
    private String time;

    @Enumerated(EnumType.ORDINAL)
    private StatusTicket status;

    @ManyToOne
    private User doctor;

    @ManyToOne
    private User patient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (date != null ? !date.equals(ticket.date) : ticket.date != null) return false;
        if (time != null ? !time.equals(ticket.time) : ticket.time != null) return false;
        return doctor != null ? doctor.equals(ticket.doctor) : ticket.doctor == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        return result;
    }
}
