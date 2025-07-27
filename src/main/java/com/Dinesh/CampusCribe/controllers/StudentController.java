package com.Dinesh.CampusCribe.controllers;

import com.Dinesh.CampusCribe.Services.StudentService;
import com.Dinesh.CampusCribe.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @GetMapping("/getStudent/{mail}")
    public Student getStudent(@PathVariable String mail){
        return studentService.getStudentByMail(mail);
    }

    @DeleteMapping("/dltStudent/{mail}")
    public String dltStudent(@PathVariable String mail){

        return studentService.dltStudent(mail);
    }

}
