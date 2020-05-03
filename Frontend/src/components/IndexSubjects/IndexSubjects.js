import React, { useEffect, useState } from 'react';
import AddIcon from '@material-ui/icons/Add';
import axios from 'axios';
import SubjectsModal from './SubjectsDialog/SubjectsDialog';


import { subjectsApi } from '../../constants/api';
import withErrorHandler from '../../hoc/withErrorHandler';
import Delay from '../Delay/Delay'



const IndexSubject = (props) => {
    const [subjects, setSubjects] = useState([]);
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        getSubjects()
    }, []);
    function getSubjects() {
        axios.get(subjectsApi.subjects, { withCredentials: true })
            .then((response) => setSubjects(response.data))
            .catch((err) => console.log(err))
    }
    const toggleModal = () => setShowModal(!showModal);
    const subjectsMap = subjects.map(dataItem => {
        return <a key={dataItem.name} className="index__subjects-item" href={'przedmiot/' + dataItem.id}>{dataItem.name}</a>
    })

    const emptyMapInfo = <Delay delayTimeout={1000}>
        <div className="index__subjects-item">Nie masz jeszcze dodanych przedmiotów</div>
    </Delay>

    const subjectItems = subjects.length > 0 ?
        subjectsMap : emptyMapInfo
    const addSubject = (subjectName) => {
        const dataToSend = { name: subjectName }
        axios.post(subjectsApi.subjects, dataToSend)
            .then(() => {
                toggleModal()
                getSubjects()
            })
    }
    return (
        <section className="index__subjects">
            <SubjectsModal
                open={showModal}
                onClose={toggleModal}
                onSubmit={addSubject}
            />
            <h1 className="index__header">Przedmioty</h1>
            <div className="index__subjects-items">
                <AddIcon
                    onClick={toggleModal}
                    className="index__subjects-add" />
                {subjectItems}
            </div>

        </section>);
}

export default withErrorHandler(IndexSubject, axios);