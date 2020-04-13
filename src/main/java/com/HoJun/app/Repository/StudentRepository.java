package com.HoJun.app.Repository;

import com.HoJun.app.domain.Student;
import org.springframework.data.repository.CrudRepository;

/* 학생을 위한 데이터베이스에 접근할 수 있는 인터페이스를 만들어야 한다
* CrudRepository class 를 상속받는다.
*/
public interface StudentRepository extends CrudRepository<Student, Long> {
}
