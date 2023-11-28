import React, { FormEvent, MutableRefObject, useRef, useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';
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
    <div className="login-container" id="login-container">
      <img 
        src="https://cdn.discordapp.com/attachments/1169703919698587750/1178702979403100230/vik2l5e9.png?ex=65771bb1&is=6564a6b1&hm=06edc38d3e73db2f12fc0980001056c28f98fcd6e9578624b5411292bf7627a6&"
        alt="Central Student Hub logo"
      />

      <form className="login-form" action="submit" onSubmit={(e) => onLogin(e)}>
        
        <h1 className="login-label">login</h1>
        
        <div className="input-container email-container" email-icon='&#128232;'>
          <input className="email" ref={emailInput} type="email" id="email" placeholder=" email" />
        </div>
        
        <div className="input-container password-container" password-icon='&#x1F510;'>
          <input className="password" ref={passwordInput} type="password" id="password" placeholder="password" />
        </div>

        {invalid ? <p className="error">invalid email or password</p> : <></>}
        
        <a className="forget-pass" href="http://localhost:3000/forgot-my-password">forgot password?</a>

        <button className="login-button" type="submit">login</button>

        <div className='OAuth'>
          <span>login with</span>
          <FontAwesomeIcon icon={faGoogle} />
        </div>

        <button className="signUp-button" onClick={() => navigate("/signup")}>sign up</button>

      </form>
    </div>
  )
}