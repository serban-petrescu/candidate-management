import React from 'react';
import {fetchNotesForCandidate} from "../utils/CandidateAPI";
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
            let candidateA = this.state.candidate;
            candidateA.candidateNotesList=data;
            this.setState({candidate: candidateA});
        });
    }

    addCandidateNoteButton = () => {
        return (
            <AddNoteModal candidate={this.state.candidate} existingCandidateNotes={this.state.data}/>
        );
    };

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
