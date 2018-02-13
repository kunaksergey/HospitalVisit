package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.domen.DateRange;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.entity.Schedule;
import ua.shield.entity.Ticket;
import ua.shield.enum_.EvenOddEnum;
import ua.shield.enum_.StatusTicket;
import ua.shield.helper.ScheduleHelper;
import ua.shield.repository.TicketRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("ticketService")
@Transactional
public class TicketServiceImpl implements TicketService {
    private final ScheduleService scheduleService;
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(ScheduleService scheduleService, TicketRepository ticketRepository) {
        this.scheduleService = scheduleService;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket findOne(Integer id){
        return ticketRepository.findOne(id);
    }

    @Override
    public Ticket add(Ticket ticket) {
        ticket.setStatus(StatusTicket.PROCESED);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllForTwoWeek(Doctor doctor, LocalDate start, LocalDate end) {
        return ticketRepository.findAllByDoctorAndDateGreaterThanEqualAndDateLessThanEqual(
                doctor, start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> assambledInRangeByDoctor(DateRange range, Doctor doctor) {
        Set<Ticket> ticketSet = new HashSet<>();
        ticketSet.addAll(findAllByDoctorInRange(doctor,range)); //db tickets
        ticketSet.addAll(virtualTicketList(doctor,range)); //virtual tickets
        return new ArrayList<>(ticketSet);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllByPatient(Patient patient) {
        return ticketRepository.findAllByPatient(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllByDoctor(Doctor doctor) {
        return ticketRepository.findAllByDoctor(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllByPatientInRange(Patient patient,DateRange range) {
        return ticketRepository.findAllByPatientAndDateBetween(
                patient,
                range.getStart(),
                range.getEnd());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllByDoctorInRange(Doctor doctor,DateRange range) {
        return ticketRepository.findAllByDoctorAndDateGreaterThanEqualAndDateLessThanEqual(
                doctor,
                range.getStart(),
                range.getEnd()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllByPatientAndDateStartWith(Patient patient, DateRange range) {
        return ticketRepository.findAllByPatientAndDateGreaterThanEqual(patient,range.getStart());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllByDoctorAndDateStartWith(Doctor doctor, DateRange range) {
        return ticketRepository.findAllByPatientAndDateGreaterThanEqual(doctor,range.getStart());
    }


    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllByDoctorAndStatus(Doctor doctor, StatusTicket statusTicket) {
        return ticketRepository.findAllByDoctorAndStatus(doctor,statusTicket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAllByPatientAndStatus(Patient patient, StatusTicket statusTicket) {
        return ticketRepository.findAllByPatientAndStatus(patient,statusTicket);
    }

    private List<Ticket> virtualTicketList(Doctor doctor,DateRange dateRange) {
        List<Ticket> ticketList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleService.findAllByDoctor(doctor);
        for (int i = 0; i < dateRange.between(); i++) {
            final LocalDate currentDay = LocalDate.now().plusDays(i);
            final DayOfWeek dayOfWeek = currentDay.getDayOfWeek();
            Schedule currentSchedule = ScheduleHelper.defineCurrentSchedule(scheduleList, currentDay);
            final EvenOddEnum evenOrOddDay = (currentDay.getDayOfMonth() % 2 == 0) ? EvenOddEnum.EVEN : EvenOddEnum.ODD;

            currentSchedule.getScheduleDaySet()
                    .stream()
                    .filter(sd -> sd.getEvenOrOdd().equals(evenOrOddDay) && sd.getWeekDay().name().equals(dayOfWeek.name()))
                    .forEach(s -> {
                        List<Ticket> collect = s.getScheduleTimeSet().stream().map(t -> {
                            Ticket ticket = new Ticket();
                            ticket.setDate(currentDay);
                            ticket.setTime(t.getTime());
                            ticket.setDoctor(doctor);
                            ticket.setStatus(StatusTicket.NEW);
                            return ticket;
                        }).collect(Collectors.toList());
                        ticketList.addAll(collect);
                    });
        }
        return ticketList;
    }

}
