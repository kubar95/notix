import React, { useRef, useState, Fragment } from 'react';
import './Notes.scss';

import axios from 'axios';
import Moment from 'react-moment';
import Button from '@material-ui/core/Button';

import Note from './Note/Note';
import withErrorHandler from '../../hoc/withErrorHandler';
import NewNote from './NewNote/NewNote';
import NoteExpansionPanel from './NoteExpansionPanel/NoteExpansionPanel';

const initialNewNote = {
    show: false,
    title: '',
    date: new Date()
}

const Notes = ({ notes, subjectUrl, openNoteId, onOpenNoteChange, onUpdateNotes }) => {


    const [newNote, setNewNote] = useState(initialNewNote)
    const noteRef = useRef();
    const url = subjectUrl + "/note";

    const handleSave = () => {
        const updatedNote = noteRef.current.getNote();

        axios.put(url, updatedNote)
            .then(() => onUpdateNotes())
            .catch(err => console.log(err))

    }
    const handleNewNoteSave = () => {
        const noteToSave = {
            title: newNote.title,
            date: newNote.date,
            content: noteRef.current.getNote().content
        }

        axios.put(url, noteToSave)
            .then((response) => updateNotesAndSetOpenNoteToSaved(response.data))
            .catch(err => console.log(err))
    }
    const updateNotesAndSetOpenNoteToSaved = (data) => {
        setNewNote(initialNewNote)
        if (data.id) {
            onOpenNoteChange(data.id)
        }
        onUpdateNotes()
    }

    const handlePanelChange = panelId => (e, expanded) => {
        onOpenNoteChange(expanded ? panelId : '')
    }

    const handleNewNoteDateChange = (date, val) => {
        setNewNote({
            ...newNote,
            date
        })
    }
    const handleNewNoteTitleChange = event => {
        setNewNote({
            ...newNote,
            title: event.target.value
        })
    }

    const toggleShowNewNote = () => {
        if (!newNote.show) {
            onOpenNoteChange('')
            setNewNote({
                ...newNote,
                show: !newNote.show
            })
        }
        else {
            setNewNote(initialNewNote)
        }
    }

    const renderNoteDate = (note) => note.date ? <Moment format='DD/MM/YYYY' date={note.date}></Moment> : null

    const renderButtons = (note) => {
        return <Fragment>
            <Button size="small" color="secondary">Usuń</Button>
            <Button size="small" color="primary" onClick={() => handleSave(note.id)}>
                Zapisz
            </Button>
        </Fragment>
    }
    const notesAccordion = notes ? notes.map((note) => {
        return <NoteExpansionPanel
            key={note.id}
            expanded={openNoteId === note.id}
            onChange={handlePanelChange(note.id)}
            title={note.title}
            date={renderNoteDate(note)}
            note={<Note note={note} ref={noteRef} />}
            buttons={renderButtons(note)}
        />
    }) : null;
    return (
        <div className="notes">
            <Button
                variant="contained"
                color="primary"
                onClick={toggleShowNewNote}
            >Dodaj notatke</Button>
            {notesAccordion}
            {newNote.show ? <NewNote
                note={<Note note='' ref={noteRef} />}
                toggle={toggleShowNewNote}
                date={newNote.date}
                title={newNote.title}
                onTitleChange={handleNewNoteTitleChange}
                onDateChange={handleNewNoteDateChange}
                onSave={handleNewNoteSave}
            /> : null}
        </div>);
}


export default withErrorHandler(Notes, axios);