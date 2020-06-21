package kr.ac.ks.app.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LessonUpdateForm {
    private Long id;
    private String name;
    private int quota;
}
