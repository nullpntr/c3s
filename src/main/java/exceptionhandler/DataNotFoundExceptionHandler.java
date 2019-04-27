package exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.c3s.exception.DataNotFoundException;

@ControllerAdvice
public class DataNotFoundExceptionHandler {
  @ResponseBody
  @ExceptionHandler(DataNotFoundException.class)
  // @ResponseStatus(HttpStatus.NOT_FOUND)
  String DataNotFoundHandler(DataNotFoundException ex) {
    return ex.getMessage();
  }
}
