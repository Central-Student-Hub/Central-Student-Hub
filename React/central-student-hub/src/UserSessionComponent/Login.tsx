import React, { FormEvent, MutableRefObject, useRef, useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFacebook, faGoogle } from '@fortawesome/free-brands-svg-icons';
import './Login.css';
import { useNavigate } from "react-router-dom";
// import { APIRequester } from "../Services/APIRequester.ts";

// const apiRequester = new APIRequester()

export default function Login() {
  const emailInput: MutableRefObject<HTMLInputElement | null> = useRef(null);
  const passwordInput: MutableRefObject<HTMLInputElement | null> = useRef(null);
  const navigate = useNavigate();
  const [invalid, setInvalid] = useState(false);

  async function onLogin(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    const email: string = (emailInput.current as HTMLInputElement).value;
    const password: string = (passwordInput.current as HTMLInputElement).value;
    // const loggedIn: boolean = await apiRequester.login(email, password);

    // if (loggedIn)
      // navigate("/")
    // else
      // setInvalid(true);
  }

  return (
    <div id="login-container">
      
      {invalid ? <p>Invalid email or password</p> : <></>}
      
      <form className="login-form" action="submit" onSubmit={(e) => onLogin(e)}>
        
        <h1 className="login-label">login</h1>
        
        <div className="email-input">
          <label htmlFor="email">email</label>
          <input ref={emailInput} type="email" id="email" placeholder="type you email" />
        </div>

        <div className="password-input">
          <label htmlFor="password">password</label>
          <input ref={passwordInput} type="password" id="password" placeholder="type your password" />
        </div>

        <a className="forget-pass" href="http://localhost:3000/forgot-my-password">forgot password?</a>

        <button className="login-button" type="submit">login</button>

        <div className="o-auth">
          <p>or sign up using</p>
          <div className='google-icon'>
            <FontAwesomeIcon icon={faGoogle} />
          </div>
        </div>

        <div className="signUp-button">
          <p>or sign up using</p>
          <button onClick={() => navigate("/signup")}>sign up</button>
        </div>

      </form>
    </div>
  )
}