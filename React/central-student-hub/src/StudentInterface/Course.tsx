import React, { useState, useEffect } from 'react';

// Enum for semesters
enum Semester {
  Fall = "Fall",
  Summer = "Summer",
  Spring = "Spring"
}

// Type definition for course
type CourseType = {
  semCourseId: number;
  courseId: number;
  code: string;
  name: string;
  description: string;
  creditHours: number;
  semester: Semester;
  maxSeats: number;
};

const Course: React.FC = () => {
  const [courses, setCourses] = useState<CourseType[]>([]);

  // Fetch courses from the backend
  useEffect(() => {
    // TODO: Replace with actual fetch request
    // Example:
    // fetch('/api/courses')
    //   .then(response => response.json())
    //   .then(data => setCourses(data));

    setCourses([
      {
        semCourseId: 1,
        courseId: 101,
        code: "CS101",
        name: "Introduction to Computer Science",
        description: "Basic concepts of computer science",
        creditHours: 3,
        semester: Semester.Fall,
        maxSeats: 30
      },
      {
        semCourseId: 2,
        courseId: 102,
        code: "ENG201",
        name: "English Literature",
        description: "Exploration of classic literature",
        creditHours: 3,
        semester: Semester.Spring,
        maxSeats: 25
      },
      {
        semCourseId: 3,
        courseId: 103,
        code: "MATH202",
        name: "Advanced Calculus",
        description: "Advanced topics in calculus",
        creditHours: 4,
        semester: Semester.Summer,
        maxSeats: 20
      },
      {
        semCourseId: 4,
        courseId: 104,
        code: "PHYS301",
        name: "Modern Physics",
        description: "Study of modern physics theories",
        creditHours: 3,
        semester: Semester.Fall,
        maxSeats: 30
      },
      {
        semCourseId: 5,
        courseId: 105,
        code: "CHEM205",
        name: "Organic Chemistry",
        description: "Fundamentals of organic chemistry",
        creditHours: 4,
        semester: Semester.Spring,
        maxSeats: 25
      },
      {
        semCourseId: 6,
        courseId: 106,
        code: "HIST110",
        name: "World History",
        description: "Survey of world history",
        creditHours: 3,
        semester: Semester.Summer,
        maxSeats: 20
      }
    ]);    
  }, []);

  return (
    <div className="p-5">
      <h1 className="text-2xl font-bold mb-4">Course Page</h1>
      <div className="flex flex-wrap">
        {courses.map(course => (
          <div key={course.semCourseId} className="bg-yellow-100 p-4 m-4 rounded shadow-lg" style={{ minWidth: "200px" }}>
            <h2 className="font-bold text-lg">{course.name}</h2>
            <p className="text-sm">Semester: {course.semester}</p>
            <p className="text-sm">ID: {course.semCourseId}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Course;
