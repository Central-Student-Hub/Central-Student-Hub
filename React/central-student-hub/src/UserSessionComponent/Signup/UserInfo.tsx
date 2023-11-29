'use client';
import React, { useState, useRef, useEffect } from 'react';
import styles from './UserInfo.module.css';
import DatePicker from 'react-datepicker' ; 
import 'react-datepicker/dist/react-datepicker.css';
// import { useRouter } from 'next/router';


type FormState = {
  SSN: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  username: string;
  password: string;
  confirmPassword: string;
  birthdate: string;
  country: string;
  city: string;
  street: string;
  building: string;
  square: string;
};

type UserInfoPageProps = {
  ssnNew: string;
};

export default function UserInfo({ ssnNew }: UserInfoPageProps) {
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);
  const currentDate = new Date();
  const minDate = new Date(currentDate.getFullYear() - 80, 0, 1); 
  const maxDate = new Date(currentDate.getFullYear() - 15, 11, 31);
  const defaultOpenYear = new Date(currentDate.getFullYear() - 18, 0, 1);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const firstNameRef = useRef<HTMLInputElement>(null);
  const [form, setForm] = useState<FormState>({
    SSN: ssnNew,
    firstName: '',
    lastName: '',
    phoneNumber: '',
    email: '',
    username: '',
    password: '',
    confirmPassword: '',
    birthdate: '',
    country: '',
    city:'',
    square:'',
    street:'',
    building:'',
  });
  const [errors, setErrors] = useState<FormState>({
    SSN:'',
    firstName: '',
    lastName: '',
    phoneNumber: '',
    email: '',
    username: '',
    password: '',
    confirmPassword: '',
    birthdate: '',
    country: '',
    city:'',
    square:'',
    street:'',
    building:'',
  });


  
  useEffect(() => {
    if (firstNameRef.current) {
      firstNameRef.current.focus();
    }
  }, []);

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = event.target;
    setForm((prevForm) => ({ ...prevForm, [id]: value }));
  };

  const validatePhoneNumber = (phoneNumber: string) => {
    const phoneNumberRegex = /^\d*$/; // Regex to allow digits only
    return phoneNumberRegex.test(phoneNumber)
      ? ''
      : 'Phone number should consist of digits only';
  };

  const validateFieldNotEmpty = (field: string, fieldName: string) => {
    return field.trim().length > 0 ? '' : `${fieldName} cannot be empty`;
  };

  const validatePassword = (password: string) => {
    const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,}$/;
    return passwordRegex.test(password)
      ? ''
      : 'Password must be at least 8 characters long and include a number, an uppercase letter, a lowercase letter, and a special character';
  };

  const validateForm = () => {
    const newErrors = { ...errors };
  
    // Check for non-empty fields
    Object.keys(form).forEach((key) => {
      newErrors[key as keyof FormState] = validateFieldNotEmpty(
        form[key as keyof FormState],
        key
      );
    });
  
    // Additional validations
    newErrors.password = validatePassword(form.password);
    newErrors.confirmPassword =
      form.password === form.confirmPassword
        ? ''
        : 'Passwords do not match';
  
    newErrors.phoneNumber = validatePhoneNumber(form.phoneNumber);
  
    // Check for email format (simple regex for example purposes)
    newErrors.email = /^\S+@\S+\.\S+$/.test(form.email)
      ? ''
      : 'Invalid email format';
  
    setErrors(newErrors);
  
    // Check if there are any errors
    return !Object.values(newErrors).some((error) => error !== '');
  };
  

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (validateForm()) {
      console.log('Form is valid, submit the data', form);
      // Submit to backend here
      // Set the submission state to true to show the success message
      setIsSubmitted(true);
    } else {
      console.log('Form is invalid, do not submit', errors);
      // Set the submission state to false in case of form errors
      setIsSubmitted(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} className={styles.userInfoForm}>
      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="SSN">SSN</label>
        </div>
        <div className={styles.editInput}>
          <span>{form.SSN}</span>
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
            <label htmlFor="firstName">First Name</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="text"
            id="firstName"
            value={form.firstName}
            onChange={handleInputChange}
            ref={firstNameRef}
          />
          {errors.firstName && <span className={styles.error}>{errors.firstName}</span>}
        </div>
      </div>


      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="lastName">Last Name</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="text"
            id="lastName"
            value={form.lastName}
            onChange={handleInputChange}
          />
          {errors.lastName && (<span className={`${styles.error} ${styles.errorMessage}`}>
              {errors.lastName}</span>
          )}
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="phoneNumber">Phone Number</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="text"
            id="phoneNumber"
            value={form.phoneNumber}
            onChange={handleInputChange}
          />
          {errors.phoneNumber && (
            <span className={`${styles.error} ${styles.errorMessage}`}>
              {errors.phoneNumber}
            </span>
          )}
        </div>
      </div>
      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="email">Email</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="email"
            id="email"
            value={form.email}
            onChange={handleInputChange}
          />
          {errors.email && (
            <span className={`${styles.error} ${styles.errorMessage}`}>
              {errors.email}
            </span>
          )}
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="username">Username</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="text"
            id="username"
            value={form.username}
            onChange={handleInputChange}
          />
          {errors.username && (
            <span className={`${styles.error} ${styles.errorMessage}`}>
              {errors.username}
            </span>
          )}
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="password">Password</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="password"
            id="password"
            value={form.password}
            onChange={handleInputChange}
          />
          {errors.password && (
            <span className={`${styles.error} ${styles.errorMessage}`}>
              {errors.password}
            </span>
          )}
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="confirmPassword">Confirm Password</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="password"
            id="confirmPassword"
            value={form.confirmPassword}
            onChange={handleInputChange}
          />
          {errors.confirmPassword && (
            <span className={`${styles.error} ${styles.errorMessage}`}>
              {errors.confirmPassword}
            </span>
          )}
        </div>
      </div>


      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="birthdate">Birthdate</label>
        </div>
        <div className={styles.editInput}>
          {/* <input type='date' id='birthdate' value={form.birthdate} onChange={handleInputChange} /> */}
          <DatePicker  
            selected={selectedDate}
            onChange={(date: Date) => {
              setSelectedDate(date);
              setForm({ ...form, birthdate: date.toISOString().split('T')[0] }); 
            }}
            dateFormat="yyyy/MM/dd"
            showYearDropdown
            scrollableYearDropdown
            yearDropdownItemNumber={80}
            minDate={minDate}
            maxDate={maxDate}
            openToDate={defaultOpenYear}
          />
          {errors.birthdate && (
            <span className={`${styles.error} ${styles.errorMessage}`}>
              {errors.birthdate}
            </span>
          )}
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="country">Country</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="text"
            id="country"
            value={form.country}
            onChange={handleInputChange}
          />
          {errors.country && <span className={styles.error}>{errors.country}</span>}
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="city">City</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="text"
            id="city"
            value={form.city}
            onChange={handleInputChange}
          />
          {errors.city && <span className={styles.error}>{errors.city}</span>}
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="square">Square</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="text"
            id="square"
            value={form.square}
            onChange={handleInputChange}
          />
          {errors.square && <span className={styles.error}>{errors.square}</span>}
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="street">Street</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="text"
            id="street"
            value={form.street}
            onChange={handleInputChange}
          />
          {errors.street && <span className={styles.error}>{errors.street}</span>}
        </div>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.editLabel}>
          <label htmlFor="building">Building</label>
        </div>
        <div className={styles.editInput}>
          <input
            type="text"
            id="building"
            value={form.building}
            onChange={handleInputChange}
          />
          {errors.building && <span className={styles.error}>{errors.building}</span>}
        </div>
      </div>   
  
      <div className={styles.formGroup}>
        <button type="submit" className={styles.userInfosubButton}  disabled={isSubmitted}>Submit</button>
      </div>
      

      {isSubmitted && (
        <div className={styles.successMessage}>
          Signup successful!
        </div>
      )}

  </form>
);
}
