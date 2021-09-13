package cr.una.taskapp.backend.error;

import javassist.NotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom Rest Exception Handler
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 7/31/21
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     * @param ex the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
            final BindException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            final TypeMismatchException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        String error = "Validation error";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            final MissingServletRequestPartException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        String error = ex.getRequestPartName().concat(" part is missing");
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            final MissingServletRequestParameterException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        String error = ex.getParameterName().concat(" parameter is missing");
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            final MethodArgumentTypeMismatchException ex,
            final WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            final NoHandlerFoundException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(String.format("Could not find the %s method for URL %s",
                ex.getHttpMethod(), ex.getRequestURL()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    // 405

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            final HttpRequestMethodNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(builder.toString());
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            final HttpMediaTypeNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                builder.substring(0, builder.length() - 2), ex));
    }

    // NotFoundException
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(
            final NotFoundException ex,
            final WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage("Element not found");
        apiError.setDebugMessage(ex.getMessage());
        apiError.addSubError(new ApiSubError("001","Element not found"));
        return buildResponseEntity(apiError);
    }

    // DataIntegrityViolationException
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            final DataIntegrityViolationException ex,
            final WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage("Element already exist");
        apiError.setDebugMessage(ex.getMessage());
        apiError.addSubError(new ApiSubError("002","Element already exist in the system"));
        return buildResponseEntity(apiError);
    }

    // ResponseStatusException
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<Object> handleResponseStatusException(
            final ResponseStatusException ex,
            final WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage("error occurred");
        apiError.setDebugMessage(ex.getMessage());
        apiError.addSubError(new ApiSubError("001","Element not found"));
        return buildResponseEntity(apiError);
    }

    // 500
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage("error occurred");
        apiError.setDebugMessage(ex.getMessage());
        apiError.addSubError(new ApiSubError("001","Element not found"));
        return buildResponseEntity(apiError);
    }

    /**
     * Build the Response Entity
     * @param apiError the API Error
     * @return the Response Entity Build
     */
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}