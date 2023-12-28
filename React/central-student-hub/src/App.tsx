import './App.css';
import React from 'react';
import Login from './UserSessionComponent/Login.tsx';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import TeachingStaffProfile from './UserProfileComponent/TeachingStaffProfile.tsx';
import SignUp from './UserSessionComponent/Signup.tsx';
import SideBar from './StudentInterface/SideBar.tsx';
import { ChakraProvider } from '@chakra-ui/react'
import StudentProfile from './UserProfileComponent/StudentProfile.tsx';
import { DeleteSemesterCourse } from './AdminPages/DeleteSemesterCourse.tsx';
import AddSemesterCourse from './AdminPages/AddSemesterCourse.tsx';
import AddUsers from './AdminPages/AddUsers.tsx';
import DeleteCourse from './AdminPages/DeleteCourse.tsx';
import AddNewCourse from './AdminPages/AddNewCourse.tsx';
import RegistrationDeadline from './AdminPages/RegistrationDeadline.tsx';
import AddStudentProfileInfo from './AdminPages/AddStudentProfileInfo.tsx';
import AddStudentGrades from './AdminPages/AddStudentGrades.tsx';

function App() {

  // return (
  //   <ChakraProvider>
  //     {/* <DeleteSemesterCourse /> */}
  //     {/* <AddSemesterCourse /> */}
  //     {/* <AddUsers /> */}
  //     {/* <DeleteCourse /> */}
  //     {/* <AddNewCourse /> */}
  //     <SideBar/>
  //     {/* <RegistrationDeadline /> */}
  //     {/* <DeleteCourse /> */}
  //     {/* <AddNewCourse /> */}
  //     {/* <AddStudentGrades /> */}
  //     {/* <AddStudentProfileInfo /> */}
  //   </ChakraProvider>
  // )

  return (
    <ChakraProvider>
      <BrowserRouter>        
        <Routes>
          <Route path='/login' element={<Login />} />
          <Route path='/forgot-my-password' element={<Login />}></Route> {/* (TODO): Add forgot password component here */}
          <Route path='/' element={<SideBar />}></Route>
          <Route path='/signup' element={<SignUp />}></Route>
          <Route path='/teaching-staff-profile/:id' element={<TeachingStaffProfile />}></Route>
          <Route path='/teaching-staff-profile/:id' element={<StudentProfile/>}></Route>
        </Routes>
      </BrowserRouter>
    </ChakraProvider>
  );
}

export default App;
