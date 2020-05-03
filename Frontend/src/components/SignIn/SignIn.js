import React, { useState } from 'react';

import { signInLabels } from '../../constants/labels';
import { userApi } from '../../constants/api';

import Axios from 'axios';
import { useCookies, } from 'react-cookie';

import TextField from '@material-ui/core/TextField';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputAdornment from '@material-ui/core/InputAdornment';
import IconButton from '@material-ui/core/IconButton';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import Button from '@material-ui/core/Button';
import Input from '../UI/Input/Input';


const SignIn = ({ onModeChange }) => {
  const [showPassword, setShowPassword] = useState(false);
  const [values, setValues] = useState({
    username: '',
    password: ''
  })
  const [error, setError] = useState(false);
  const setCookie = useCookies()[1];

  const getLabelWidth = () => {
    return signInLabels.password.length * 9;
  }

  const inputHandler = (event) => {
    const inputType = event.target.id
    setValues({
      ...values,
      [inputType]: event.target.value
    })
  }

  const handleSignIn = (event) => {
    event.preventDefault()
    const sendingData = {
      username: values.username,
      password: values.password
    }
    Axios.post(userApi.signIn, sendingData)
      .then(response => setAuthorizationCookie(response.data))
      .catch(() => setError(true))
  }
  const setAuthorizationCookie = (data) => {
    const cookieValue = data.tokenType + data.token;
    const cookieExpiration = new Date(data.expiration);
    setCookie('Authorization', cookieValue, { expires: cookieExpiration, path: '/' })
  }

  return (
    <>
      <form className="login__form" onSubmit={handleSignIn}>

        <Input
          onChange={inputHandler}
          id="username"
          value={values.username}
          label={signInLabels.username}
          error={error}
          helperText={error ? signInLabels.error : ''}
        />

        <FormControl fullWidth={true} variant="outlined" margin="normal">
          <InputLabel htmlFor="password" error={error}>{signInLabels.password}</InputLabel>
          <OutlinedInput
            id="password"
            type={showPassword ? 'text' : 'password'}
            onChange={inputHandler}
            value={values.password}
            error={error}
            labelWidth={getLabelWidth()}
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  aria-label="toggle password visibility"
                  onClick={() => setShowPassword(!showPassword)}
                  onMouseDown={(event) => event.preventDefault()}
                  edge="end"
                >
                  {showPassword ? <Visibility /> : <VisibilityOff />}
                </IconButton>
              </InputAdornment>
            }

          />
        </FormControl>
        <Button variant="contained"
          className="login__form-btn"
          type="submit"
          color="primary"
          fullWidth={true}
        >
          Zaloguj się
          </Button>
      </form>
      <div className="login__change-view">
        <button className="login__change-view-btn" onClick={onModeChange}> Nie masz konta? </button>
      </div>

    </>);

}
export default SignIn;