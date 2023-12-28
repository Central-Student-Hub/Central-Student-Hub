import React, { useEffect, useState } from 'react';
import './AddExam.css';
import { AdminApi } from "../Services/AdminApi.ts";
import { SemesterCourseResponse } from "../Models/SemesterCourseResponse";

export interface ExamRequest {
  semCourseId: number;
  date: string;
  fromTime: number;
  period: number;
}

const AddExam: React.FC = () => {
  const [examRequests, setExamRequests] = useState<ExamRequest[]>([]);
  const [isEdited, setIsEdited] = useState(true);
  const [allSemesterCourses, setAllSemesterCourses] = useState<SemesterCourseResponse[]>([]);
  const api = new AdminApi();

  useEffect(() => {
    const getSemesterCourses = async () => await api.getAllSemesterCourses();

    getSemesterCourses()
      .then((semesterCourses) => {
        setAllSemesterCourses(semesterCourses);
        setExamRequests(semesterCourses.map((semesterCourse) => {
          return {
            semCourseId: semesterCourse.semCourseId,
            date: '',
            fromTime: 0,
            period: 0,
          };
        }))
      })
      .catch((err) => console.log(err));
  }, []);

  const handleSubmitAll = () => {
    console.log("Submitting all changes: ", examRequests);
    setIsEdited(false);

    examRequests.forEach(async (exam) => await api.distributeExams(exam));
  };

  const handleEdit = (index: number, field: string, value: string | number) => {
    const updatedRequests = [...examRequests];
    updatedRequests[index] = { ...updatedRequests[index], [field]: value };
    setExamRequests(updatedRequests);
    setIsEdited(true);
  };

  return (
    <div className="exam-request-container">
      <div className="header">
        <h1 className='exam-title'>Exams</h1>
        <button onClick={handleSubmitAll} disabled={!isEdited}>
          Submit
        </button>
      </div>
      <table className="exam-request-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Date</th>
            <th>From Time</th>
            <th>Period</th>
          </tr>
        </thead>
        <tbody>
          {examRequests.map((examRequest, index) => (
            <tr key={examRequest.semCourseId}>
              <td>{allSemesterCourses[index].code} - {allSemesterCourses[index].name}</td>
              <td>
                <input
                  type="date"
                  value={examRequest.date}
                  onChange={(e) => handleEdit(index, 'date', e.target.value)}
                />
              </td>
              <td>
                <input
                  type="number"
                  value={examRequest.fromTime}
                  onChange={(e) => handleEdit(index, 'fromTime', parseFloat(e.target.value))}
                />
              </td>
              <td>
                <input
                  type="number"
                  value={examRequest.period}
                  onChange={(e) => handleEdit(index, 'period', parseFloat(e.target.value))}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AddExam;
