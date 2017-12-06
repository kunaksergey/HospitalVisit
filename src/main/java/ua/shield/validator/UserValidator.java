package ua.shield.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.shield.entity.User;
import ua.shield.service.UserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "userform.required");

        if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "userform.username.length");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "userform.username.duplicate");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "userform.required");

        if (user.getPassword().length() < 8 || user.getUsername().length() > 32) {
            errors.rejectValue("password", "userform.password.length");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "userform.different.password");
        }
    }
}
