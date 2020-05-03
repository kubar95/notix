import React from 'react';

import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelActions from '@material-ui/core/ExpansionPanelActions';
import Divider from '@material-ui/core/Divider'
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

const NoteExpansionPanel = ({ expanded, onChange, expandIcon = true, title, date, note, buttons }) => {
    return (<ExpansionPanel
        TransitionProps={{ unmountOnExit: true }}
        onChange={onChange}
        expanded={expanded}
        className="notes__item note">
        <ExpansionPanelSummary
            className="note__header"
            expandIcon={expandIcon ? <ExpandMoreIcon /> : null}
            aria-controls="panel1c-content"
        >
            <div className="note__header-content">
                <div className="note__header-title"> {title} </div>
                {date}
            </div>

        </ExpansionPanelSummary>
        <ExpansionPanelDetails >
            {note}
        </ExpansionPanelDetails>
        <Divider />
        <ExpansionPanelActions>
            {buttons}
        </ExpansionPanelActions>
    </ExpansionPanel>);
}

export default NoteExpansionPanel;