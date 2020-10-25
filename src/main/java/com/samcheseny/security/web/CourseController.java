package com.samcheseny.security.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private static final Map<Integer, String> COURSES = new HashMap<>();

    static {
        COURSES.put(1, "IT");
        COURSES.put(2, "Computer Science");
        COURSES.put(3, "Engineering");
        COURSES.put(4, "Medicine");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('course_read')")
    public Map<Integer, String> getCourses() {
        return COURSES;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('course_read')")
    public String getOneCourse(@NotNull @PathVariable("id") int id) {
        return COURSES.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addCourse() {
        System.out.println("Added a course");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCourse(@NotNull @PathVariable("id") int id) {
        System.out.println("Updated a course");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCourse(@NotNull @PathVariable("id") int id) {
        return ResponseEntity.noContent().build();
    }
}
