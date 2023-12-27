import React, { useState, useEffect } from "react";
import './ShowExamTable.css'; 


interface Location {
    building: number;
    room: number;
    capacity: number;
}

interface CourseExam {
    courseId: number;
    date: string;
    period: number;
    location: Location; 
}


  

const ShowExamTable: React.FC = () => {
    const [exams, setExams] = useState<CourseExam[]>([]);

    useEffect(() => {
        // Fetch the exams data or use mock data
        const mockExams: CourseExam[] = [
            {
              courseId: 1,
              date: '2023-05-15',
              period: 1.5,
              location: {
                building: 101,
                room: 202,
                capacity: 50
              }
            },
            {
              courseId: 2,
              date: '2023-05-20',
              period: 2,
              location: {
                building: 102,
                room: 203,
                capacity: 60
              }
            },
          ];
        setExams(mockExams);
    }, []);

    return (
        <div className="course-details-container">
            <h2>Exam TimeTable</h2>
            <table className="course-details-table">
                <thead>
                    <tr>
                        <th>Course ID</th>
                        <th>Date</th>
                        <th>Period</th>
                        <th>Building</th>
                        <th>Room</th>
                    </tr>
                </thead>
                <tbody>
                    {exams.map((exam) => (
                        <tr key={exam.courseId}>
                            <td>{exam.courseId}</td>
                            <td>{exam.date}</td>
                            <td>{exam.period}</td>
                            <td>{exam.location.building}</td>
                            <td>{exam.location.room}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
    
};

export default ShowExamTable;
