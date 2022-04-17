package toyproject.juniorforum.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import toyproject.juniorforum.exception.BoardNotFoundException;
import toyproject.juniorforum.exception.ErrorResponse;
import toyproject.juniorforum.exception.ReplyNotFoundException;

import java.util.Date;

@RestControllerAdvice
@Slf4j
// ResponseEntityExceptionHandler 는 스프링 예외를 처리해둔 ExceptionHandler가 구현되어 있음
public class ApiExceptionController extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public final ErrorResponse handleAllExceptions(Exception ex, WebRequest request) {
        return new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReplyNotFoundException.class)
    public final ErrorResponse handleReplyNotFoundException(Exception ex, WebRequest request) {
        return new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BoardNotFoundException.class)
    public final ErrorResponse handleBoardNotFoundException(Exception ex, WebRequest request) {
        return new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
    }

    // Valid Error 처리
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
