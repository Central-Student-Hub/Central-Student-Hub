import './App.css';
import React from 'react';
import Login from './UserSessionComponent/Login/Login.tsx';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import SignUp from './UserSessionComponent/Signup/Signup.tsx';

function App() {
  return (
    <div className='container'>
      <BrowserRouter>
        <Routes>
          <Route path='/login' element={<Login />} />
          <Route path='/forgot-my-password' element={<Login />}></Route> {/* (TODO): Add forgot password component here */}
          <Route path='/' element={<SignUp />}></Route>
          <Route path='/signup' element={<SignUp />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
