'use client'
import React, { useEffect, useState } from 'react';
import { BsPerson, BsBook, BsCalendar, BsCashStack, BsGraphUp, BsBoxArrowRight, BsHouseDoor, BsLayoutTextSidebarReverse, BsPaperclip, BsX, BsXSquare, BsCalendar2Check, BsExclamation, BsClock } from 'react-icons/bs';
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
import { Tooltip } from '@chakra-ui/react';

type Component = {
  name: string;
  icon: JSX.Element;
}

const SideBar: React.FC = () => {

  const studentComponents: Component[] = [
    {
      name: 'Home',
      icon: <BsHouseDoor size="28" />
    }, 
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
      icon: <BsPaperclip size="28" />
    }
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
    {
      name: 'Home',
      icon: <BsHouseDoor size="28" />
    },
  ];

  const adminComponents: Component[] = [
    {
      name: 'Add User',
      icon: <BsPerson size="28" />
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
    }
  ];


  const [components, setComponents] = useState<Component[]>([]);
  const [activeComponent, setActiveComponent] = useState<string>('Home');

  const api = new ApiRequester();

  useEffect(() => {
    if (document.cookie === "") {
      window.location.href = 'http://localhost:3000/login';
    }

    const getRole = async () => {
      const response = await api.getRole();
      console.log(response);
      setComponents(response === 'Student' ? studentComponents : response === 'Staff' ? teacherComponents : adminComponents)
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
      case 'Home': 
        // return <Timeline />;
        return;
      case 'Teacher Profile':
        return <TeachingStaffProfile />;
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
      case 'Delete Semester Course':
        return <DeleteSemesterCourse />;
      case 'Add Warning':
        return <AddWarning />;
      case 'Set Registration Deadline':
        return <RegistrationDeadline />;
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
  
  <Tooltip label={text} placement='start' borderRadius='0px 5px 5px 0px'>
      <div className="sidebar-icon group" onClick={onClick}>
        {icon}
      </div>
  </Tooltip>
);

export default SideBar;

