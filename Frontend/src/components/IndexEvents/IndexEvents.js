import React, { useState, useEffect } from 'react';
import axios from 'axios';
import EventCard from './EventCard/EventCard';

import withErrorHandler from '../../hoc/withErrorHandler';
import Delay from '../Delay/Delay';

const IndexEvents = (props) => {
    const [events, setEvents] = useState([]);
    useEffect(() => {
        const source = axios.CancelToken.source();
        axios.get('/api/events', {
            withCredentials: true,
            cancelToken: source.token
        })
            .then((response) => setEvents(response.data))
            .catch((err) => console.log(err))

        return () => {
            source.cancel();
        };
    }, []);
    const emptyMapInfo = <Delay delayTimeout={1000}>
        <EventCard name={"Nie masz jeszcze dodanych wydarzeń"} />
    </Delay>

    const eventsMap = events.map((dataItem, index) => {
        return <EventCard key={index} name={dataItem.title} start={dataItem.startDate}></EventCard>
    })
    const eventItems = events.length > 0 ?
        eventsMap : emptyMapInfo
    return (
        <section className="index__events">
            <h1 className="index__header">Kalendarz</h1>
            <div className="index__events-items">{eventItems}</div>
        </section>);
}

export default withErrorHandler(IndexEvents, axios);