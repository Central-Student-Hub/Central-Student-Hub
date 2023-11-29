'use client';
import React, { useState } from 'react';
import styles from '../Signup/Signup.module.css';
import { APIRequester } from '../../services/APIRequester.ts';

export type FormState = {
  SSN: string;
  email: string;
  password: string;
  type: string; // New field for type
};

export default function Home() {

  const apiRequester = new APIRequester();
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
    <form onSubmit={handleSubmit} className={styles.form}>
      {/* SSN Field */}
      <div className={styles.formGroup}>
        <label htmlFor="SSN">SSN</label>
        <div className={styles.inputContainer}>
          <input type="text" id="SSN" value={form.SSN} onChange={handleInputChange} />
          {errors.SSN && <span className={styles.error}>{errors.SSN}</span>}
        </div>
      </div>

      {/* Email Field */}
      <div className={styles.formGroup}>
        <label htmlFor="email">Email</label>
        <input type="email" id="email" value={form.email} onChange={handleInputChange} />
        {errors.email && <span className={styles.error}>{errors.email}</span>}
      </div>

      {/* Password Field */}
      <div className={styles.formGroup}>
        <label htmlFor="password">Password</label>
        <input type="password" id="password" value={form.password} onChange={handleInputChange} />
        {errors.password && <span className={styles.error}>{errors.password}</span>}
      </div>

      {/* Type Dropdown */}
      <div className={styles.formGroup}>
        <label htmlFor="type">Type</label>
        <select id="type" value={form.type} onChange={handleInputChange}>
          <option value="Staff">Staff</option>
          <option value="Student">Student</option>
          <option value="Admin">Admin</option>
        </select>
      </div>

      {/* Submit Button */}
      <div className={styles.formGroup}>
        <button type="submit" className={styles.subButton} disabled={isSubmitted}>Submit</button>
      </div>

      {/* Success Message */}
      {isSubmitted && <div className={styles.successMessage}>{successMessage}</div>}

    </form>
  );
}