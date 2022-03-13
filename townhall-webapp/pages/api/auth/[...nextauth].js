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

        //const { email, password } = credentials;

        const client = await ApiLogin();

        const user = {
          id: 14567,
          name: client.roles[0],
          email: client.roles[1],
          password: '12345',
          token: client.accessToken
        }
        
        if (
          credentials.email === user.email &&
          credentials.password === user.password 
          
        )
         // console.log('user', user);
          return user
        throw new Error('Incorrect Credentials') // This will be error message displayed in login form
      },
    }),
  ],
  callbacks: {
    // called after sucessful signin
    jwt: async ({ token, user }) => {
      console.log('user', user);
      console.log('token', token);
      
      if (user) token.id = user.token
      return token
    }, // called whenever session is checked
    session: async ({ session, token, user }) => {
      if (token) 
         session.id = token.id,
         session.data = token.data
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