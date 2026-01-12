package aciccone.climatechange10.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationExceptions extends  RuntimeException{
    public ValidationExceptions(String message){super(message);
    }
}


