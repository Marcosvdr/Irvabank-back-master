package br.com.irvabank.config;

import br.com.irvabank.exception.ApiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorHandler {

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    String exceptionHandler(HttpServletRequest request,
                            HttpServletResponse response, ApiException ex) {
        response.setStatus(ex.getHttpStatus());
        return ex.getError();
    }

}
