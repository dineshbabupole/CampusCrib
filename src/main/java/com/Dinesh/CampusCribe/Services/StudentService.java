package com.Dinesh.CampusCribe.Services;

import com.Dinesh.CampusCribe.Exceptions.UserNotFoundException;
import com.Dinesh.CampusCribe.Reposiories.StudentRepo;
import com.Dinesh.CampusCribe.models.Owner;
import com.Dinesh.CampusCribe.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student addStudent(Student student){
        return studentRepo.save(student);
    }
    public Student getStudentByMail(String mail){
        return studentRepo.findByMail(mail);
    }

    public Student updateStudent(Student student,Long id){
        Student presStudent=studentRepo.findById(id).orElseThrow(()->new UserNotFoundException("student not found"));

        presStudent.setCollegeName(student.getCollegeName());
        presStudent.setFullName(student.getFullName());
        return studentRepo.save(presStudent);
    }
    public String dltStudent(String mail){
        try {
            studentRepo.delete(studentRepo.findByMail(mail));
            return "successfully deleted";
        }
        catch (Exception e){
            return "deleted fail";
        }

    }


}
