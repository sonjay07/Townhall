
import { Fragment, useState } from "react";
import classes from './login-form.module.css';
import {useRef} from 'react';
import { useSession, signIn, signOut } from "next-auth/react";

function LoginForm(props) {
    const {} = props;
    const usernameRef = useRef();
    const passwordRef = useRef();
    const { data: session } = useSession();


    const [isLogin, setIsLogin] = useState(true);

    function swithAuthModeHandler() {
        setIsLogin((prevState) => !prevState);
    }

    function handleLogin(){
        console.log('Hello From Login..');
        const enteredUsername= usernameRef.current.value;
        const enteredPassword = passwordRef.current.value;
  
        console.log('username.. ' + enteredUsername);
        console.log('password.. ' + enteredPassword);


    }

    if (session) {
        return (
          <>
            Signed in as {session.user.email} <br />
            <button onClick={() => signOut()}>Sign out</button>
          </>
        )
      }

    return (
       <Fragment>
            <form>    
                <label><b>Username
                </b>    
                </label>    
                <input type="text" name="username" id="username" placeholder="username" ref={usernameRef}/>    
                <br/> 
                <label><b>Password  </b>    
                </label>    
                <input type="Password" name="password" id="password" placeholder="password" ref={passwordRef}/>    
                <br/>
                <input type="button" name="log" id="log" value="Log In Here" className='btn btn--warning' onClick={handleLogin}/>  
                <br/>
                <input type="checkbox" id="check"/>    
                <span>Remember me</span>    
                <br/>
                Forgot Password
            </form>     
          
       </Fragment>
    );
}

export default LoginForm; 


