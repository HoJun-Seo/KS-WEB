package com.HoJun.app.controller;

import com.HoJun.app.Repository.StudentRepository;
import com.HoJun.app.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//Controller : Controller 는 Model 을 이용해 데이터를 가져오고, view 에 데이터를 넘겨 적절한 view 를 생성하는 역할을 한다.
@Controller //@Controller Annotation 을 붙이면 해당 클래스를 웹 요청을 처리하는 컨트롤러로 사용하게 된다.(MVC pattern - Controller)
public class IndexController {

    private final StudentRepository studentRepository; //CRUD 기능 사용을 위한 interface 객체 생성
    //StudentRepository interface 는 CrudRepository class 를 상속받음

    public IndexController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    //생성자 생성

    @GetMapping("/") // HTTP Get 요청을 특정 핸들러 메소드에 매핑 하기위한 annotation, 주소에 파라미터가 노출된다.(GET - 리소스 조회)
    public String index(Model model){
        //Iterable : Iterator 를 제공하는 메소드를 보유하고 있는 인터페이스, 실질적으로 for-each를 사용할 수 있는 클래스라는것을 명세해주는 기능을 제공하고
        //Iterable 을 상속받은 클래스는 Iterator 를 사용하여 for-each 기능을 사용할 수 있다.
        Iterable<Student> students = studentRepository.findAll();
        //Iterable<T> findAll()  : CrudRepository class 에서 지원하는 메소드, 전체 레코드(모든 Entity 목록) 불러오기, 정렬(sort), 페이징(pageable) 가능
        model.addAttribute("students", students); //students 객체를 "students" 이름으로 추가해준다, 뷰 코드(html) 에서는 "students" 으로 지정한 이름을 통해서 students 객체를 사용한다.
        //여기서 students 객체는 CrudRepository class 의 findAll method 를 이용해 전체 레코드를 불러온 객체를 말한다.
        /*
         * 모델은 mvc pattern 에서 애플리케이션의 정보(데이터) 를 나타낸다.
         * Model 객체의 addAttribute method 를 이용하여 모델을 뷰(html) 로 전달해준다.
         */
        return "index"; //뷰(html) 에서 사용하기 위해 객체 직렬화
        // Controller 를 작성한 뒤 view file 의 이름과 같은 문자열을 return 하게 되면
        // classpath 에 존재하는 view 를 찾아 그것을 사용자에게 응답하게 된다.
    }
    @GetMapping("/add") // /add 라는 URI 에 매핑되도록 설정
    public String showAddForm(Student student) {
        return "add-student";
    }
    @PostMapping("/add") //HTTP Post 요청을 특정 핸들러 메소드에 매핑 하기위한 annotation(POST - 리소스 생성). /add 라는 URI 에 매핑되도록 설정된다.
    public String addStudent(Student student) {
        studentRepository.save(student); //student Entity 의 등록과 수정 기능 제공
        return "redirect:/";
        // return "redirect:/" - /주소에 해당하는 페이지로 이동하라(redirect)
    }
    @GetMapping("/update/{id}") // /update/{id} 라는 URI 에 매핑되도록 설정
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invaild student Id : " + id));
        //findByID(Iterable<ID> ids) : 해당 식별키를 가진 Entity 목록을 반환한다.
        //orElseThrow(() -> new IllegalArgumentException("Invaild student Id : " + id));
        //orElseThrow() : 찾고자 하는 데이터가 존재하지 않는 경우 익셉션을 리턴해주는 메소드.
        //IlleagalArgumentException() : 부정한 인수, 또는 부적절한 인수를 메소드에 건네준 것을 나타내기 위해서 발생하는 익셉션
        model.addAttribute("student", student);
        return "update-student";
    }
    @PostMapping("/update/{id}")
    public String updateStudent(Student student) {
        studentRepository.save(student);
        return "redirect:/";
    }
    @GetMapping("delete/{id}") // delete/{id} 라는 URI 에 매핑되도록 설정
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        // @PathVariable 은 메소드 매개변수가 URI 템플릿 변수에 바인딩 되어야 함을 나타내는 spring annotation 이다.(URI의 일부를 변수로 전달한다.)
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id : " + id));
        studentRepository.delete(student); //findById method 를 통해 찾은 student Entity 삭제
        model.addAttribute("students", studentRepository.findAll());
        return "redirect:/";
    }
}