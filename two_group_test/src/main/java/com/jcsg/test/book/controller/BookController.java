package com.jcsg.test.book.controller;

import com.jcsg.test.book.domain.dto.BookDto;
import com.jcsg.test.book.domain.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author 이영훈
 *
 * 도서 관리 시스템의 컨트롤러 클래스.
 * 도서 등록, 조회, 수정, 삭제와 관련된 클라이언트 요청을 처리하며
 * 각 요청에 대해 알맞은 서비스 로직을 호출하고 결과를 뷰에 전달한다.
 *
 * 주요 요청 경로
 * - /book/bookAdd - 도서 등록
 * - /book/bookList - 전체 도서 조회
 * - /book/bookRead/{bookCode} - 단일 도서 조회
 * - /book/bookUpdate/{bookCode} - 도서 수정
 * - /book/bookDelete - 도서 삭제
 */
@Controller
@RequestMapping("/book")
public class BookController {

    /** BookServiceImpl 의존성 주입 */
    @Autowired
    private BookServiceImpl bookServiceImpl;

    /**
     * 도서 등록 페이지를 반환하는 메서드.
     * 사용자가 도서 등록 양식을 작성할 수 있는 페이지로 이동시킨다.
     * @return 도서 등록 페이지 뷰 이름 (book/bookAdd)
     */
    @GetMapping("/bookAdd")
    public String bookAdd() {
        return "book/bookAdd";
    }

    /**
     * 도서 등록 요청을 처리하는 메서드.
     * 클라이언트에서 전달받은 도서 정보를 데이터베이스에 저장한다.
     *
     * @param bookDto 등록할 도서의 정보
     * @return 도서 목록 페이지로 리다이렉트
     */
    @PostMapping("/bookAdd")
    public String bookAdd_post(BookDto bookDto) {
        bookServiceImpl.addBook(bookDto);
        return "redirect:/book/bookList";
    }

    /**
     * 전체 도서 목록 조회 요청을 처리하는 메서드.
     * 데이터베이스에 저장된 모든 도서 정보를 가져와 뷰에 전달한다.
     *
     * @param model 뷰에 데이터를 전달하기 위한 객체
     * @return 도서 목록 페이지 뷰 이름 (book/bookList)
     */
    @GetMapping("/bookList")
    public String bookList(Model model) {
        model.addAttribute("books", bookServiceImpl.getBooks());
        return "book/bookList"; // 뷰 반환
    }


    /**
     * 특정 도서 조회 요청을 처리하는 메서드.
     * 도서 코드로 조회한 도서 정보를 뷰에 전달한다.
     *
     * @param bookCode 조회할 도서의 코드
     * @param model 뷰에 데이터를 전달하기 위한 객체
     * @return 도서 단건 조회 페이지 뷰 이름 (book/bookRead)
     */
    @GetMapping("/bookRead/{bookCode}")
    public String bookRead(@PathVariable("bookCode") Long bookCode, Model model) {
        model.addAttribute("bookDto", bookServiceImpl.getBook(bookCode));
        return "book/bookRead";
    }

    /**
     * 도서 수정 페이지 요청을 처리하는 메서드 (GET).
     * 특정 도서의 정보를 수정하기 위한 데이터를 뷰에 전달한다.
     *
     * @param bookCode 수정할 도서의 코드
     * @param model 뷰에 데이터를 전달하기 위한 객체
     * @return 도서 수정 페이지 뷰 이름 (book/bookUpdate)
     */
    @GetMapping("/bookUpdate/{bookCode}")
    public String bookUpdate(@PathVariable("bookCode") Long bookCode, Model model) {
        model.addAttribute("bookDto", bookServiceImpl.getBook(bookCode));
        return "book/bookUpdate";
    }

    /**
     * 도서 수정 요청을 처리하는 메서드 (POST).
     * 클라이언트에서 전달받은 수정 데이터를 데이터베이스에 반영한다.
     *
     * @param bookDto 수정할 도서의 정보
     * @return 도서 목록 페이지로 리다이렉트
     */
    @PostMapping("/bookUpdate")
    public String bookUpdate(BookDto bookDto) {
        bookServiceImpl.updateBook(bookDto);
        return "redirect:/book/bookList";
    }

    /**
     * 도서 삭제 요청을 처리하는 메서드.
     * 특정 도서를 데이터베이스에서 삭제한다.
     *
     * @param bookCode 삭제할 도서의 코드
     * @return 도서 목록 페이지로 리다이렉트
     */
    @PostMapping("/bookDelete")
    public String bookDelete(@RequestParam("bookCode") Long bookCode) {
        bookServiceImpl.deleteBook(bookCode);
        return "redirect:/book/bookList";
    }

}
