package br.com.feliciano.forum.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    @Autowired
    private MessageSource messageSource; // get language locale by request

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class) // catch all exceptions MethodArg...
    public List<ErrorDTO> handle(MethodArgumentNotValidException exception) {
        List<ErrorDTO> errorsDTO = new ArrayList<>();
        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
        fieldErrorList.forEach(e -> errorsDTO
                .add(new ErrorDTO(e.getField(), messageSource.getMessage(e, LocaleContextHolder.getLocale()))));
        return errorsDTO;
    }
}
