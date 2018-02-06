package ua.shield.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ua.shield.dto.ErrorDto;
import ua.shield.dto.ErrorResponseDto;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class ErrorResponseFactory {
    private final MessageSource messageSource;

    @Autowired
    public ErrorResponseFactory(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ErrorResponseDto createErrorResponse(HttpStatus status, String code, List<FieldError> allFieldErrors) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        List<ErrorDto> errors=allFieldErrors
                .stream()
                .map(fr->new ErrorDto("",messageSource.getMessage(fr,currentLocale))).collect(Collectors.toList());
        return new  ErrorResponseDto(status,code, errors);
    }

}
