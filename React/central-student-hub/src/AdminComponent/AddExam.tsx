import { useEffect, useState } from "react";
import './AddExam.css';


enum Semester {
    FALL = 'FALL',
    SPRING = 'SPRING',
    SUMMER = 'SUMMER'
}

interface SemesterCourse {
    semCourseId: number;
    code: string;
    name: string;
    description: string;
    creditHours: number;
    semester: Semester;
    maxSeats: number;
}

interface CourseExam {
    courseId: number;
    date: string;
    period: number; 
}


const mockCourses: SemesterCourse[] = [
    {
      semCourseId: 1,
      code: 'CSE101',
      name: 'Introduction to Computer Science',
      description: 'An introductory course to computer science concepts.',
      creditHours: 3,
      semester: Semester.FALL,
      maxSeats: 50,
    },
  ];
  
  const mockExams: CourseExam[] = [
    {
      courseId: 1,
      date: '2023-05-15', 
      period: 1.5,
    },
  ];
  
const CourseList: React.FC = () => {
    const [courses, setCourses] = useState<SemesterCourse[]>(mockCourses);
    const [exams, setExams] = useState<CourseExam[]>(mockExams);
     const [isSubmitted, setIsSubmitted] = useState(false);
    const [submissionMessage, setSubmissionMessage] = useState('');

    const handleSubmit = () => {
        setIsSubmitted(true);
        setSubmissionMessage('Submission successful!'); 
        // Simulate backend submission, remove or replace this with actual API call
    };

    useEffect(() => {
        // Fetch courses from backend
        fetch('your-api-endpoint')
            .then(response => response.json())
            .then(data => setCourses(data));
    }, []);

    const handleDateChange = (courseId: number, date: string) => {
        const updatedExams = exams.map(exam => 
            exam.courseId === courseId ? { ...exam, date } : exam
        );
        setExams(updatedExams);
        setIsSubmitted(false); 
    };
    
    const handlePeriodChange = (courseId: number, period: number) => {
        const updatedExams = exams.map(exam => 
            exam.courseId === courseId ? { ...exam, period } : exam
        );
        setExams(updatedExams);
        setIsSubmitted(false); 
    };
    

    return (
        <div className="container">
            <div className="header">
                <h2>Courses</h2>
                <button 
                    className={`button ${isSubmitted ? 'button-disabled' : ''}`} 
                    onClick={handleSubmit} 
                    disabled={isSubmitted}
                >
                    Submit
                </button>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th className="th">Name</th>
                        <th className="th">Semester</th>
                        <th className="th">Exam Date</th>
                        <th className="th">Exam Period</th>
                    </tr>
                </thead>
                <tbody>
                    {courses.map(course => (
                        <tr key={course.semCourseId}>
                            <td className="td">{course.name}</td>
                            <td className="td">{course.semester}</td>
                            <td className="td">
                                <input 
                                    className="input"
                                    type="date" 
                                    value={exams.find(exam => exam.courseId === course.semCourseId)?.date || ''}
                                    onChange={(e) => handleDateChange(course.semCourseId, e.target.value)} 
                                />
                            </td>
                            <td className="td">
                                <input 
                                    className="input"
                                    type="number" 
                                    step="0.1"
                                    value={exams.find(exam => exam.courseId === course.semCourseId)?.period || 0}
                                    onChange={(e) => handlePeriodChange(course.semCourseId, parseFloat(e.target.value))} 
                                />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {isSubmitted && <p>{submissionMessage}</p>}
        </div>
    );        
};

export default CourseList;
