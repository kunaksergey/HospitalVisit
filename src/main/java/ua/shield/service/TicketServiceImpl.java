package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Doctor;
import ua.shield.entity.Ticket;
import ua.shield.repository.TicketRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service("ticketService")
public class TicketServiceImpl implements TicketService{
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAllForTwoWeek(Doctor doctor, LocalDate start, LocalDate end) {
        return ticketRepository.findAllByDoctorAndDateGreaterThanEqualAndDateLessThanEqual(
                doctor,start,end);
    }
}
