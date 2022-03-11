

export async function ApiLogin() {
     const reqBody = { usernameOrEmail : "margarita@gmail.com", password : "1234" }

        //const getUserLoginInfo = async ()  =>{
        //const reqBody = { usernameOrEmail : "margarita@gmail.com", password : "1234" }
        //console.log('reqBody .... ' , reqBody);
        const response = await fetch('http://localhost:8086/api/auth/signin', {
           method: 'POST',
            body: JSON.stringify(reqBody),
            headers: {
               'Content-Type' : 'application/json'
           },
           // mode: 'no-cors',
        })
        const data = await response.json();
        console.log('POSTXXX: ', data);
        //  return data;
   // }
     
   // console.log('getUserLoginInfo: ', getUserLoginInfo);
  return data;
}
