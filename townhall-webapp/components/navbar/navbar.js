import { signOut, useSession } from 'next-auth/react'

const Navbar = () => {
  const { data: session } = useSession()
  return (
    <div
     
    >
      <a>{session.user.name}</a>
       <br />
      <span>token:  {session.id}</span>
      <br />
      <span>email:  {session.user.email}</span>
      <br />
      <span>id:  {session.user.id}</span>
      <br />
      <span>data:  {session.data}</span>
      &nbsp;
      <button
        onClick={() => {
          signOut({ redirect: false })
        }}
      >
        Signout
      </button>
    </div>
  )
}
export default Navbar