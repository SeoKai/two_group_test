package com.jcsg.test.book.domain.service;

import com.jcsg.test.book.domain.Entity.Book;
import com.jcsg.test.book.domain.dto.BookDto;
import com.jcsg.test.book.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 심성완, 정민수
 * 도서 관리 시스템의 서비스 클래스.
 * 비즈니스 로직을 처리하며, 데이터베이스와의 상호작용을 위한 Repository를 호출한다.
 *
 * 주요 기능
 * - 도서 추가
 * - 도서 목록 조회
 * - 단일 도서 조회
 * - 도서 수정
 * - 도서 삭제
 */
@Service
public class BookServiceImpl {

    /**
     * BookRepository 의존성 주입
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * DTO → 엔티티 변환 메서드.
     * 클라이언트에서 전달받은 DTO 데이터를 데이터베이스에 저장할 엔티티로 변환한다.
     *
     * @param bookDto 변환할 DTO 객체
     * @return 변환된 Book 엔티티 객체
     */
    private Book dtoToEntity(BookDto bookDto) {
        return Book.builder()
                .bookName(bookDto.getBookName())
                .publisher(bookDto.getPublisher())
                .isbn(bookDto.getIsbn())
                .build();
    }

    /**
     * 엔티티 → DTO 변환 메서드.
     * 데이터베이스에서 조회한 엔티티 데이터를 클라이언트로 반환할 DTO로 변환한다.
     *
     * @param book 변환할 엔티티 객체
     * @return 변환된 BookDto 객체
     */
    private BookDto entityToDto(Book book) {
        return BookDto.builder()
                .bookCode(book.getBookCode())
                .bookName(book.getBookName())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .build();
    }

    /**
     * 도서 추가 메서드.
     * 클라이언트에서 전달받은 도서 정보를 데이터베이스에 저장한다.
     * 모든 항목에 대한 필수 입력값을 검증하며 DTO → 엔티티 변환 후 데이터베이스에 저장한다.
     *
     * @param bookDto 저장할 도서 정보 (DTO)
     */
    public void addBook(BookDto bookDto) {
        if (bookDto.getBookName().isEmpty() || bookDto.getPublisher().isEmpty() || bookDto.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("모든 항목을 입력해야 합니다.");
        }

        Book book = dtoToEntity(bookDto);

        bookRepository.save(book);
    }

    /**
     * 도서 목록 조회 메서드.
     * 데이터베이스에서 모든 도서 정보를 조회하고 DTO 리스트로 변환하여 반환한다.
     *
     * @return 모든 도서 정보를 포함한 BookDto 리스트
     */
    public List<BookDto> getBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * 도서 단건 조회 메서드.
     * 특정 도서 코드를 기준으로 데이터를 조회하고 DTO로 변환하여 반환한다.
     * 조회할 특정 도서 코드가 유효한지 검증한다.
     *
     * @param bookCode 조회할 도서의 코드
     * @return 조회된 도서 정보 (DTO)
     */
    public BookDto getBook(Long bookCode) {
        Book book = bookRepository.findById(bookCode)
                .orElseThrow(() -> new IllegalArgumentException("해당 도서를 찾을 수 없습니다."));

        return entityToDto(book);
    }

    /**
     * 도서 수정 메서드.
     * 클라이언트에서 전달받은 데이터를 기반으로 기존 도서 정보를 수정한다.
     * 도서 제목에 대한 필수 입력값을 검증하며 수정 대상 도서가 유효한지 검증한다.
     * 전달받은 데이터로 기존 PK를 유지한 새로운 엔티티 생성하며
     * 수정된 엔티티를 데이터베이스에 저장한다.
     *
     * @param bookDto 수정할 도서 정보 (DTO)
     */
    public void updateBook(BookDto bookDto) {
        if (bookDto.getBookName() == null || bookDto.getBookName().isEmpty()) {
            throw new IllegalArgumentException("도서 제목은 필수 항목입니다.");
        }

        Book currentBook = bookRepository.findById(bookDto.getBookCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 도서를 찾을 수 없습니다."));

        Book updatedBook = Book.builder()
                .bookCode(currentBook.getBookCode())
                .bookName(bookDto.getBookName())
                .publisher(bookDto.getPublisher())
                .isbn(bookDto.getIsbn())
                .build();

        bookRepository.save(updatedBook);
    }

    /**
     * 도서 삭제 메서드.
     * 특정 도서 코드를 기준으로 데이터를 삭제한다.
     *
     * @param bookCode 삭제할 도서의 코드
     */
    public void deleteBook(Long bookCode) {
        bookRepository.deleteById(bookCode);
    }

}
