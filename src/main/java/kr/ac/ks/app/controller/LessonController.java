package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Course;
import kr.ac.ks.app.domain.Lesson;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.CourseRepository;
import kr.ac.ks.app.repository.LessonRepository;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LessonController {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public LessonController(LessonRepository lessonRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/lessons/new")
    public String createForm(Model model) {
        model.addAttribute("lessonForm", new LessonForm());
        return "lessons/lessonForm";
    }

    @PostMapping(value = "/lessons/new")
    public String create(LessonForm form) {
        Lesson lesson = new Lesson();
        lesson.setName(form.getName());
        lesson.setQuota(form.getQuota());
        lessonRepository.save(lesson);
        return "redirect:/lessons";
    }
    //강의 정보 수정(강의 id 값 이용)
    @GetMapping("/lessons/update/{id}")
    public String showUpdateLessonForm(@PathVariable("id") Long id, Model model){
        Lesson lesson = lessonRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id:" + id));
        model.addAttribute("lessonUpdateForm", lesson);
        return "lessons/lessonUpdateForm";
    }

    @PostMapping("/lessons/update/{id}")
    public String updateLesson(@PathVariable("id") Long id, @Valid LessonUpdateForm lessonUpdateForm, BindingResult result){
        if (result.hasErrors()){
            return "lessons/lessonUpdateForm";
        }
        Lesson lesson = lessonRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id:" + id));
        lesson.setName(lessonUpdateForm.getName());
        lesson.setQuota(lessonUpdateForm.getQuota());
        lessonRepository.save(lesson);
        return "redirect:/lessons";
    }

    //강의 정보 삭제(강의 id 값 이용)
    @GetMapping("/lessons/delete/{id}")
    public String deleteLesson(@PathVariable("id") Long id, Model model){
        Lesson lesson = lessonRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id:" + id));
        if (!lesson.getCourses().isEmpty()) return "redirect:/lessons"; // 학생이 수강 신청한 강의일 경우 삭제 불가능
        lessonRepository.delete(lesson);
        model.addAttribute("lessons", lessonRepository.findAll());
        return "redirect:/lessons";
    }

    @GetMapping(value = "/lessons")
    public String list(Model model) {
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return "lessons/lessonList";
    }
}
