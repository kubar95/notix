import ThemeProvider from '@material-ui/styles/ThemeProvider';
import TextField from '@material-ui/core/TextField';
import { theme } from '../../../themes/materialUiTheme';
import PropTypes from 'prop-types';

import React from 'react';



const Input = (props) => {
    const { value, handleChange, label, variant = 'outlined' } = props;
    return (
        <ThemeProvider theme={theme}>
            <TextField
                margin="dense"
                value={value}
                onChange={handleChange}
                label={label}
                type="text"
                variant={variant}
                fullWidth
                {...props}

            />
        </ThemeProvider>
    );
}

Input.PropType = {
    value: PropTypes.string,
    handleChange: PropTypes.func,
    label: PropTypes.string,
    variant: PropTypes.string,
}

export default Input;