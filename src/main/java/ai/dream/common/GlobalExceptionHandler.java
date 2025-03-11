package ai.dream.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResultBody<String>> handleServiceException(ServiceException ex) {
        ResultBody<String> response = new ResultBody<>();
        response.setCode(new String()+HttpStatus.BAD_REQUEST.value());
        response.setMsg(ex.getMessage());
        response.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultBody<String>> handleGeneralException(Exception ex) {
        ResultBody<String> response = new ResultBody<>();
        response.setCode(new String()+HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMsg("系统错误，请稍后再试");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}