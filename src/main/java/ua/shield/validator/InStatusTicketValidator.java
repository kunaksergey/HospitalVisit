package ua.shield.validator;

import ua.shield.enum_.StatusTicket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by sa on 13.02.18.
 */
public class InStatusTicketValidator implements ConstraintValidator<InStatusTicket,StatusTicket> {
    @Override
    public void initialize(InStatusTicket constraintAnnotation) {

    }

    @Override
    public boolean isValid(StatusTicket status, ConstraintValidatorContext context) {
        return Arrays.asList(StatusTicket.values()).contains(status);
    }
}
