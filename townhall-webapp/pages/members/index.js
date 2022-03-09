import { Fragment } from "react";
import { signOut, useSession } from 'next-auth/react'


function MembersPage() {
    const { data: session } = useSession()

   const getUserLoginInfo = async ()  =>{
        const reqBody = { usernameOrEmail : "margarita@gmail.com", password : "1234" }
       
        //const res = await fetch("http://localhost:8086/api/auth/signin",{ 
        //    mode: 'no-cors' // 'cors' by default
        //}); 
        //const data = res.json();

        //const reqBody = {email: enteredEmail, text: enteredFeedback}

        console.log('reqBody .... ' , reqBody);

        const response = await fetch('http://localhost:8086/api/auth/signin', {
            method: 'POST',
            body: JSON.stringify(reqBody),
            headers: {
                'Content-Type' : 'application/json'
            },
           // mode: 'no-cors',
        })

          const data = await response.json();
          console.log('POST: ', data);
       
    }
  

    if(!session) {
        return (
            <Fragment>
                <h1>Member's Page (Unauthenticated) </h1>
            </Fragment>
         );
    }

    return (
        <Fragment>
            <h1>Members Page  </h1> <a>{session.user.name}</a>
          
            <button
                onClick={() => {
                    signOut({ redirect: false })
                }}
                >
          Signout
        </button>
        <button
                onClick={getUserLoginInfo}
                >
          Get Users 
        </button>
        </Fragment>
     );
}

export default MembersPage

/**
 * 
 import { getSession } from "next-auth/react";

const handler =  async (req,res) => {
    const session = await getSession({ req })

    if(!session) {
         res.status(401).json({ error: 'Unauthenticated User'})
    } else {
        res.status(200).json({ message: 'Success', session})
    }
}

export default handler
 */