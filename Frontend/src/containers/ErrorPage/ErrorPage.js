import React from 'react';

import './ErrorPage.scss'

import withHeader from '../../hoc/withHeader';
import errorMessages from '../../constants/errors';

const ErrorPage = (props) => {
    let errorCode = 404
    if (props.location.state) {
        if (props.location.state === 401) {
            errorCode = 401
        }
    }
    let errorMessage = errorMessages[errorCode];
    return (
        <div className="error-page">
            <h1 className="error-page__code">{errorCode}</h1>
            <h2 className="error-page__message">{errorMessage}</h2>
        </div>
    );
}

export default withHeader(ErrorPage, true, true);
