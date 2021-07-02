package app.ldev.dio.person.personapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonalNotFoundException extends Exception {
    public PersonalNotFoundException(Long id) {
        super("Person not found with ID " + id);
    }
}
