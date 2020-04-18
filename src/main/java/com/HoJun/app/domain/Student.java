package com.HoJun.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//JPA Annotation
//객체(Entity) 와 데이터베이스 Table 을 매핑할 때 해당 Annotation 을 사용한다.
@Entity // JPA 에서 테이블에 매핑할 클래스에 붙여준다. 해당 클래스는 Entity 라 부른다.
/*
 * 기본 생성자는 필수(default constructor) 는 필수, final class, enum, interface, inner class 에 사용불가, final 필드 사용 불가
 * runtime 시에 javassist 에 의해 Entity 의 서브클래스가 생성되기 때문, 클래스 상속 불가하면 안됨
 * @Entity Annotation(주석) 은 데이터베이스의 테이블과 일대일로 매칭되는 객체 단위
 * Entity 객체의 인스턴스 하나가 테이블에서 하나의 레코드 값을 의미한다.
 * 그래서 객체의 인스턴스를 구분하기 위한 유일한 키 값을 가지는데 이것은 테이블 상의 PK 와 같은 의미를 가지며 @Id Annotation 으로 표기된다.
 * 이때 명시적으로 @Entity 의 name 속성을 이용해 데이터베이스 상의 실제 테이블 명칭을 지정하지 않는다면
 * Entity class 의 이름 그대로 CamelCase 를 유지한 채 테이블이 생성되기 때문에 테이블 이름을 명시적으로 작성하는 것이 관례이다.
 * 왜냐하면 데이터베이스 상에서 보편적으로 사용되는 명명법은 UnderScore 가 원칙이기 때문이다.
 * 신규로 구축되는 사이트의 경우에는 CamelCase 형태의 이름을 가지는 테이블을 사용해도 무방하겠지만, 기존에 이미 테이블이 설계되어 있는 사이트에
 * JPA 를 적용하거나 다른 개발자 혹은 DBA 와 커뮤니케이션을 위해서라도 가급적이면 UnderScore 를 이용한 테이블 명명 규칙을 이용하는 것이 좋다.
 */
public class Student {
    @Id //Entity 의 기본 키 설정 (기본키 매핑) - 해당 클래스에서는 Long id 필드를 PK 로 매핑한다.
    /*
     * 데이터베이스의 테이블은 기본적으로 유일한 값을 가진다. 그것을 PK 라고 하는데, 데이테베이스는 이 유일한 키값을 기준으로
     * 질의한 데이터를 추출해 결과셋으로 반환해준다. 테이블 상에 PK 가 없는 테이블도 있지만 대부분의 경우 반드시 PK 가 존재한다.
     * JPA 에서도 Entity class 상에 해당 PK 를 명시적으로 표시를 해야 되는데 그것을 @Id Annotation 을 이용해 이것이 PK 임을 지정한다.
     */
    @GeneratedValue(strategy = GenerationType.AUTO) //기본키 매핑 전략
    /*
     * AUTO 전략 : 데이터베이스의 벤더에 따라 IDENTITY, SEQUENCE, TABLE 중 하나를 선택해준다.(기본 키 자동생성)
     * 장점 : 데이터베이스의 벤더가 바뀌어도 코드에 수정이 없다.
     * IDENTITY 전략 : 기본키 생성을 데이터베이스에 위임한다.
     * SEQUENCE 전략 : 데이터베이스 시퀀스를 사용해서 기본 키를 할당한다.
     * TABLE 전략 : 키 생성 테이블을 사용한다.(ex : 시퀀스용 테이블을 생성해서 테이블의 기본 키를 저장하고 관리한다.)
     */
    private Long id;

    private String name;

    private String email;

    private String phoneNo;

    public Student(){

    }
    public Student(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
