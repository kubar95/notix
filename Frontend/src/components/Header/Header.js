import React from 'react';
import { useCookies } from 'react-cookie';
import { Link, useHistory } from 'react-router-dom';
import PersonIcon from '@material-ui/icons/Person';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';

import './Header.scss';

const Header = ({ lightMode, showLink }) => {
    const removeCookie = useCookies()[2];
    const history = useHistory();

    const handleSignOut = () => {

        history.push('/');
        removeCookie('Authorization', { path: '/' })
    }
    const iconClasses = "header__icon" + (lightMode ? " header__icon--light" : "");
    return (

        <header className="header">
            <div className="header__right">
                <PersonIcon className={iconClasses} fontSize="large" />
                <ExitToAppIcon className={iconClasses} fontSize="large" onClick={handleSignOut} />
            </div>
            {showLink ?
                <Link className="header__left" to="/">
                    <ArrowBackIcon className={iconClasses} fontSize="large" />
                    <div className={iconClasses}>Strona główna</div>
                </Link> : null}
        </header>

    );
}

export default Header;