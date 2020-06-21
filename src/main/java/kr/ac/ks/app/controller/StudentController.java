package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students/new")
    public String showStudentForm(Model model) {
        model.addAttribute("studentForm", new StudentForm());
        return "students/studentForm";
    }

    @PostMapping("/students/new")
    public String createStudent(@Valid StudentForm studentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "students/studentForm";
        }
        Student student = new Student();
        student.setName(studentForm.getName());
        student.setEmail(studentForm.getEmail());
        studentRepository.save(student);
        return "redirect:/students";
    }
    
    // 학생 정보 수정(학생 id 값 기준)
    @GetMapping("/students/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id:" + id));
        model.addAttribute("updateForm", student);
        return "students/updateForm";
    }
    
    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @Valid UpdateForm updateForm, BindingResult result){
        if (result.hasErrors()) {
            return "students/updateForm";
        }
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalud id:" + id));
        student.setName(updateForm.getName());
        student.setEmail(updateForm.getEmail());
        studentRepository.save(student);
        return "redirect:/students";
    }
    
    //학생 정보 삭제(학생 id 값 기준)
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model){
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id:" + id));
        if(!student.getCourses().isEmpty()) return "redirect:/students"; // 수강 신청한 학생일 경우 삭제 불가능
        studentRepository.delete(student);
        model.addAttribute("students", studentRepository.findAll());
        return "redirect:/students";
    }


    @GetMapping("/students")
    public String list(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "students/studentList";
    }


}
