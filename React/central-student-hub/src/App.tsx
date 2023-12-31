import './App.css';
import React from 'react';
import Login from './UserSessionComponent/Login.tsx';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import TeachingStaffProfile from './UserProfileComponent/TeachingStaffProfile.tsx';
import SignUp from './UserSessionComponent/Signup.tsx';
import Home from './Home.tsx';
import SideBar from './StudentInterface/SideBar.tsx';
import { ChakraProvider } from '@chakra-ui/react'
import StudentProfile from './UserProfileComponent/StudentProfile.tsx';

function App() {
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
