package com.jcsg.test.book.domain.repository;

import com.jcsg.test.book.domain.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 심성완
 * 도서 관리 시스템의 Repository 인터페이스.
 * 데이터베이스와 직접 상호작용하며, JPA를 통해 기본적인 CRUD 메서드를 제공한다.
 * JpaRepository는 기본적인 CRUD 메서드(save, findById, delete 등)를 제공하므로
 * 추가적인 메서드 정의 없이도 대부분의 데이터 액세스 작업을 처리할 수 있다.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
