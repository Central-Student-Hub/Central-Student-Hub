import React, { useEffect, useState } from 'react';
import './ShowExamTable.css';
import { ExamsApi } from '../Services/ExamsApi.ts';

export interface ExamResponse {
  courseName: string;
  building: number;
  seatNumber: number;
  room: number;
  date: string; 
  fromTime: number;
  period: number;
}


const ShowExamTable: React.FC = () => {
  const [examResponses, setExamResponses] = useState<ExamResponse[]>([]);
  const api = new ExamsApi();

  useEffect(() => {
    const getExams = async () => await api.getExams();

    getExams()
      .then((exams) => setExamResponses(exams))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="exam-table-container">
      <h2 className="exam-title">Exam Schedule</h2>
      <table className="exam-table">
        <thead>
          <tr>
            <th>Course Name</th>
            <th>Building</th>
            <th>Seat Number</th>
            <th>Room</th>
            <th>Date</th>
            <th>From Time</th>
            <th>Period</th>
          </tr>
        </thead>
        <tbody>
          {examResponses.map((examResponse, index) => (
            <tr key={index}>
              <td>{examResponse.courseName}</td>
              <td>{examResponse.building}</td>
              <td>{examResponse.seatNumber}</td>
              <td>{examResponse.room}</td>
              <td>{examResponse.date.substring(0, 10)}</td>
              <td>{formatTime(examResponse.fromTime)}</td>
              <td>{examResponse.period}h</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

function formatTime(time: number): string {
  const hours = Math.floor(time);
  const minutes = (time - hours) * 60;
  return `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}`;
}

export default ShowExamTable;
