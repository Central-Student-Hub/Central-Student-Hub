package com.centralstudenthub.service;

import com.centralstudenthub.entity.student_profile.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.centralstudenthub.repository.CourseRepository;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    CourseRepository courseRepository;

    public int levenshteinDistance(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < memo.length; i++) {
            memo[i][0] = i;
        }

        for (int i = 0; i < memo[0].length; i++) {
            memo[0][i] = i;
        }

        for (int i = 1; i < memo.length; i++) {
            for (int j = 1; j < memo[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1];
                } else {
                    memo[i][j] = 1 + Math.min(memo[i][j - 1], Math.min(memo[i - 1][j], memo[i - 1][j - 1]));
                }
            }
        }

        return memo[s1.length()][s2.length()];
    }

    public int courseLevenshteinDistance(Course course, String searchKey) {
        searchKey = searchKey.toLowerCase();
        int min = levenshteinDistance(searchKey, course.getName());
        min = Integer.min(min, levenshteinDistance(searchKey, course.getCode()));

        String[] descriptionTokens = course.getDescription().split("[.,;]");
        for (String token : descriptionTokens) {
            token = token.toLowerCase();
            token = token.strip();
            min = Integer.min(min, levenshteinDistance(searchKey, token));
        }

        return min;
    }

    public List<Course> filterCourses(String searchKey) {
        if (searchKey == null || searchKey.isEmpty()) {
            return null;
        }

        List<Course> allCourses = courseRepository.findAll();
        allCourses.sort((a, b) -> courseLevenshteinDistance(a, searchKey) - courseLevenshteinDistance(b, searchKey));
        return allCourses;
    }
}
