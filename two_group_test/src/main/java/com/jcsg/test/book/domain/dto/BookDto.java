package com.jcsg.test.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 심성완
 *
 * 도서 관리 시스템에서 사용되는 DTO 클래스.
 * 클라이언트와 서버 간의 데이터 전송을 위해 사용되며
 * 데이터베이스 엔티티(Book)와는 독립적으로 설계되었다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    /**
     * 주요 필드
     *  - bookCode: 도서 고유 식별 번호 (Primary Key)
     *  - bookName: 도서 제목
     *  - publisher: 도서를 출판한 출판사
     *  - isbn: 도서의 국제 표준 도서 번호
     */
    private long bookCode;
    private String bookName;
    private String publisher;
    private String isbn;

}
