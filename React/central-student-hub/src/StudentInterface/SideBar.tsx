'use client'
import React, { useEffect, useState } from 'react';
import { BsPerson, BsBook, BsCalendar, BsCashStack, BsGraphUp, BsBoxArrowRight, BsHouseDoor, BsLayoutTextSidebarReverse, BsPaperclip,
   BsCalendar2Check, BsExclamation, BsClock, BsCalendar2Week, BsPencilSquare, BsPersonPlus, BsMap, BsCheck2Square } from 'react-icons/bs';
import Registration from './Registration.tsx';
import Schedule from './Schedule.tsx';
import Fees from './Fees.tsx';
import Grades from './Grades.tsx';
import Course from './Course.tsx';
import TeachingStaffProfile from '../UserProfileComponent/TeachingStaffProfile.tsx';
import StudentProfile from '../UserProfileComponent/StudentProfile.tsx';
import { ApiRequester } from '../Services/ApiRequester.ts';
import AddUsers from '../AdminPages/AddUsers.tsx';
import AddNewCourse from '../AdminPages/AddNewCourse.tsx';
import DeleteCourse from '../AdminPages/DeleteCourse.tsx';
import AddSemesterCourse from '../AdminPages/AddSemesterCourse.tsx';
import { DeleteSemesterCourse } from '../AdminPages/DeleteSemesterCourse.tsx';
import { AddWarning } from '../AdminPages/AddWarning.tsx';
import RegistrationDeadline from '../AdminPages/RegistrationDeadline.tsx';
import AddExam from '../AdminComponent/AddExam.tsx';
import AddStudentProfileInfo from '../AdminPages/AddStudentProfileInfo.tsx';
import AddLocation from '../AdminPages/AddLocation.tsx';
import ShowExamTable from './ShowExamTable.tsx';
import AddStudentGrades from '../AdminPages/AddStudentGrades.tsx';

type Component = {
  name: string;
  icon: JSX.Element;
}

const SideBar: React.FC = () => {

  const studentComponents: Component[] = [
    {
      name: 'Student Profile',
      icon: <BsPerson size="28" />
    },
    {
      name: 'Course',
      icon: <BsLayoutTextSidebarReverse size="28" />
    },
    {
      name: 'Schedule',
      icon: <BsCalendar size="28" />
    },
    {
      name: 'Grades',
      icon: <BsGraphUp size="28" />
    },
    {
      name: 'Registration',
      icon: <BsBook size="28" />
    },
    {
      name: 'Fees',
      icon: <BsCashStack size="28" />
    }, 
    {
      name: 'Exams',
      icon: <BsCalendar2Week size="28" />
    },
  ];

  const teacherComponents: Component[] = [
    {
      name: 'Teacher Profile',
      icon: <BsPerson size="28" />
    },
    {
      name: 'Course',
      icon: <BsLayoutTextSidebarReverse size="28" />
    }, 
    {
      name: 'Schedule',
      icon: <BsCalendar size="28" />
    },
  ];

  const adminComponents: Component[] = [
    {
      name: 'Add User',
      icon: <BsPersonPlus size="28" />
    },
    {
      name: 'Admin Course',
      icon: <BsLayoutTextSidebarReverse size="28" />
    },
    {
      name: 'Admin Semester Course',
      icon: <BsCalendar2Check size="28" />
    },
    {
      name: 'Add Warning',
      icon: <BsExclamation size="28" />
    },
    {
      name: 'Set Registration Deadline',
      icon: <BsClock size="28" />
    },
    {
      name: 'Add Exam',
      icon: <BsPencilSquare size="28" />
    },
    {
      name: 'Add Location',
      icon: <BsMap size="28" />
    },
    {
      name: 'Add Student Profile Info',
      icon: <BsPerson size="28" />
    },
    {
      name: 'Add Student Grade',
      icon: <BsCheck2Square size="28" />
    }
  ];


  const [components, setComponents] = useState<Component[]>([]);
  const [activeComponent, setActiveComponent] = useState<string>('');

  const api = new ApiRequester();

  useEffect(() => {
    if (document.cookie === "") {
      window.location.href = 'http://localhost:3000/login';
    }

    const getRole = async () => {
      const response = await api.getRole();
      console.log(response);
      setComponents(response === 'Student' ? studentComponents : response === 'Staff' ? teacherComponents : adminComponents);
      setActiveComponent(response === 'Student' ? 'Student Profile' : response === 'Staff' ? 'Teacher Profile' : 'Add User');
    };

    getRole();
  }, []);

  const renderComponent = () => {
    switch (activeComponent) {
      case 'Student Profile':
        return <StudentProfile />;
      case 'Registration':
        return <Registration />;
      case 'Schedule':
        return <Schedule />;
      case 'Fees':
        return <Fees />;
      case 'Grades':
        return <Grades />;
      case 'Course':
        return <Course />;
      case 'Exams':
        return <ShowExamTable />
      case 'Teacher Profile':
        return <TeachingStaffProfile />;

        // admin components
      case 'Add User':
        return <AddUsers /> 
      case 'Admin Course':
        return<div style={{ gap: '200px', display: 'flex', flexDirection: 'row'}}>
        <AddNewCourse /> 
        <DeleteCourse /> 
        </div>
      case 'Admin Semester Course':
        return (
        <div style={{display: 'flex', flexDirection: 'column'}}>
          <AddSemesterCourse /> 
          <DeleteSemesterCourse />  
        </div>
      );
      case 'Add Warning':
        return <AddWarning />; 
      case 'Set Registration Deadline':
        return <RegistrationDeadline />; 
      case 'Add Exam':
        return <div style={{marginTop: '100px'}}>
          <AddExam /> 
        </div>
      case 'Add Location':
        return <AddLocation /> 
      case 'Add Student Profile Info':
        return <AddStudentProfileInfo />; 
      case 'Add Student Grade':
        return <AddStudentGrades />;
      default:
        return <div >Welcome to Central Student Hub</div>;
    }
  };

  //"Add User", "Add Course", "Delete Course", "Add Semester Course", "Delete Semester Course", "Add Warning", "Set Registration Deadline"

  const handleLogout = () => {
    document.cookie.split(";").forEach((c) =>
     document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/")
    );
    window.location.reload();
  };

  return (
    <>
      <div className="sidebar">
        {
          components.map(component => (
            <SideBarIcon
            icon={component.icon}
              text={component.name}
              onClick={() => setActiveComponent(component.name)}
            />
          ))
        }
        <div className="mt-auto">
          <SideBarIcon icon={<BsBoxArrowRight size="28" />} text="Logout" onClick={handleLogout} />
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
        {renderComponent()}
      </div>
    </>
  );
};

const SideBarIcon: React.FC<{ icon: JSX.Element; text: string; onClick: () => void }> = ({ icon, text, onClick }) => (
  <div className="sidebar-icon group" onClick={onClick}>
    {icon}
    <span className="sidebar-tooltip group-hover:scale-100">
      {text}
    </span>
  </div>
);

export default SideBar;

