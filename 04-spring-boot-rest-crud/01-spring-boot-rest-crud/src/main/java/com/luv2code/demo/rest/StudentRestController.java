package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api") // base url path
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostContruct to load the student data... only once!
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Tyjae", "Spears"));
        theStudents.add(new Student("Cameron", "Ward"));
        theStudents.add(new Student("Calvin", "Ridley"));
    }

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    // define endpoint for "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // check the studentId against list size
        if ((studentId >= theStudents.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId); // goes and looks for @ExceptionHandler with a StudentNotFoundException type
        }
        return theStudents.get(studentId);
    }
}
