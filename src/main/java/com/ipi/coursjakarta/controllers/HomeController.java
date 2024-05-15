package com.ipi.coursjakarta.controllers;

import com.ipi.coursjakarta.beans.StudentBean;
import com.ipi.coursjakarta.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    StudentRepository studentRepository = new StudentRepository();

    public HomeController(){
        studentRepository.init();
    }

    @GetMapping("/")
    public String home(Model model){
        StudentBean student = new StudentBean();
        student.setAge(20);
        student.setFirstName("Samuel");
        student.setLastName("Lagarde");

        model.addAttribute("student", student);
        model.addAttribute("friends", studentRepository.data);

        return "welcome";
    }

    @GetMapping("/add")
    public String addStudent(Model model,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "age", required = false) Integer age){

        if(firstName != null && lastName != null && age != null){
            StudentBean student = new StudentBean();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setAge(age);

            studentRepository.data.add(student);
        }

        model.addAttribute("friends", studentRepository.data);

        return "welcome";
    }

    @GetMapping("/filter")
    public String filterStudent(Model model,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName){

        StudentBean parameters = new StudentBean();
        parameters.setAge(age);
        parameters.setFirstName(firstName);
        parameters.setLastName(lastName);

        List<StudentBean> data = studentRepository.data;

        if (age != null) {
            data = data.stream().filter(student -> age.equals(student.getAge())).toList();
        }
        if (firstName != null) {
            data = data.stream().filter(student -> firstName.equals(student.getFirstName())).toList();
        }
        if (lastName != null) {
            data = data.stream().filter(student -> lastName.equals(student.getLastName())).toList();
        }

        model.addAttribute("params", parameters);
        model.addAttribute("students", data);
        return "recherche";
    }


    @GetMapping("/form")
    public String form(StudentBean student) {
        return "studentForm";
    }

    @PostMapping("/formSubmit")
    public String formSubmit(StudentBean student, RedirectAttributes redirectAttributes) {

        try {
            if (student.getFirstName().trim().isEmpty() || student.getLastName().trim().isEmpty() || student.getAge() == null) {
                throw new Exception("All fields are required");
            }
            studentRepository.data.add(student);
            return "redirect:/";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("studentBean", student);
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/form";
        }

    }


}
