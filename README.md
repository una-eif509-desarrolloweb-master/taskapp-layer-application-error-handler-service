# Error Handling for REST

eLearning: https://bit.ly/layer-service-error

## Overview

[GitHub - una-eif509-desarrolloweb-master/taskapp-layer-service-restapi-errorhandler: A basic example of a application service layer with the support for Error Handling](https://github.com/una-eif509-desarrolloweb-master/taskapp-layer-service-restapi-errorhandler.git)

Error Handling for REST is a process to handle the errors in an API service.

## Solutions

### @ControllerAdvice

Spring 3.2 brings support for a global @ExceptionHandler with the @ControllerAdvice annotation.

This enables a mechanism that breaks away from the older MVC model and makes use of ResponseEntity along with the type safety and flexibility of @ExceptionHandler:

```java
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
...
    // PriorityNotFoundException
    @ExceptionHandler({PriorityNotFoundException.class})
    public ResponseEntity<Object> handleAll(final PriorityNotFoundException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        //
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(),"error occurred");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
...
}
```

The*@ControllerAdvice* annotation allows us to **consolidate our multiple, scattered \*@ExceptionHandler\*s from before into a single, global error handling component.**

The actual mechanism is extremely simple but also very flexible:

- It gives us full control over the body of the response as well as the status code.
- It provides a mapping of several exceptions to the same method, to be handled together.
- It makes good use of the newer RESTful *ResposeEntity* response.

One thing to keep in mind here is to **match the exceptions declared with \*@ExceptionHandler\* to the exception used as the argument of the method.**

## ResponseStatusException (Spring 5 and Above)

Spring 5 introduced the *ResponseStatusException* class.

We can create an instance of it providing an *HttpStatus* and optionally a *reason* and a *cause*:

```java
/**
     * Delete user by id
     * @param id the id of the entity
     */
    @DeleteMapping("{id}")
    @ResponseBody
    public void deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (PriorityNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Priority Not Found", ex);
        }
    }
```

What are the benefits of using *ResponseStatusException*?

- Excellent for prototyping: We can implement a basic solution quite fast.
- One type, multiple status codes: One exception type can lead to multiple different responses. **This reduces tight coupling compared to the \*@ExceptionHandler\*.**
- We won’t have to create as many custom exception classes.
- We have **more control over exception handling** since the exceptions can be created programmatically.

And what about the tradeoffs?

- There’s no unified way of exception handling: It’s more difficult to enforce some application-wide conventions as opposed to *@ControllerAdvice*, which provides a global approach.
- Code duplication: We may find ourselves replicating code in multiple controllers.

We should also note that it’s possible to combine different approaches within one application.

**For example, we can implement a \*@ControllerAdvice\* globally but also \*ResponseStatusException\*s locally.**

However, we need to be careful: If the same exception can be handled in multiple ways, we may notice some surprising behavior. A possible convention is to handle one specific kind of exception always in one way.

## Resources

https://www.baeldung.com/exception-handling-for-rest-with-spring