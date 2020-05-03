import React, { useEffect, useState, Fragment } from 'react';
import axios from 'axios';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import { useHistory } from 'react-router-dom';
import { subjectsApi } from '../../constants/api';
import withErrorHandler from '../../hoc/withErrorHandler';
import DeleteDialog from './DeleteDialog/DeleteDialog';
import Notes from '../Notes/Notes';



const SubjectContent = (props) => {
    const history = useHistory();
    const [subject, setSubject] = useState(null);
    const [showDeleteDialog, setShowDeleteDialog] = useState(false)
    const [openNoteId, setOpenNoteId] = useState(null)
    const notes = subject ? subject.notes : null;
    const url = subjectsApi.subjects + '/' + props.subjectId;

    useEffect(() => {
        getSubject();
    }, [])

    function getSubject() {
        axios.get(url, { withCredentials: true })
            .then((resposne) => setSubject(resposne.data))
            .catch((err) => console.log(err));
    }
    const deleteSubject = () => {
        axios.delete(url, { withCredentials: true })
            .then(() => history.push('/'))
            .catch(err => console.log(err))
    }

    const handleOpenNote = (noteId) => setOpenNoteId(noteId)

    const toggleDialog = () => setShowDeleteDialog(!showDeleteDialog);



    return (
        <Fragment >
            <DeleteDialog show={showDeleteDialog} handleClose={toggleDialog} handleDelete={deleteSubject} />
            <div className="subject">
                <div className="subject__header">
                    <h1 className="subject__header-text">{subject ? subject.name : null}</h1>
                    <DeleteForeverIcon
                        className="subject__header-icon"
                        onClick={toggleDialog} />
                </div>
                <Notes
                    notes={notes}
                    subjectUrl={url}
                    openNoteId={openNoteId}
                    onOpenNoteChange={handleOpenNote}
                    onUpdateNotes={getSubject} />
            </div>
        </Fragment>);
}

export default withErrorHandler(SubjectContent, axios);