package com.hpcl.web;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

 
@ControllerAdvice
public class GlobalExceptionHandler {
 
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
     
   
    @ExceptionHandler(Error.class)
    public ModelAndView handleException(Error ex, HttpServletRequest request){
    	ex.printStackTrace();
        logger.error("Error handler executed");
        ModelAndView model = new ModelAndView();
        model.addObject("error", ClassUtils.getShortName(ex.getClass()));
        model.setViewName("errorPage");
        //returning 404 error code
        return model;
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Throwable ex, HttpServletRequest request){
    	ex.printStackTrace();
        logger.error("Throwable Exception handler executed");
        ModelAndView model = new ModelAndView();
        model.addObject("error", ClassUtils.getShortName(ex.getClass()));
        model.setViewName("errorPage");
        //returning 404 error code
        return model;
    }
    @ExceptionHandler
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order")
    public ModelAndView handleException (NoSuchRequestHandlingMethodException ex) {
    	ex.printStackTrace();
    	ModelAndView model = new ModelAndView();
        model.addObject("error", ClassUtils.getShortName(ex.getClass()));
        model.setViewName("errorPage");
        //returning 404 error code
        return model;
    }
}