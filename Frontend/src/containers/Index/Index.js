import React from 'react';
import withHeader from '../../hoc/withHeader';
import IndexSubjects from '../../components/IndexSubjects/IndexSubjects';
import IndexEvents from '../../components/IndexEvents/IndexEvents';

import './Index.scss';

const Index = (props) => {
    return (
        <div className="index">
            <IndexSubjects />
            <IndexEvents />
        </div>);
}

export default withHeader(Index);