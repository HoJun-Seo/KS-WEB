package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Course;
import kr.ac.ks.app.domain.Lesson;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.CourseRepository;
import kr.ac.ks.app.repository.LessonRepository;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class CourseController{
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public CourseController(StudentRepository studentRepository, CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/course")
    public String showCourseForm(Model model) {
        List<Student> students = studentRepository.findAll();
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        return "courses/courseForm";
    }

    @PostMapping("/course")
    public String createCourse(@RequestParam("studentId") Long studentId,
                               @RequestParam("lessonId") Long lessonId
                               ) {
        Student student = studentRepository.findById(studentId).get();
        Lesson lesson = lessonRepository.findById(lessonId).get();
        if(lesson.getQuota() > 0) {
            lesson.setQuota(lesson.getQuota() - 1);
            Course course = Course.createCourse(student,lesson);
            courseRepository.save(course);
            return "redirect:/courses";
        }
        else return "redirect:/courses";
    }

    // 수강 신청 내역 수정(신청 내역 id값 기준)
    @GetMapping("/course/update/{id}")
    public String showCoruseUpdateForm(@PathVariable("id") Long courseId, Model model){
        Course course = courseRepository.findById(courseId).orElseThrow(()->new IllegalArgumentException("Invalid id:" + courseId));
        List<Student> students = studentRepository.findAll();
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("courseUpdateForm", course);
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        return "courses/courseUpdateForm";
    }

    @PostMapping("/course/update/{id}")
    public String courseUpdate(@PathVariable("id") Long courseId,
                               @RequestParam("studentId") Long studentId,
                               @RequestParam("lessonId") Long lessonId){
        Course course = courseRepository.findById(courseId).orElseThrow(()->new IllegalArgumentException("Invalid id:" + courseId));
        Student student = studentRepository.findById(studentId).get();
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lesson.setQuota(lesson.getQuota());
        course.updateCourse(student.getName(), lesson.getName(), course);
        courseRepository.save(course);
        return "redirect:/courses";
    }

    //수강 신청 내역 제거(신청 내역 id 값 기준)
    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, Model model){
        Course course = courseRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id:" + id));
        Student student = studentRepository.findByName(course.getStudent().getName());
        Lesson lesson = lessonRepository.findByName(course.getLesson().getName());
        student.getCourses().clear();
        lesson.getCourses().clear();
        courseRepository.delete(course);
        lesson.setQuota(lesson.getQuota() + 1);
        lessonRepository.save(lesson);
        model.addAttribute("courses", courseRepository.findAll());
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String courseList(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "courses/courseList";
    }
}




