package com.HoJun.app.Repository;

import com.HoJun.app.domain.Student;
import org.springframework.data.repository.CrudRepository;

/* 학생을 위한 데이터베이스에 접근할 수 있는 인터페이스를 만들어야 한다
* CrudRepository class 를 상속받는다.
* JPA 처리를 담당하는 Repository 는 기본적으로 4가지가 있다.
* 1. Repository<T, ID> 2. CrudRepository<T, ID> 3. PagingAndSortingRepository<T, ID> 4. JpaRepository<T, ID>
* T 는 Entity 의 타입 클래스이고 ID 는 PK 값의 타입이다.
* CrudRepository 는 관리되는 Entity class 에 대해 정교한 CRUD 기능을 제공한다.
*/
public interface StudentRepository extends CrudRepository<Student, Long> {
}
