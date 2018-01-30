package ua.shield.service.adapter;

import ua.shield.domen.DateRange;
import ua.shield.entity.Doctor;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;
import ua.shield.service.TicketService;

import java.util.List;


public class DoctorMethodServiceAdapter implements UnitMethodServiceAdapter {
    private Doctor doctor;
    private TicketService ticketService;
    public DoctorMethodServiceAdapter(Doctor doctor, TicketService ticketService) {
        this.doctor=doctor;
        this.ticketService=ticketService;
    }

    @Override
    public List<Ticket> findAllDateBetween(DateRange range){
        return ticketService.findAllByDoctorInRange(doctor,range);
    }

    @Override
    public List<Ticket> findAllDateStartWith(DateRange range) {
        return ticketService.findAllByDoctorAndDateStartWith(doctor,range);
    }

    @Override
    public List<Ticket> findAllByStatus(StatusTicket statusTicket){
        return ticketService.findAllByDoctorAndStatus(doctor,statusTicket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketService.findAllByDoctor(doctor);
    }
}
