import React, { useState, Fragment } from 'react';

import './Login.scss';


import SignIn from '../../components/SignIn/SignIn';
import SignUp from '../../components/SignUp/SignUp';



const Login = (props) => {
    const [signInMode, setSignInMode] = useState(true);
    // const lastLocation = useLastLocation()
    // useEffect(() => {
    //     if (lastLocation && isNotLoginOrRootPath(lastLocation)) {
    //         props.onSetRedirect(lastLocation.pathname)
    //     }
    // }, [])

    // const modeClickHandler = (event) => {
    //     event.preventDefault()
    //     setState({
    //         ...state,
    //         resetMode: !state.resetMode
    //     })
    // }

    const handleSignInModeChange = () => setSignInMode(!signInMode)

    return (
        <Fragment>
            <div className="login-wrapper">
                <div className="login">
                    <div className="login__container">
                        <div className="login__logo"></div>
                        {signInMode ?
                            <SignIn onModeChange={handleSignInModeChange} /> :
                            <SignUp onModeChange={handleSignInModeChange} />
                        }
                    </div>
                </div>
            </div>
        </Fragment>
    )
}

export default Login;