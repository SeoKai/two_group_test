package com.jcsg.test.book.domain.Entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author 심성완
 * 도서 관리 시스템의 Book 엔티티 클래스.
 * 데이터베이스의 "book" 테이블과 매핑되며, 도서 정보를 저장하고 관리한다.
 *
 * 무분별한 객체 생성을 방지하지 위해 @NoArgsConstructor 접근 제한 레벨을 PROTECTED로 설정했으며
 * 충돌 위험성을 방지하기 위해 Book 엔티티의 생성자는 따로 지정하여 @Builder 어노테이션을 적용하였다.
 */
@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {


    /**
     * 주요 필드
     * - bookCode: 도서 고유 식별 번호 (Primary Key)
     *             데이터베이스에서 자동 생성되는 값.
     * - bookName: 도서 제목
     * - publisher: 도서의 출판사
     * - isbn: 도서의 국제 표준 도서 번호 (ISBN)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_code")
    private long bookCode;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn")
    private String isbn;

    @Builder
    public Book(long bookCode, String bookName, String publisher, String isbn) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}
