'use client'
import React, { useState } from 'react';
import { BsPerson, BsBook, BsCalendar, BsCashStack, BsGraphUp, BsBoxArrowRight, BsHouseDoor, BsLayoutTextSidebarReverse } from 'react-icons/bs';
import Registration from './Registration.tsx';
import Schedule from './Schedule.tsx';
import Fees from './Fees.tsx';
import Grades from './Grades.tsx';
import Course from './Course.tsx';
import TeachingStaffProfile from '../UserProfileComponent/TeachingStaffProfile.tsx';
import StudentProfile from '../UserProfileComponent/StudentProfile.tsx';

const SideBar: React.FC = () => {
  const [activeComponent, setActiveComponent] = useState<string>('userProfile');

  const renderComponent = () => {
    switch (activeComponent) {
      case 'userProfile':
        return <StudentProfile />;
      case 'registration':
        return <Registration />;
      case 'schedule':
        return <Schedule />;
      case 'fees':
        return <Fees />;
      case 'grades':
        return <Grades />;
      case 'course':
        return <Course />;
      case 'home': 
        // return <Timeline />;
      default:
        return <div >Welcome to Student Panel</div>;
    }
  };

  const handleLogout = () => {
    console.log('Logging out...');
  };

  return (
    <div className="flex">
      <div className="sidebar">
        <SideBarIcon icon={<BsPerson size="28" />} text="User Profile" onClick={() => setActiveComponent('userProfile')} />
        <SideBarIcon icon={<BsBook size="28" />} text="Registration" onClick={() => setActiveComponent('registration')} />
        <SideBarIcon icon={<BsCalendar size="28" />} text="Schedule" onClick={() => setActiveComponent('schedule')} />
        <SideBarIcon icon={<BsCashStack size="28" />} text="Fees" onClick={() => setActiveComponent('fees')} />
        <SideBarIcon icon={<BsGraphUp size="28" />} text="Grades" onClick={() => setActiveComponent('grades')} />
        <SideBarIcon icon={<BsLayoutTextSidebarReverse size="28" />} text="Course" onClick={() => setActiveComponent('course')} />
        <SideBarIcon icon={<BsHouseDoor size="28" />} text="Home" onClick={() => setActiveComponent('home')} /> {/* Changed 'Timeline' to 'Home' */}
        <div className="mt-auto">
          <SideBarIcon icon={<BsBoxArrowRight size="28" />} text="Logout" onClick={handleLogout} />
        </div>
      </div>
      <div className="main-content">
        {renderComponent()}
      </div>
    </div>
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
