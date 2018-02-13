package ua.shield.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import ua.shield.dto.TicketDoctorDto;
import ua.shield.dto.TicketDto;

@Validated(TicketDoctorDtoValidator.class)
public class TicketDoctorDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TicketDoctorDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TicketDtoValidator ticketDtoValidator = new TicketDtoValidator();
        TicketDoctorDto TicketDoctorDto = (TicketDoctorDto) target;
        ValidationUtils.rejectIfEmpty(errors, "patientFullName", "ticket.doctorId.empty");
        ValidationUtils.rejectIfEmpty(errors, "status", "ticket.status.empty");

//        if (!checkInputString(chieldDto.getBirthDay())) {
//            try {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                LocalDate.parse(chieldDto.getBirthDay(), formatter);
//            } catch (DateTimeParseException ex) {
//                errors.rejectValue("birthDay", "chield.birthday.badformat");
//            }
//        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

}
