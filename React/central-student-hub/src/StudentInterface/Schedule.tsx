import React, { useState, useEffect } from 'react';
import './Schedule.css';

type SessionType = string;
type Location = string; 

type ScheduleEntry = {
    courseName: string,
    period: number,
    weekday: string,
    sessionType: SessionType,
    teacherName: string,
    location: Location
};

const Schedule: React.FC = () => {
  const [schedule, setSchedule] = useState<ScheduleEntry[]>([]);

  const mockScheduleData = [
    {
      courseName: 'Mathematics 101',
      period: 1,
      weekday: 'Monday',
      sessionType: 'Lecture',
      teacherName: 'John Doe',
      location: 'Room 101'
    },
    {
      courseName: 'Physics 201',
      period: 2,
      weekday: 'Tuesday',
      sessionType: 'Lab',
      teacherName: 'Jane Smith',
      location: 'Lab 202'
    },
  ];

  useEffect(() => {
    setSchedule(mockScheduleData);
  }, []);

  return (
  <div className="exam-table-container">
    <h2 className="exam-title">Schedule</h2>
    <div className="schedule-table">
      <div className="schedule-header">
        <div>Course Name</div>
        <div>Period</div>
        <div>Weekday</div>
        <div>Session Type</div>
        <div>Teacher Name</div>
        <div>Location</div>
      </div>
      {schedule.map((entry, index) => (
        <div className="schedule-row" key={index}>
          <div>{entry.courseName}</div>
          <div>{entry.period}</div>
          <div>{entry.weekday}</div>
          <div>{entry.sessionType}</div>
          <div>{entry.teacherName}</div>
          <div>{entry.location}</div>
        </div>
      ))}
    </div>
    </div>
  );
};

export default Schedule;
