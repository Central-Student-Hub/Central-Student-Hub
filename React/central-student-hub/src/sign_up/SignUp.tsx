"use client";
import React, { useState, FormEvent, useRef, useEffect } from "react";
import styles from "./page.module.css"; // Update import to match your file name
// import Link from 'next/link';
import UserInfo from "./components/userInfo.tsx";

export default function SignUp() {
  const [ssn, setSsn] = useState("");
  const [error, setError] = useState("");
  const [submitted, setSubmitted] = useState(false);
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (inputRef.current) {
      inputRef.current.focus();
    }
  }, []);

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError("");
    setSubmitted(false);

    // Check if SSN is 14 digits
    if (/^\d{14}$/.test(ssn)) {
      setError("");
      setSubmitted(true);
      console.log("SSN:", ssn);
    } else {
      setError("SSN must consist of 14 digits only");
    }
  };

  return (
    <>
      <div className={styles.container}>
        <form onSubmit={handleSubmit} className={styles.form}>
          <div>
            <div className={styles.tmp}>
              <label htmlFor="ssn" className={styles.label}>
                Enter National ID{" "}
              </label>
            </div>
            <input
              type="text"
              id="ssn"
              value={ssn}
              onChange={(e) => setSsn(e.target.value)}
              autoComplete="off" // Disable browser suggestions
              ref={inputRef} // Assign the ref to the input element
            />
          </div>
          <button
            type="submit"
            className={styles.subButton}
            disabled={submitted}
          >
            Submit
          </button>
          {submitted && (
            <div className={styles.successMessage}>
              Submitted successfully. <br />
              Please wait for approval.
            </div>
          )}
          {error && <p className={styles.error}>{error}</p>}
        </form>
      </div>
      {submitted && (
        <div>
          <UserInfo ssnNew={ssn} />
        </div>
      )}
    </>
  );
}
