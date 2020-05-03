import React from 'react';
import CalendarTodayIcon from '@material-ui/icons/CalendarToday';
import Moment from 'react-moment';

import './EventCard.scss'

const EventCard = (props) => {
    const eventDate = props.start ?
        <div className="event-card__date">
            <CalendarTodayIcon />
            <Moment format='DD/MM/YYYY' date={props.start}></Moment>
        </div> : null
    return (
        <div onClick={props.onCardClick} className="event-card">
            <div className="event-card__title">{props.name}</div>
            {eventDate}
        </div>);
}

export default EventCard;