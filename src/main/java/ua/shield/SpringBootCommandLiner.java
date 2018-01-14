package ua.shield;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.shield.domen.DateRange;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.entity.Ticket;
import ua.shield.service.DoctorService;
import ua.shield.service.ScheduleService;
import ua.shield.service.TicketService;

import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class SpringBootCommandLiner implements CommandLineRunner {
    @Autowired
    DoctorService doctorService;

    @Autowired
    TicketService ticketService;

    @Autowired
    ScheduleService scheduleService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCommandLiner.class, args);
    }
    @Override
    public void run(String... strings) throws Exception {
        Doctor doctor = doctorService.findOne(3);
        List<Ticket> ticketList = ticketService.assambledInRangeByDoctor(new DateRange(LocalDate.now(), LocalDate.now().plusDays(14)), doctor);
        System.out.println();
    }

}
