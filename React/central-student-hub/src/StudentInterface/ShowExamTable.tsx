import React, { useEffect, useState } from 'react';
import './ShowExamTable.css';

interface ExamResponse {
  courseName: string;
  building: number;
  seatNumber: number;
  room: number;
  date: string; 
  fromTime: number;
  period: number;
}


const mockData: ExamResponse[] = [
  {
    courseName: 'Introduction to Biology',
    building: 1,
    seatNumber: 24,
    room: 101,
    date: '2023-05-12',
    fromTime: 9.5,
    period: 2, 
  },
  {
    courseName: 'Fundamentals of Physics',
    building: 2,
    seatNumber: 30,
    room: 201,
    date: '2023-05-13',
    fromTime: 14, // 2:00 pm
    period: 1.5,
  },
];

const ShowExamTable: React.FC = () => {
  const [examResponses, setExamResponses] = useState<ExamResponse[]>([]);

  useEffect(() => {
    //api
    setExamResponses(mockData);
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
              <td>{examResponse.date}</td>
              <td>{formatTime(examResponse.fromTime)}</td>
              <td>{examResponse.period}</td>
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
