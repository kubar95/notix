import React, { useState, useEffect } from 'react';
import { Redirect } from 'react-router-dom'

const withErrorHandler = (WrappedComponent, axios) => {
    return props => {
        const [errorStatus, setErrorStatus] = useState(null);

        const reqInterceptor = axios.interceptors.request.use(req => {
            setErrorStatus(null);
            return req;
        });
        const resInterceptor = axios.interceptors.response.use(
            null,
            err => {
                if (err.response) {
                    setErrorStatus(err.response.status)
                }
                return Promise.reject(err)
            }
        );
        useEffect(() => {
            return () => {
                axios.interceptors.request.eject(reqInterceptor);
                axios.interceptors.response.eject(resInterceptor);
            };
        }, [reqInterceptor, resInterceptor]);
        const contentToDisplay = errorStatus ? <Redirect to={{
            pathname: "/blad",
            state: errorStatus
        }} /> : <WrappedComponent {...props} />
        return (
            contentToDisplay

        );
    };
};

export default withErrorHandler;