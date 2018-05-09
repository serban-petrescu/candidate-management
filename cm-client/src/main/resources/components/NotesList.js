import React from 'react';
import {fetchNotesForCandidate} from "../utils/CandidateAPI";
import { BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import '../less/candidateNotesTable.less';
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import AddNoteModal from "../containers/AddNoteModal";
/**
 * Component used to display all the notes of the selected candidate
 * by accessing the Notes tab. Notes List is built from a a list of NotesItems.
 */
class NotesList extends React.Component {

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
        this.loadNodes();
    }

    loadNodes = () => {
        fetchNotesForCandidate(this.props.notesUrl).then(data => {
            let candidateA = this.state.candidate;
            candidateA.candidateNotesList=data;
            this.setState({candidate: candidateA});
        });
    };

    addCandidateNoteButton = () => {
        return (
            <AddNoteModal candidate={this.state.candidate} existingCandidateNotes={this.state.data} onAdd={() => this.loadNodes()}/>
        );
    };

    render(){
        return (
            <BootstrapTable tableBodyClass='candidateNotesTableBodyClass' tableHeaderClass='candidateNotesTableHeaderClass' bordered={false} hover={true} striped={true}
                            data={this.state.candidate.candidateNotesList}  options={this.options} insertRow >

                <TableHeaderColumn dataField='date' isKey={true}>Date</TableHeaderColumn>
                <TableHeaderColumn dataField='status'>Status</TableHeaderColumn>
                <TableHeaderColumn dataField='note' >Note</TableHeaderColumn>

            </BootstrapTable>
        );
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({fetchNotesForCandidate: fetchNotesForCandidate}, dispatch);
}

export default connect(null, mapDispatchToProps) (NotesList);
