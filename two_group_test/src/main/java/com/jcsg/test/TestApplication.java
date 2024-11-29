package com.jcsg.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 도서 관리 시스템의 메인 애플리케이션 클래스.
 * Spring Boot 애플리케이션의 시작점으로 main 메서드를 통해 애플리케이션을 실행한다.
 */
@SpringBootApplication
public class TestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
