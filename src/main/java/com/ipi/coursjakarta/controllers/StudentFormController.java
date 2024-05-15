package com.ipi.coursjakarta.controllers;

import com.ipi.coursjakarta.beans.StudentBean;
import com.ipi.coursjakarta.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentFormController {

    StudentRepository studentRepository = new StudentRepository();

    public StudentFormController() {
        studentRepository.init();
    }

}
