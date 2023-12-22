import React, { useState, useEffect } from 'react';
import { ApiRequester } from '../Services/ApiRequester.ts';

// Enum for semesters
enum Semester {
  Fall = "Fall",
  Summer = "Summer",
  Spring = "Spring"
}

// Type definition for course
export type CourseType = {
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
  const apiRequester = new ApiRequester();

  // Fetch courses from the backend
  useEffect(() => {
    const getCourses = async () => await apiRequester.retrieveSemesterCourses();
    getCourses()
      .then((courses) => setCourses(courses))
      .catch((error) => console.error(error));
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