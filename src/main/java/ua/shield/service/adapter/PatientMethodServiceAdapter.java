package ua.shield.service.adapter;

import ua.shield.domen.DateRange;
import ua.shield.entity.Patient;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;
import ua.shield.service.TicketService;

import java.util.List;


public class PatientMethodServiceAdapter implements UnitMethodServiceAdapter {
    private Patient patient;
    private TicketService ticketService;
    public PatientMethodServiceAdapter(Patient patient, TicketService ticketService) {
        this.patient=patient;
        this.ticketService=ticketService;
    }

    @Override
    public List<Ticket> findAllDateBetween(DateRange range){
        return ticketService.findAllByPatientInRange(patient,range);
    }

    @Override
    public List<Ticket> findAllDateStartWith(DateRange range) {
        return ticketService.findAllByPatientAndDateStartWith(patient,range);
    }

    @Override
    public List<Ticket> findAllByStatus(StatusTicket statusTicket){
        return ticketService.findAllByPatientAndStatus(patient,statusTicket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketService.findAllByPatient(patient);
    }
}
