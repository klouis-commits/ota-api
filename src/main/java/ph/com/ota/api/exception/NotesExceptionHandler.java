package ph.com.ota.api.exception;

import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

/**
 * NotesExceptionHandler class handles exceptions thrown by the NotesController. It
 * provides appropriate responses based on the type of exception.
 */
@ControllerAdvice
public class NotesExceptionHandler {
	
	@ExceptionHandler(KeyAlreadyExistsException.class)
	public ResponseEntity<String> handleResourceNotFoundException(KeyAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<String> handleResourceNotFoundException(NumberFormatException ex) {
		return new ResponseEntity<>("Invalid ID format. " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(NotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleResourceNotFoundException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();
		return new ResponseEntity<>("Validation Errors: " + errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleResourceNotFoundException(HttpMessageNotReadableException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
