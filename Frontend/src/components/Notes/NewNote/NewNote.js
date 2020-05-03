import React, { Fragment } from 'react';

import DateFnsUtils from '@date-io/date-fns';
import NoteExpansionPanel from '../NoteExpansionPanel/NoteExpansionPanel';

import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
} from '@material-ui/pickers';
import Button from '@material-ui/core/Button';


import { noteLabels } from '../../../constants/labels';
import Input from '../../UI/Input/Input';

const NewNote = (props) => {


    const datePicker = <MuiPickersUtilsProvider utils={DateFnsUtils}>
        <KeyboardDatePicker
            autoOk
            disableToolbar
            variant="inline"
            format="dd/MM/yyyy"
            margin="normal"
            label={noteLabels.date}
            value={props.date}
            onChange={date => props.onDateChange(date)}
        />
    </MuiPickersUtilsProvider>

    const titleInput = <Input
        id="standard-basic"
        label={noteLabels.title}
        value={props.title}
        onChange={props.onTitleChange}
        variant='standard'
    />


    const buttons =
        <Fragment>
            <Button size="small" color="secondary" onClick={props.toggle}> Anuluj </Button>
            <Button
                size="small"
                color="primary"
                onClick={props.onSave}
                disabled={props.title ? false : true}> Zapisz
        </Button>
        </Fragment>


    return (
        <NoteExpansionPanel
            expanded={true}
            expandIcon={false}
            title={titleInput}
            date={datePicker}
            note={props.note}
            buttons={buttons}
        />
    )

}

export default NewNote;