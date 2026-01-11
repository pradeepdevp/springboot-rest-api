package com.example.springboot.controller;

import com.example.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("students")
public class StudentController {

    //http://localhost/student
    @GetMapping("/student")
    public Student getStudent (){
        Student student = new Student(
        1,"Ramesh","Kumar");
        return student;
    }
    //Usage of ResponseEntity class
    //http://localhost:8080/students/studentUsingResponseEntity
    @GetMapping("/studentUsingResponseEntity")
    public ResponseEntity<Student> getStudentUsingResponseEntity (){
        Student student = new Student(
                2,"Pradeep","Kumar");
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);//above line is same as this line.

        //below for returning custom header
        return ResponseEntity.ok()
                .header("Custom-Header", "Lakshit")
                .body(student);

    }

    @GetMapping("/students")
    public ArrayList<Object> getStudents(){
        ArrayList<Object> students = new ArrayList<>();
        students.add (new Student(1, "Pradeep", "Kumar"));
        students.add (new Student(2, "Lakshit", "Lakshit"));
        students.add (new Student(3, "Archana", "Kumari"));

        return students;
    }

//    //Same GET method but using ResponseEntity
//    @GetMapping("/students")
//    public ResponseEntity<ArrayList<Object>> getStudents(){
//        ArrayList<Object> students = new ArrayList<>();
//        students.add (new Student(1, "Pradeep", "Kumar"));
//        students.add (new Student(2, "Lakshit", "Lakshit"));
//        students.add (new Student(3, "Archana", "Kumari"));
//
//        return ResponseEntity.ok(students);
//    }

    //http://localhost:8080/students/1
    @GetMapping("/students/{id}/{first-name}")
    public Student getSudentWithPathVariable(@PathVariable int id,
                                             @PathVariable ("first-name") String firstName){
        return new Student(id,firstName, "Kumar");
    }

//    //http://localhost:8080/students/1
//    @GetMapping("/students/{id}/{first-name}")
//    public ResponseEntity<Student> getSudentWithPathVariable(@PathVariable int id,
//                                             @PathVariable ("first-name") String firstName){
//        Student student = new Student(id,firstName, "Kumar");
//        return ResponseEntity.ok(student);
//    }

//    //http://localhost:8080/students/query?id=2
//    @GetMapping("/students/query")
//    public Student getSudentWithRequestParam(@RequestParam int id){
//        return new Student(id,"Lakshit", "Lakshit");
//    }

    //http://localhost:8080/students/query?id=2&firstName=Pradeep&lastName=Kumar
    @GetMapping("students/query")
    public Student getSudentWithMultipleRequestParam(@RequestParam int id,
                                             @RequestParam String firstName,
                                             @RequestParam String lastName){
        return new Student(id,firstName, lastName);
    }

    //http://localhost:8080/students/
    @PostMapping("students/createStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent (@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;
    }

//    //http://localhost:8080/students/
//    @PostMapping("students/createStudent")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Student> createStudent (@RequestBody Student student){
//        System.out.println(student.getId());
//        System.out.println(student.getFirstName());
//        System.out.println(student.getLastName());
//
//        return new ResponseEntity<>(student, HttpStatus.CREATED);
//    }

    //http://localhost:8080/students/1/update
    //Spring boot rest Api that handles PUT requst
    @PutMapping("students/{id}/update")
    public Student updateStudent (@RequestBody Student student,
                                  @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //http://localhost:8080/students/1/delete
    //Spring boot rest Api that handles DELETE requst
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent (@PathVariable("id") int studentId){
        return "Student with Id: "+ studentId + " deleted sucessfully! ";
    }

}
