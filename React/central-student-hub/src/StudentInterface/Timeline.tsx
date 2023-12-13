import React, { useState } from 'react';

type Assignment = {
  assignmentName: string;
  courseName: string;
  dueDate: string;
  description: string;
  assignmentLink: string;
  answerLink: string;
  submissionDate?: string;
};

const Timeline: React.FC = () => {
  // Mock data to simulate backend response
  const mockAssignments: Assignment[] = [
    {
      assignmentName: 'Assignment 1',
      courseName: 'Course 101',
      dueDate: '2023-12-20',
      description: 'Description for Assignment 1',
      assignmentLink: 'http://example.com/assignment1',
      answerLink: '',
      submissionDate: '2023-12-05'
    },
    {
      assignmentName: 'Assignment 2',
      courseName: 'Course 102',
      dueDate: '2023-12-25',
      description: 'Description for Assignment 2',
      assignmentLink: 'http://example.com/assignment2',
      answerLink: '',
      submissionDate: '2023-12-10'
    },
    {
      assignmentName: 'Assignment 3',
      courseName: 'Course 103',
      dueDate: '2023-12-28',
      description: 'Description for Assignment 3',
      assignmentLink: 'http://example.com/assignment3',
      answerLink: '',
      submissionDate: '2023-12-15'
    },
    {
      assignmentName: 'Assignment 4',
      courseName: 'Course 104',
      dueDate: '2023-12-30',
      description: 'Description for Assignment 4',
      assignmentLink: 'http://example.com/assignment4',
      answerLink: '',
      submissionDate: '2023-12-20'
    },
    {
      assignmentName: 'Assignment 5',
      courseName: 'Course 105',
      dueDate: '2024-01-05',
      description: 'Description for Assignment 5',
      assignmentLink: 'http://example.com/assignment5',
      answerLink: '',
      submissionDate: '2023-12-25'
    },
    // ... (if more assignments are needed)
  ];


  const [assignments, setAssignments] = useState<Assignment[]>(mockAssignments);
  const [showActiveOnly, setShowActiveOnly] = useState<boolean>(false);

  // TODO: Fetch assignments from backend
  // useEffect(() => {
  //   fetch('/api/assignments').then(response => response.json()).then(data => setAssignments(data));
  // }, []);

  const handleToggleActive = () => {
    setShowActiveOnly(!showActiveOnly);
    // TODO: Fetch active assignments from backend if required
  };

  const handleSubmit = (assignment: Assignment, answerLink: string) => {
    // TODO: Send submitted assignment to backend
    console.log(`Submitting assignment: ${assignment.assignmentName} with link: ${answerLink}`);
  };

  return (
    <div className="p-10 ml-20">
      <div className='ml-20'>
      <button
        onClick={handleToggleActive}
        className="bg-green-600 hover:bg-green-900 text-white font-bold py-2 px-4 rounded absolute top-0 right-0 m-5"
      >
        {showActiveOnly ? 'All Assignments' : 'Active Assignments'}
      </button>
      {assignments.map((assignment, index) => (
        <div
          key={index}
          className="bg-white p-4 my-4 rounded shadow-lg"
          style={{ width: 'calc(100% - 530px)' }} // Adjusted
        >
          <h3 className="font-bold text-xl">{assignment.assignmentName}</h3>
          <p className="text-base">{assignment.courseName}</p>
          <p className="text-base">Due: {assignment.dueDate}</p>
          <p className="text-base">{assignment.description}</p>
          <a href={assignment.assignmentLink} target="_blank" rel="noopener noreferrer"
            className="underline text-blue-500">
              View Assignment
          </a>
          <div className="flex items-center">
            <input
              type="text"
              placeholder="Answer link"
              className="border-2 border-gray-300 rounded py-2 px-4 mr-2 my-2 w-72"
              onChange={(e) => (assignment.answerLink = e.target.value)}
            />
            <button
              onClick={() => handleSubmit(assignment, assignment.answerLink)}
              className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded ml-10"
            >
              Submit
            </button>
          </div>
        </div>
      ))}
      </div>
    </div>
  );

};

export default Timeline;
