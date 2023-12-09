package com.centralstudenthub.service;

import com.centralstudenthub.entity.Course;
import com.centralstudenthub.repository.CoursesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SearchServiceTest {

    @Autowired
    SearchService searchService;

    @MockBean
    CoursesRepository coursesRepository;

    static Course softwareEngineering = Course.builder()
            .courseId(1)
            .name("Software Engineering")
            .code("CSE 327")
            .creditHours(3)
            .description("Concepts of Software Development. Life-Cycle of Software. Requirements and Specification. Data Model. Process Model. Design and Coding. Verification, Validation and Testing. Management of Software Projects. Software Evolution.")
            .build();

    static Course systemsProgramming = Course.builder()
            .courseId(2)
            .name("Systems Programming")
            .code("CSE 266")
            .creditHours(3)
            .description("Programming in Assembly Language, Assemblers, Macro processors, Linkers and Loaders. Introduction to compilers.")
            .build();

    static Course operatingSystems = Course.builder()
            .courseId(3)
            .name("Operating Systems")
            .code("CSE 366")
            .creditHours(3)
            .description("Overview. Concurrency, implementation structures: dispatching and context switching. Mutual exclusion: deadlock; synchronization. Scheduling, .Memory management. Device management .File systems. Security and protection.")
            .build();

    static List<Course> allCourses = new ArrayList<>();

    @BeforeAll
    static void addCourses() {
        allCourses.add(softwareEngineering);
        allCourses.add(systemsProgramming);
        allCourses.add(operatingSystems);
    }

    @BeforeEach
    void mockCourses() {
        Mockito.when(coursesRepository.findAll()).thenReturn(allCourses);
    }

    @Test
    void testLevenshteinDistance1() {
        String s1 = "Hello!";
        String s2 = "Hello";

        int result = searchService.levenshteinDistance(s1, s2);
        assertEquals(result, 1);
    }

    @Test
    void testLevenshteinDistance2() {
        String s1 = "Hello!";
        String s2 = "Hllo";

        int result = searchService.levenshteinDistance(s1, s2);
        assertEquals(result, 2);
    }

    @Test
    void searchByCompleteCode() {
        String searchKey = "CSE 327";
        assertEquals(searchService.filterCourses(searchKey).get(0), softwareEngineering);
    }

    @Test
    void searchByIncompleteCode() {
        String searchKey = "CS 37";
        assertEquals(searchService.filterCourses(searchKey).get(0), softwareEngineering);
    }

    @Test
    void searchByCompleteName() {
        String searchKey = "Systems Programming";
        assertEquals(searchService.filterCourses(searchKey).get(0), systemsProgramming);
    }

    @Test
    void searchByIncompleteName() {
        String searchKey = "Systems Pr";
        assertEquals(searchService.filterCourses(searchKey).get(0), systemsProgramming);
    }

    @Test
    void searchByDescription() {
        String searchKey = "concurrency";
        assertEquals(searchService.filterCourses(searchKey).get(0), operatingSystems);
    }

}
