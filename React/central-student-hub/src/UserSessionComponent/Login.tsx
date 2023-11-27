import React, { FormEvent, MutableRefObject, useRef, useState } from "react";
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
      <img 
        src="https://cdn.discordapp.com/attachments/1169703919698587750/1174431449332596736/image.png?ex=6570cc05&is=655e5705&hm=72894009e4914ee56c9e99caaab334c263302ce98b16834d88b872e55f1d36c7&"
        alt="Central Student Hub logo"
      />
      <div className="login-div">
        {invalid ? <p>Invalid email or password</p> : <></>}
        <form className="login-form" action="submit" onSubmit={(e) => onLogin(e)}>
          <input className="login-input" ref={emailInput} type="email" id="email" placeholder="Email address" />
          <br />
          <input className="login-input" ref={passwordInput} type="password" id="password" placeholder="Password" />
          <br />
          <button className="login-button" id="login-button" type="submit">Login</button>
          <button onClick={() => navigate("/signup")} className="login-button">Sign-up</button>
        </form>

        <a className="login-a" href="http://localhost:3000/forgot-my-password">
          Forgot my password
        </a>
        <br />
        <button className="login-button">
          Login with Gmail
        </button>
      </div>
    </div>
  )
}