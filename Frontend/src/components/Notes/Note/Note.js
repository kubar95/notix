import React,
{ useState, useEffect, useImperativeHandle, forwardRef } from 'react';

import draftToHtml from 'draftjs-to-html';
import { EditorState, convertToRaw, ContentState, convertFromHTML } from 'draft-js';
import { Editor } from 'react-draft-wysiwyg';
import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css';

const Note = forwardRef((props, ref) => {
    const [editorState, setEditorState] = useState(
        EditorState.createEmpty(),
    );
    useEffect(() => {

        setEditorStateFromPropsHtml(setEditorState);
    }, [])
    useImperativeHandle(ref, () => ({
        getNote() {
            const newNote = {
                ...props.note,
                content: getHtmlValeFromEditor()
            }
            return newNote
        }
    }))
    function setEditorStateFromPropsHtml() {
        if (props.note) {
            const blocksFromHtml = convertFromHTML(props.note.content);
            const { contentBlocks, entityMap } = blocksFromHtml;
            const contentState = ContentState.createFromBlockArray(contentBlocks, entityMap);
            const createdEditorState = EditorState.createWithContent(contentState);
            setEditorState(EditorState.moveFocusToEnd(createdEditorState));
        }

    }


    const getHtmlValeFromEditor = () => draftToHtml(convertToRaw(editorState.getCurrentContent()))
    const handleEditorStateChange = (editorState) => setEditorState(editorState)

    return <Editor
        wrapperClassName="note__editor-wrapper"
        editorClassName="note__editor"
        editorState={editorState}
        onEditorStateChange={handleEditorStateChange}
        toolbar={{
            options: ['inline', 'blockType', 'fontSize', 'fontFamily', 'list', 'textAlign', 'link', 'emoji', 'history'],
        }} />;
})

export default Note;

