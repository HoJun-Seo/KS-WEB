package com.HoJun.app;

import com.HoJun.app.Repository.StudentRepository;
import com.HoJun.app.domain.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	private final StudentRepository studentRepository;

	public AppApplication(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//더미 데이터 저장
		//더미 데이터를 만들어서 DB 에 저장한다.
		//DB에 저장하는 역할? : JPA
		studentRepository.save(new Student("홍길동", "홍길동@길동.com", "010-1234-5678"));
		studentRepository.save(new Student("유관순", "유관순@관순.com", "010-9876-5432"));


	}
}
