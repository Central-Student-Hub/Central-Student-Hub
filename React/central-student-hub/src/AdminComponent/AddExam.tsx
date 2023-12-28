import React, { useState } from 'react';
import './AddExam.css';

interface ExamRequest {
  semCourseId: number;
  date: string;
  fromTime: number;
  period: number;
}

const AddExam: React.FC = () => {
  const [examRequests, setExamRequests] = useState<ExamRequest[]>([
    { semCourseId: 1, date: '2023-01-01', fromTime: 9.0, period: 2.0 },
    { semCourseId: 2, date: '2023-01-02', fromTime: 10.0, period: 1.5 },
  ]);

  const [isEdited, setIsEdited] = useState(true);

  /*
  useEffect(() => {
    // API
    setExamRequests();
  }, []);
  */

  const handleEdit = (index: number, field: string, value: string | number) => {
    const updatedRequests = [...examRequests];
    updatedRequests[index] = { ...updatedRequests[index], [field]: value };
    setExamRequests(updatedRequests);
    setIsEdited(true); 
  };

  const handleSubmitAll = () => {
    console.log("Submitting all changes: ", examRequests);
    //API
    setIsEdited(false); 
  };

  return (
    <div className="exam-request-container">
      <div className="header">
        <h1  className='exam-title'>Exams</h1>
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
              <td>{examRequest.semCourseId}</td>
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
