import React from 'react';
import NotesItem from "./NotesItem";
import {fetchNotesForCandidate} from "../utils/api";
import { BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import '../less/candidateNotesTable.less';
import AddNoteModal from "../containers/AddNoteModal";
/**
 * Component used to display all the notes of the selected candidate
 * by accessing the Notes tab. Notes List is built from a a list of NotesItems.
 */
export default class NotesList extends React.Component {

    constructor(props) {
        super(props);
        let candidateA = props.candidate;
        candidateA.candidateNotesList=[];
        this.state = {
            candidate:candidateA
        };
        this.options = {
            insertBtn: this.addCandidateNoteButton,
            paginationPosition: 'bottom',
            searchField: this.CustomSearchField,
            expandBy: 'column'
        };
    }

    componentDidMount() {
        fetchNotesForCandidate(this.props.notesUrl).then(data => {
            //console.log("data",data);
            let candidateA = this.state.candidate;
            candidateA.candidateNotesList=data;
            this.setState({candidate: candidateA});
        });
    }


    render2() {
        //iterate over each data item and encapsulate the data into an NoteItem component
        return (
        <table>
            <tr>
                <th>Date</th><th>Status</th><th>Note</th></tr>
           { this.state.data.map((item, index) => {
                return (
                <NotesItem key={index} candidate ={this.state.candidate} status={item.status} note={item.note} date={item.date}/>)
            })}
        </table>

        )
    }
    addCandidateNoteButton = () => {
        return (
            <AddNoteModal candidate={this.state.candidate} existingCandidateNotes={this.state.data}/>


        );
    };
//<a href="addCandidateNote" className="btn-lg candidateNotesCustomButton" role="button">Add Note</a>
    render(){
        return (
            <BootstrapTable tableBodyClass='candidateNotesTableBodyClass' tableHeaderClass='candidateNotesTableHeaderClass' bordered={false} hover={true} striped={true}
                            data={this.state.candidate.candidateNotesList}  options={this.options} insertRow >

                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center',} }
                                   dataField='date' isKey={true}>Date</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center',} }
                                   dataField='status'>Status</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center',} }
                                   dataField='note' >Note</TableHeaderColumn>

            </BootstrapTable>
        );
    }
}
