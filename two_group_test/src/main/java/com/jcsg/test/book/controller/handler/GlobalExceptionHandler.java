package com.jcsg.test.book.controller.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 심성완
 *
 * 도서 관리 시스템의 전역 예외 처리 핸들러 클래스.
 * 애플리케이션 전반에서 발생하는 예외를 처리하며
 * 사용자에게 예외에 대한 적절한 메시지와 에러 페이지를 제공한다.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * IllegalArgumentException 발생 시 처리.
     * 잘못된 매개변수나 비정상적인 인자가 전달될 경우 호출된다.
     *
     * @param exception IllegalArgumentException 객체
     * @return 에러 페이지 뷰와 예외 메시지를 포함한 ModelAndView 객체
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException exception) {
        ModelAndView errorPage = new ModelAndView("error");
        errorPage.addObject("errorMessage", exception.getMessage());

        return errorPage;
    }

    /**
     * 그 외의 예외 Exception 처리.
     * 예기치 못한 오류 발생 시 호출되며, 사용자에게 기본 에러 메시지를 전달한다.
     *
     * @param exception Exception 객체
     * @return 에러 페이지 뷰와 기본 메시지를 포함한 ModelAndView 객체
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception exception) {
        ModelAndView errorPage = new ModelAndView("error");
        errorPage.addObject("errorMessage", "예기치 못한 오류가 발생했습니다.");

        return errorPage;
    }
}
