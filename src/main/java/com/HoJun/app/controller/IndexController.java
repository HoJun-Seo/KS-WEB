package com.HoJun.app.controller;

import com.HoJun.app.Repository.StudentRepository;
import com.HoJun.app.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final StudentRepository studentRepository;

    public IndexController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("Students", students);
        return "index";
    }
}