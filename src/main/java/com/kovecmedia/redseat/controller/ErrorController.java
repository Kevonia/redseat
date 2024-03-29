package com.kovecmedia.redseat.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorController {

	@RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
		  Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		    
		    if (status != null) {
		        Integer statusCode = Integer.valueOf(status.toString());
		    
		        if(statusCode == HttpStatus.NOT_FOUND.value()) {
		            return "error-404";
		        }
		        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
		            return "error-500";
		        }
		    }
		    return "error";
       
    }

    public String getErrorPath() {
        return null;
    }
    
}
