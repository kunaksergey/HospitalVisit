package ua.shield.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.shield.dto.ChieldDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//@Component("beforeCreateChieldValidator")
@Component
public class ChieldDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ChieldDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChieldDto chieldDto = (ChieldDto) target;
        ValidationUtils.rejectIfEmpty(errors, "fullName", "chield.fullname.empty");
        ValidationUtils.rejectIfEmpty(errors, "birthDay", "chield.birthday.empty");
        if (!checkInputString(chieldDto.getBirthDay())) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate.parse(chieldDto.getBirthDay(), formatter);
            } catch (DateTimeParseException ex) {
                errors.rejectValue("birthDay", "chield.birthday.badformat");
            }
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
