import React, { useState } from 'react';

type Assignment = {
  assignmentName: string;
  courseName: string;
  dueDate: string;
  description: string;
  assignmentLinks: string[];
  answerLinks: string[];
  submissionDate?: string;
};

const Timeline: React.FC = () => {
  // Updated mock data with arrays for links
  const mockAssignments: Assignment[] = [
    {
      assignmentName: 'Assignment 1',
      courseName: 'Course 101',
      dueDate: '2023-12-20',
      description: 'Description for Assignment 1',
      assignmentLinks: ['http://example.com/assignment1','http://example.com/assignment1','http://example.com/assignment1','http://example.com/assignment1','http://example.com/assignment1','http://example.com/assignment1'],
      answerLinks: [''],
      submissionDate: '2023-12-05'
    }, {
      assignmentName: 'Assignment 2',
      courseName: 'Course 102',
      dueDate: '2023-12-22',
      description: 'Description for Assignment 2',
      assignmentLinks: ['http://example.com/assignment2', 'http://example.com/assignment2'],
      answerLinks: [''],
      submissionDate: '2023-12-06'
    },
    {
      assignmentName: 'Assignment 3',
      courseName: 'Course 103',
      dueDate: '2023-12-25',
      description: 'Description for Assignment 3',
      assignmentLinks: ['http://example.com/assignment3', 'http://example.com/assignment3'],
      answerLinks: [''],
      submissionDate: '2023-12-08'
    },
  ];

  const [assignments, setAssignments] = useState<Assignment[]>(mockAssignments);
  const [showActiveOnly, setShowActiveOnly] = useState<boolean>(false);

  const handleToggleActive = () => {
    setShowActiveOnly(!showActiveOnly);
  };

  const handleSubmit = (assignmentIndex: number) => {
    const assignment = assignments[assignmentIndex];
    console.log(`Submitting assignment: ${assignment.assignmentName} with links:`, assignment.answerLinks);
    // TODO: Send submitted assignment to backend
  };

  const handleAnswerLinkChange = (assignmentIndex: number, linkIndex: number, value: string) => {
    const updatedAssignments = [...assignments];
    updatedAssignments[assignmentIndex].answerLinks[linkIndex] = value;
    setAssignments(updatedAssignments);
  };

  const addAnswerLink = (assignmentIndex: number) => {
    const updatedAssignments = [...assignments];
    updatedAssignments[assignmentIndex].answerLinks.push('');
    setAssignments(updatedAssignments);
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
        {assignments.map((assignment, assignmentIndex) => (
          <div key={assignmentIndex} className="bg-white p-4 my-4 rounded shadow-lg" style={{ width: 'calc(100% - 530px)' }}>
            <h3 className="font-bold text-xl">{assignment.assignmentName}</h3>
            <p className="text-base">{assignment.courseName}</p>
            <p className="text-base">Due: {assignment.dueDate}</p>
            <p className="text-base">{assignment.description}</p>
            {assignment.assignmentLinks.map((link, linkIndex) => (
              <div key={linkIndex} className="my-1">
                <a href={link} target="_blank" rel="noopener noreferrer" className="underline text-blue-500">
                  View Assignment Link {linkIndex + 1}
                </a>
              </div>
            ))}
            <div className="mt-2">
              {assignment.answerLinks.map((link, linkIndex) => (
                <div key={linkIndex} className="flex items-center my-1">
                  <input
                    type="text"
                    value={link}
                    placeholder={`Answer link ${linkIndex + 1}`}
                    className="border-2 border-gray-300 rounded py-2 px-4 mr-2 w-72"
                    onChange={(e) => handleAnswerLinkChange(assignmentIndex, linkIndex, e.target.value)}
                  />
                  {linkIndex === assignment.answerLinks.length - 1 && (
                    <button onClick={() => addAnswerLink(assignmentIndex)} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded">
                      Add Link
                    </button>
                  )}
                </div>
              ))}
              <button
                onClick={() => handleSubmit(assignmentIndex)}
                className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
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
