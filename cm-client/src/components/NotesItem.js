import React from 'react';
import {fetchNotesForCandidate} from "../utils/api";
/**
 * Component used to display all the notes data of the selected candidate
 * by accessing the Notes tab.
 */
export default class NotesItem extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            date: props.date,
            status: props.status,
            note:props.note};
    }

    render() {

        return (
        <tr>
            <td>{this.state.date}</td><td>{this.state.status}</td><td>{this.state.note}</td></tr>

        )
    }
}