package ir.test.student.controller;

import java.util.ArrayList;
import java.util.List;

import ir.test.student.model.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stu")
public class StudentController {

    List<Student> students = new ArrayList<>();

    @GetMapping
    public List<Student> students() {

        return students;
    }
    @PostMapping
    public Student save(@RequestBody Student studentRequest){
       students.add(studentRequest);
       return studentRequest;

    }
    @DeleteMapping("id")
    public void delete(@PathVariable int id){ students.remove(id); }

    @PutMapping("{id}")
    public Student update(@PathVariable("id") int id, @RequestBody Student student){
        int count = 0;
        Student student1 = new Student();
         for (Student stu : students){
             if (stu.getId() == id) {
                 student1 = stu;
                 count = students.indexOf(stu);
             }
         }
        if(student.getFirstname() != null) {
            student1.setFirstname(student.getFirstname());
        }  else if(student.getLastname() != null){
            student1.setLastname(student.getLastname());
        }
         students.set(count, student1);
         return student1;
    }
}
