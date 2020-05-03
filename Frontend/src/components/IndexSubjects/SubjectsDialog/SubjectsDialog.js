import React, { useState } from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';

import Input from '../../UI/Input/Input';
import './SubjectDialog.scss'

import { dialogLabels } from '../../../constants/labels';


const SubjectsModal = (props) => {
    const [subject, setSubject] = useState('');
    const handleTextFieldChange = (event) => {
        setSubject(event.target.value)
    }
    const handleSubmit = () => {
        props.onSubmit(subject);
    }
    return (
        <Dialog
            open={props.open}
            aria-labelledby="form-dialog-subject"
            onBackdropClick={props.onClose} >
            <DialogTitle id="form-dialog-subject">Dodaj nowy przedmiot</DialogTitle>
            <DialogContent>
                <Input
                    value={subject}
                    handleChange={handleTextFieldChange}
                    label={dialogLabels.subject}
                    autoFocus
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={props.onClose} color="primary">
                    Anuluj
          </Button>
                <Button onClick={handleSubmit} color="primary">
                    Dodaj
          </Button>
            </DialogActions>
        </Dialog>

    );
}

export default SubjectsModal;