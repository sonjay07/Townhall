import Navbar from '../components/navbar/navbar'

export default function Dashboard() {
  return (
    <>
      <Navbar />
      <h1>secret dashboard</h1>
    </>
  )
}

Dashboard.auth = true