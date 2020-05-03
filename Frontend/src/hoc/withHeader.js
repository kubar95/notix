import React from 'react';
import Header from '../components/Header/Header';


const withHeader = (WrappedComponent, isLightMode = false, showLinkToHeader = false) => {
    return (props) => (
        <>
            <Header lightMode={isLightMode} showLink={showLinkToHeader} />
            <WrappedComponent {...props} />
        </>);
}

export default withHeader;