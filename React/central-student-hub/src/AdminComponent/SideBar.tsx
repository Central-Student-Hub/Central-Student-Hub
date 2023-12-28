import React, { useState } from 'react';
import { BsPerson, BsBook, BsPlusSquare, BsPersonPlus, BsBoxArrowRight, BsPencilSquare } from 'react-icons/bs';
import UserProfile from './UserProfile';
import AddSemesterCourse from './AddSemesterCourse';
import AddCourse from './AddCourse';
import AddNewStudent from './AddNewStudent';
import AddExam from './AddExam'; // Import your AddExam component

const SideBar: React.FC = () => {
  const [activeComponent, setActiveComponent] = useState<string>('');

  const renderComponent = () => {
    switch (activeComponent) {
      case 'userProfile':
        return <UserProfile />;
      case 'addSemesterCourse':
        return <AddSemesterCourse />;
      case 'addCourse':
        return <AddCourse />;
      case 'addNewStudent':
        return <AddNewStudent />;
      case 'addExam':
        return <AddExam />;
      default:
        return <div>Welcome to Admin Panel</div>;
    }
  };

  const handleLogout = () => {
    console.log('Logging out...');
  };

  return (
    <div className="flex">
      <div className="sidebar">
        <SideBarIcon icon={<BsPerson size="28" />} text="User Profile" onClick={() => setActiveComponent('userProfile')} />
        <SideBarIcon icon={<BsBook size="28" />} text="Add Semester Course" onClick={() => setActiveComponent('addSemesterCourse')} />
        <SideBarIcon icon={<BsPlusSquare size="28" />} text="Add Course" onClick={() => setActiveComponent('addCourse')} />
        <SideBarIcon icon={<BsPersonPlus size="28" />} text="Add New Student" onClick={() => setActiveComponent('addNewStudent')} />
        <SideBarIcon icon={<BsPencilSquare size="28" />} text="Add Exam" onClick={() => setActiveComponent('addExam')} />
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
