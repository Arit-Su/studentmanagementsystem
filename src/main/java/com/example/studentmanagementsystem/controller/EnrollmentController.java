package com.example.studentmanagementsystem.controller;


import com.example.studentmanagementsystem.entity.Course;
import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.repository.CourseRepository;
import com.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/students/{studentId}/courses/{courseId}")
    public Student enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            student.getEnrolledCourses().add(course);
            return studentRepository.save(student);
        }
        return null;
    }

    @GetMapping("/students/{studentId}/courses")
    public Set<Course> getEnrolledCoursesForStudent(@PathVariable Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return (student != null) ? student.getEnrolledCourses() : null;
    }
}