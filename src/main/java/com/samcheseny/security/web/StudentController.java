package com.samcheseny.security.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final Map<Integer, String> STUDENTS = new HashMap<>();

    static {
        STUDENTS.put(1, "Samuel Ndara");
        STUDENTS.put(2, "James Bond");
        STUDENTS.put(3, "Karl Marx");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('student_read')")
    public Map<Integer, String> getStudents() {
        return STUDENTS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('student_read')")
    public String getOneStudent(@NotNull @PathVariable("id") int id) {
        return STUDENTS.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addStudent() {
        System.out.println("Added a student");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public void updateStudent(@NotNull @PathVariable("id") int id) {
        System.out.println("Updated a student");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteStudent(@NotNull @PathVariable("id") int id) {
        return ResponseEntity.noContent().build();
    }

}
