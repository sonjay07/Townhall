import '../styles/townhall-1.0.1.css';

/*
function MyApp({ Component, pageProps }) {
  <SessionProvider session={session}>
      return <Component {...pageProps} />
  </SessionProvider>

}
export default MyApp
*/



import { SessionProvider, useSession } from 'next-auth/react'
import { useEffect } from 'react'
import { useRouter } from 'next/router'

export default function MyApp({ Component, pageProps: pageProps }) {
  return (
    <SessionProvider session={pageProps.session}>
      {Component.auth ? (
        <Auth>
          <Component {...pageProps} />
        </Auth>
      ) : (
        <Component {...pageProps} />
      )}
    </SessionProvider>
  )
}

function Auth({ children }) {
  const router = useRouter()
  const { data: session, status, token } = useSession()
  const isUser = !!session?.user
  useEffect(() => {
    if (status === 'loading') return // Do nothing while loading
    if (!isUser) router.push('/auth') //Redirect to login
  }, [isUser, status])

  if (isUser) {
    return children
  }
  // Session is being fetched, or no user.
  // If no user, useEffect() will redirect.
  return <div>Loading...</div>
}