package ua.shield.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import ua.shield.dto.TicketDto;

@Validated(TicketDtoValidator.class)
public class TicketDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TicketDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TicketDto ticketDto = (TicketDto) target;
        ValidationUtils.rejectIfEmpty(errors, "doctorId", "ticket.doctorId.empty");
        ValidationUtils.rejectIfEmpty(errors, "time", "ticket.time.empty");
        ValidationUtils.rejectIfEmpty(errors, "date", "ticket.date.empty");

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
