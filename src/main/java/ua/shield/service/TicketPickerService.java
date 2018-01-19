package ua.shield.service;

import ua.shield.domen.DateRange;
import ua.shield.domen.TicketPicker;
import ua.shield.entity.Doctor;

import java.util.List;

public interface TicketPickerService {
    List<TicketPicker> findAllInRangeByDoctor(DateRange dateRange, Doctor doctor);
}
