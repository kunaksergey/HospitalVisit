package ua.shield.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.shield.dto.UserDto;
import ua.shield.entity.User;
import ua.shield.service.UserService;

import java.util.regex.Pattern;

@Component
public class UserFormValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserDto userForm = (UserDto) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "userform.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "userform.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "userform.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "userform.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "userform.required");

        if (userForm.getUsername().length() < 8 || userForm.getUsername().length() > 32) {
            errors.rejectValue("username", "userform.username.length");
        }

        if (userService.findByUsername(userForm.getUsername()) != null) {
            errors.rejectValue("username", "userform.username.duplicate");
        }

        if (userService.findByEmail(userForm.getEmail()) != null) {
            errors.rejectValue("email", "userform.email.duplicate");
        }

        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(userForm.getEmail()).matches())) {
            errors.rejectValue("email", "userform.email.invalid");
        }

        if (userForm.getPassword().length() < 8 || userForm.getUsername().length() > 32) {
            errors.rejectValue("password", "userform.password.length");
        }

        if (!userForm.getConfirmPassword().equals(userForm.getPassword())) {
            errors.rejectValue("confirmPassword", "userform.different.password");
        }
    }
}
