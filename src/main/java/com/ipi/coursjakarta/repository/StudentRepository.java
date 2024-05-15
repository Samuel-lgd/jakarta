package com.ipi.coursjakarta.repository;

import com.ipi.coursjakarta.beans.StudentBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public List<StudentBean> data = new ArrayList<>();

    public void init(){
        StudentBean friend1 = new StudentBean();
        friend1.setAge(21);
        friend1.setFirstName("John");
        friend1.setLastName("Doe");
        data.add(friend1);

        StudentBean friend2 = new StudentBean();
        friend2.setAge(22);
        friend2.setFirstName("Jane");
        friend2.setLastName("Doe");
        data.add(friend2);

        StudentBean friend3 = new StudentBean();
        friend3.setAge(23);
        friend3.setFirstName("Jack");
        friend3.setLastName("Doe");
        data.add(friend3);
    }
}
