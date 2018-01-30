package ua.shield.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.shield.service.adapter.DoctorMethodServiceAdapter;
import ua.shield.service.adapter.PatientMethodServiceAdapter;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.service.TicketService;

@Component
public class UnitMethodServiceAdapterFactory {
    @Autowired
    private TicketService ticketService;

    public DoctorMethodServiceAdapter getDoctorMethodServiceAdapter(Doctor doctor){
        return new DoctorMethodServiceAdapter(doctor,ticketService);
    }

    public PatientMethodServiceAdapter getPatientMethodServiceAdapter(Patient patient){
        return new PatientMethodServiceAdapter(patient,ticketService);
    }
}
