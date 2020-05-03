import React from 'react';

import './Subject.scss';

import withHeader from '../../hoc/withHeader';
import SubjectContent from '../../components/SubjectContent/SubjectContent';


const Subject = (props) => {
    const subjectId = props.match.params.id;
    return (
        <div className="subject-container">
            <SubjectContent subjectId={subjectId} />
        </div>
    );
}

export default withHeader(Subject, true, true);