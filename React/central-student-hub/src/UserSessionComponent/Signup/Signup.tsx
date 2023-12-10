'use client';
import React, { useState } from 'react';
import './Signup.css';
import { ApiRequester } from '../../services/ApiRequester.ts';

export type FormState = {
  SSN: string;
  email: string;
  password: string;
  type: string; // New field for type
};

export default function Home() {

  const apiRequester = new ApiRequester();
  const [form, setForm] = useState<FormState>({
    SSN: '',
    email: '',
    password: '',
    type: 'Student', // Default type
  });
  const [errors, setErrors] = useState<FormState>({
    SSN: '',
    email: '',
    password: '',
    type: '',
  });
  const [isSubmitted, setIsSubmitted] = useState(false);

  const [successMessage, setsuccessMessage] = useState("");

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { id, value } = event.target;
    setForm((prevForm) => ({ ...prevForm, [id]: value }));
  };

  const validateFieldNotEmpty = (field: string, fieldName: string) => {
    return field.trim().length > 0 ? '' : `${fieldName} cannot be empty`;
  };

  const validateForm = () => {
    const newErrors = { ...errors };
    
    // Check for non-empty fields
    ['SSN', 'email', 'password', 'type'].forEach((key) => {
      newErrors[key as keyof FormState] = validateFieldNotEmpty(
        form[key as keyof FormState],
        key
      );
    });

    // Additional validations
    newErrors.email = /^\S+@\S+\.\S+$/.test(form.email) ? '' : 'Invalid email format';

    setErrors(newErrors);
    return !Object.values(newErrors).some((error) => error !== '');
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (validateForm()) {
      const haga = await apiRequester.signup(form);
      console.log(haga.message);
      setsuccessMessage(haga.message);
      console.log('Form is valid, submit the data', form);
      setIsSubmitted(haga.accept);
    } else {
      console.log('Form is invalid, do not submit', errors);
      setIsSubmitted(false);
    }
  };

  return (
    <div className='signUp-container' id='signUp-container'>
      <img 
        src="https://cdn.discordapp.com/attachments/1169703919698587750/1178702979403100230/vik2l5e9.png?ex=65771bb1&is=6564a6b1&hm=06edc38d3e73db2f12fc0980001056c28f98fcd6e9578624b5411292bf7627a6&"
        alt="Central Student Hub logo"
      />
      
      <form onSubmit={handleSubmit} className='signUp-form'>
        <h1 className='signUp-label'>Sign Up</h1>

        <div className='input-container'>
            <input type="text" id="SSN" value={form.SSN} onChange={handleInputChange} placeholder='ssn' />
            {errors.SSN && <span className='error'>{errors.SSN}</span>}
        </div>

        <div className='input-container'>
          <input type="email" id="email" value={form.email} onChange={handleInputChange} placeholder='email' />
          {errors.email && <span className='error'>{errors.email}</span>}
        </div>

        <div className='input-container'>
          <input type="password" id="password" minLength={8} maxLength={16} value={form.password} onChange={handleInputChange} placeholder='password' />
          {errors.password && <span className='error'>{errors.password}</span>}
        </div>

        <div className='select-and-button-container'>
          <select id="type" value={form.type} onChange={handleInputChange}>
            <option value="Staff">staff</option>
            <option value="Student">student</option>
            <option value="Admin">admin</option>
          </select>

          <button type="submit" className='subButton' disabled={isSubmitted}>submit</button>
        </div>

        {isSubmitted && <div className='success-message'>{successMessage}</div>}

      </form>
    </div>
  );
}