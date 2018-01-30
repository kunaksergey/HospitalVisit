package ua.shield.service.adapter;


import ua.shield.domen.DateRange;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;

import java.util.List;

public interface UnitMethodServiceAdapter {
    List<Ticket> findAllDateBetween(DateRange range);
    List<Ticket> findAllDateStartWith(DateRange range);
    List<Ticket> findAllByStatus(StatusTicket statusTicket);
    List<Ticket> findAll();
}
