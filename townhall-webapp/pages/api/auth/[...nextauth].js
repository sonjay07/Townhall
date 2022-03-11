import NextAuth from 'next-auth'
import CredentialsProvider from 'next-auth/providers/credentials'

import { userService } from '../../../services/user.services'
import { ApiLogin } from './api-login';

// This is an example of how to read a JSON Web Token from an API route
//import { getToken } from "next-auth/jwt"

//Api route function that is returned from next auth
export default NextAuth({
  providers: [
    CredentialsProvider({
      async authorize(credentials) {
        // credentials will to passed from our login form
        // Your own logic here either check agains database or api endpoint
        // e.g. verify password if valid return user object.

       // const userInfo = userService.login(credentials.email, credentials.ps);
       //https://dev.to/dawnind/authentication-with-credentials-using-next-auth-and-mongodb-part-2-2g8k

        //const getUserLoginInfo = async ()  =>{
        //const reqBody = { usernameOrEmail : "margarita@gmail.com", password : "1234" }
        //console.log('reqBody .... ' , reqBody);
        ///const response = await fetch('http://localhost:8086/api/auth/signin', {
        //    method: 'POST',
        //    body: JSON.stringify(reqBody),
        //    headers: {
        //        'Content-Type' : 'application/json'
        //    },
           // mode: 'no-cors',
       // })
        //  const data = await response.json();
        //  console.log('POST: ', data);
        //  return data;
   // }
     
   // console.log('getUserLoginInfo: ', getUserLoginInfo);

   const client = await ApiLogin();

  // const usersCollection = client.db().collection('users');

  // const user = await usersCollection.findOne({
   //  email: credentials.email,
   //});

        const user = {
          id: 1,
          name: client.roles[1],
          email: client.roles[1],
          password: '12345',
        }
        if (
          credentials.email === user.email &&
          credentials.password === user.password
        )
          return user
        throw new Error('Incorrect Credentials') // This will be error message displayed in login form
      },
    }),
  ],
  callbacks: {
    // called after sucessful signin
    jwt: async ({ token, user }) => {
      if (user) token.id = user.id
      return token
    }, // called whenever session is checked
    session: async ({ session, token }) => {
      if (token) session.id = token.id
      return session
    },
  },
  secret: 'SECRET_HERE',
  session: {
    strategy: 'jwt',
    maxAge: 1 * 24 * 60 * 60, // 1d
  },
  jwt: {
    secret: 'SECRET_HERE',
    encryption: true,
  },
})