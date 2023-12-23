import React, { useState, useEffect } from 'react';
import { ApiRequester } from '../services/ApiRequester.ts';

export type Location = {
  building: number;
  room: number;
};

export type Session = {
  id: number;
  period: number;
  weekday: string;
  sessionType: string;
  teacherName: string;
  location: Location;
};

export type Course = {
  id: number;
  name: string;
  description?: string;
  creditHours: number;
  prerequisitesCodes?: string[];
  maxSeats: number;
  remainingSeats: number;
  sessions: Session[];
};

const Registration: React.FC = () => {
  const [courses, setCourses] = useState<Course[]>([]);
  const [availableHours, setAvailableHours] = useState<number>(0); 
  const [selectedCourses, setSelectedCourses] = useState<Course[]>([]);
  const [showDetails, setShowDetails] = useState<boolean>(false);
  const [detailedCourse, setDetailedCourse] = useState<Course | null>(null);
  const [isFocused, setIsFocused] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [debouncedTerm, setDebouncedTerm] = useState(searchTerm);
  const apiRequester = new ApiRequester();

  useEffect(() => {
    const handler = setTimeout(() => {
      setDebouncedTerm(searchTerm);
    }, 1000);

    return () => {
      clearTimeout(handler);
    };
  }, [searchTerm]);

  useEffect(() => {
    if (debouncedTerm) {
      console.log(`Search for: ${debouncedTerm}`);

      const getCourses = async () => await apiRequester.getCourses(debouncedTerm);
      getCourses()
        .then((courses) => setCourses(courses))
        .catch((error) => console.error(error));
    }
  }, [debouncedTerm]);

  useEffect(() => {
    async function fetchCourses() {
      const response = await fetch('/api/courses'); // Replace with your endpoint
      const data = await response.json();
      setCourses(data);
    }

    async function fetchAvailableHours() {
      const response = await fetch('/api/available-hours'); // Replace with your endpoint
      const data = await response.json();
      setAvailableHours(data.availableHours);
    }

    fetchCourses();
    fetchAvailableHours();
  }, []);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
  };

  const handleInputFocus = () => {
    setIsFocused(true);
  };

  const handleInputBlur = () => {
    setIsFocused(false);
  };

  const handleCourseSelect = async (course: Course) => {
    const response = await apiRequester.verifyCourse({
      courseId: course.id,
      creditHours: availableHours,
      sessions: selectedCourses.flatMap(course => course.sessions),
      newSession: course.sessions[0]
    })

    if (response) {
      setSelectedCourses([...selectedCourses, course]);
      // Optionally, update available hours here
    } else {
      // Handle registration failure (e.g., prerequisites not met)
      // console.error('Registration failed:', data.errorMessage);
    }
  };

  const viewCourseDetails = (course: Course) => {
    setDetailedCourse(course);
    setShowDetails(true);
  };

  const removeSelectedCourse = (courseId: number) => {
    setSelectedCourses(selectedCourses.filter(course => course.id !== courseId));
  };

  // Function to handle checkout - sending the list of selected courses to the backend
  const handleCheckout = async () => {
    console.log('Selected Courses for Checkout:', selectedCourses);
    // TODO: Replace with actual API call to send selected courses
    // const response = await fetch('/api/checkout', { method: 'POST', body: JSON.stringify(selectedCourses) });
    // const data = await response.json();
    // Handle the response as needed
  };

  const renderCoursesTable = () => (
    <div className="mt-4 p-4 border-2 border-gray-500 rounded-md" style={{ width: '60%' }}>
      <table className="w-full">
        <thead>
          <tr>
            <th className="px-2 py-2 border-2 border-gray-500">ID</th>
            <th className="px-2 py-2 border-2 border-gray-500">Name</th>
            <th className="px-2 py-2 border-2 border-gray-500">Credit Hours</th>
            <th className="px-2 py-2 border-2 border-gray-500">Remaining Seats</th>
            <th className="px-2 py-2 border-2 border-gray-500">Sessions</th>
            <th className="px-2 py-2 border-2 border-gray-500">Select</th>
          </tr>
        </thead>
        <tbody>
          {courses.map(course => (
            <tr key={course.id}>
              <td className="border-2 px-2 py-2 border-gray-500">{course.id}</td>
              <td className="border-2 px-2 py-2 border-gray-500">
                <button className="text-blue-500 underline" onClick={() => viewCourseDetails(course)}>
                  {course.name}
                </button>
              </td>
              <td className="border-2 px-2 py-2 border-gray-500">{course.creditHours}</td>
              <td className="border-2 px-2 py-2 border-gray-500">{course.remainingSeats}</td>
              <td className="border-2 px-2 py-2 border-gray-500">
                {course.sessions.map((session, index) => (
                  <div key={index}>
                    <p>Location: {session.location.building}, Room: {session.location.room}</p>
                    <p>Period: {session.period}, Weekday: {session.weekday}, Teacher: {session.teacherName}</p>
                    {index !== course.sessions.length - 1 && <hr className="my-2" />}
                  </div>
                ))}
              </td>
              <td className="border-2 px-2 py-2 border-gray-500">
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded" onClick={() => handleCourseSelect(course)}>
                  Select
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );

  const renderSelectedCourses = () => (
    <div className="mb-4">
      <h2 style={{ fontSize: '22px' }}className=" font-bold mb-4">Selected Courses:</h2>
      <div className="flex flex-wrap">
        {selectedCourses.map(course => (
          <div
            key={course.id}
            className="border border-blue-400 rounded-md p-2 mr-2 mb-2 cursor-pointer"
            onClick={() => removeSelectedCourse(course.id)}
            title="Click to remove"
          >
            {course.name}
          </div>
        ))}
      </div>
    </div>
  );


  const renderCourseDetails = () => (
    showDetails && detailedCourse && (
      <div className="fixed inset-0 bg-gray-500 bg-opacity-75 z-50">
        <div className="flex items-center justify-center h-full">
          <div className="bg-white p-6 border border-gray-300 rounded-lg w-3/4">
            <h2 className="text-xl font-semibold">{detailedCourse.name}</h2>
            <p className="text-gray-600 mb-4">{detailedCourse.description || 'No description available'}</p>
            <p>Credit Hours: {detailedCourse.creditHours}</p>
            <p>Remaining Seats: {detailedCourse.remainingSeats}</p>
            {detailedCourse.prerequisitesCodes && (
              <>
                <h3 className="mt-4 text-lg font-semibold">Prerequisites:</h3>
                <ul>
                  {detailedCourse.prerequisitesCodes.map((code, index) => (
                    <li key={index}>{code}</li>
                  ))}
                </ul>
              </>
            )}
            <h3 className="mt-4 text-lg font-semibold">Sessions:</h3>
            <ul className="list-disc list-inside">
              {detailedCourse.sessions.map(session => (
                <li key={session.id}>
                  {session.weekday}, Period: {session.period}, Type: {session.sessionType}, 
                  Teacher: {session.teacherName}, Location: Building {session.location.building}, Room {session.location.room}
                </li>
              ))}
            </ul>
            <button className="mt-6 bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded" onClick={() => setShowDetails(false)}>
              Close
            </button>
          </div>
        </div>
      </div>
    )
  );

  return (
    <div className="p-5">
      <div className="mb-4 font-bold" style={{ fontSize: '22px' }}>Available Hours: {availableHours}</div>
      <input 
        type="text" 
        value={searchTerm}
        onFocus={handleInputFocus}
        onBlur={handleInputBlur}
        onChange={handleInputChange}       
        placeholder="Search..."
        className={`border-2 ${isFocused ? 'border-black' : 'border-gray-500'} bg-white h-10 px-5 pr-16 rounded-lg text-sm focus:outline-none`}
      />

      {renderSelectedCourses()}
       <button
         onClick={handleCheckout}
         className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mb-4"
      >
        Checkout
      </button>
      {renderCoursesTable()}
      {renderCourseDetails()}
    </div>
  );
};

export default Registration;
