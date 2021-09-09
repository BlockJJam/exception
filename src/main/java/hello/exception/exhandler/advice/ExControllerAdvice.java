package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
//@ControllerAdvice("org.example.controllers")
//@ControllerAdvice(annotations= RestController.class)
//@ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler의 파마리터 IllegalArgumentException.class 이미 메서드 함수에 있음으로 빼기 가능
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e){
        log.error("[exceptionHandler] e", e);
        return new ErrorResult("BAD",e.getMessage());

    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e){
        log.error("[exceptionHandler] e", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        log.error("[exceptionHandler] e",e);
        return new ErrorResult("EX", "내부 오류");
    }
}
