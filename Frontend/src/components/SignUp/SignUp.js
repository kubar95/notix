import React, { Fragment, useState } from 'react';

import { signUpLabels } from '../../constants/labels';
import * as validator from '../../validators/validateUserDetails'
import { userApi } from '../../constants/api';

import Axios from 'axios';
import { TextField, FormHelperText } from '@material-ui/core';
import Button from '@material-ui/core/Button';

const SignUp = ({ onModeChange }) => {
    const [values, setValues] = useState({
        username: "",
        password: "",
        passwordRepeat: "",
        email: ""
    })
    const [errors, setErrors] = useState({
        username: false,
        password: false,
        passwordRepeat: false,
        email: false,
        responseError: false
    })

    const inputHandler = (event, hasErrorField = false) => {
        const inputType = event.target.id
        setValues({
            ...values,
            [inputType]: event.target.value
        })
        if (hasErrorField)
            setErrors({
                ...errors,
                [inputType]: false
            })
    }
    const setErrorForValue = (value) => {
        setErrors({
            ...errors,
            [value]: true
        })
    }
    const handlePasswordChange = (event) => {
        inputHandler(event, true)
        if (!validator.validatePassword(event.target.value)) {
            setErrorForValue(event.target.id)
        }
    }
    const handlePasswordRepeatChange = (event) => {
        inputHandler(event, true)
        if (!validator.validatePasswordRepeat(values.password, event.target.value)) {
            setErrorForValue(event.target.id)
        }
    }
    const handleEmailChange = (event) => {
        inputHandler(event, true)
        if (!validator.validateEmail(event.target.value)) {
            setErrorForValue(event.target.id)
        }
    }
    const handleResponseError = (error) => {
        if (error.response.data.status === 409) {
            setErrorForValue('responseError')
        }

    }
    const onSignUp = (event) => {
        event.preventDefault()
        const sendingData = {
            username: values.username,
            password: values.password,
            email: values.email
        }
        Axios.post(userApi.signUp, sendingData)
            .then(() => onModeChange())
            .catch(handleResponseError)
    }

    const isEveryValue = () => {
        for (const value in values) {
            if (values[value] === "")
                return false
        }
        return true
    }
    const areNoErrors = () => {
        for (const error in errors) {
            if (errors[error] === true)
                return false
        }
        return true
    }

    const shouldDisableSubmit = !(isEveryValue() && areNoErrors())
    return (
        <Fragment>
            <form className="login__form login-form" onSubmit={onSignUp}>
                <TextField
                    id="username"
                    onChange={inputHandler}
                    margin="normal"
                    value={values.username}
                    fullWidth={true}
                    label={signUpLabels.username}
                    error={errors.username}
                    variant="outlined"
                />
                <TextField
                    id="password"
                    type="password"
                    onChange={handlePasswordChange}
                    margin="normal"
                    fullWidth={true}
                    value={values.password}
                    label={signUpLabels.password}
                    error={errors.password}
                    helperText={signUpLabels.passwordError}
                    variant="outlined"
                />
                <TextField
                    id="passwordRepeat"
                    type="password"
                    onChange={handlePasswordRepeatChange}
                    margin="normal"
                    fullWidth={true}
                    value={values.passwordRepeat}
                    label={signUpLabels.passwordRepeat}
                    error={errors.passwordRepeat}
                    helperText={errors.passwordRepeat ? signUpLabels.passwordRepeatError : ''}
                    variant="outlined"
                />
                <TextField
                    onChange={handleEmailChange}
                    id="email"
                    margin="normal"
                    value={values.email}
                    fullWidth={true}
                    label={signUpLabels.email}
                    error={errors.email}
                    helperText={errors.email ? signUpLabels.emailError : ''}
                    variant="outlined"
                />
                <Button className="login__form-btn" variant="contained" type="submit" color="primary" fullWidth={true} disabled={shouldDisableSubmit}>Zarejestruj się</Button>
                {errors.responseError ?
                    <FormHelperText error> {signUpLabels.responseError}</FormHelperText> : null}
            </form>
            <div className="login__change-view">
                <button className="login__change-view-btn"
                    onClick={onModeChange}
                > Masz już konto? </button>
            </div>
        </Fragment>);
}

export default SignUp;