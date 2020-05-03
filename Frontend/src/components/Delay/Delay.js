import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';

const Delay = (props) => {
    const [isVisible, setIsVisible] = useState(false)
    useEffect(() => {
        setTimeout(() => setIsVisible(true), props.delayTimeout)
    }, [])
    return (
        <>
            {isVisible ? props.children : null}
        </>
    );
}

Delay.PropTypee = {
    delayTimeout: PropTypes.number
}




export default Delay;