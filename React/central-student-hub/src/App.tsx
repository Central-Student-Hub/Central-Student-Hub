import './App.css';
import React from 'react';
import Login from './UserSessionComponent/Login.tsx';
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <div className='container'>
      <BrowserRouter>
        <Routes>
          <Route path='/login' element={<Login />} />
          <Route path='/forgot-my-password' element={<Login />}></Route> {/* (TODO): Add forgot password component here */}
          <Route path='/' element={<Login />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
