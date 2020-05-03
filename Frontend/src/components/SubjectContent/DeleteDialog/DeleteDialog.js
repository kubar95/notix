import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogTitle from '@material-ui/core/DialogTitle';

const DeleteDialog = (props) => {
    return (
        <Dialog
            open={props.show}
            onClose={props.handleClose}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
        >
            <DialogTitle id="alert-dialog-title">{"Czy napewno chcesz usunać przedmiot?"}</DialogTitle>
            <DialogActions>
                <Button onClick={props.handleClose} color="primary">
                    Anuluj
          </Button>
                <Button onClick={props.handleDelete} color="primary" autoFocus>
                    Usuń
          </Button>
            </DialogActions>
        </Dialog>);
}

export default DeleteDialog;