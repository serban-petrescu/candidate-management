import React from 'react';
import {SearchField} from 'react-bootstrap-table';
import {Tab, Tabs} from 'react-bootstrap';
import EditCandidate from './EditCandidate';
import ConfirmationDialog from './ConfirmationDialog';
import '../less/candidateTable.less';
import "../less/roboto.less";
import SkillsList from '../components/SkillsList'
import {connect} from 'react-redux';
import {loadCandidates, selectCandidate} from '../actions/CandidateActions';
import {bindActionCreators} from 'redux';
import EducationList from "../components/EducationList";
import NotesList from "../components/NotesList";
import MainCandidatesTable from "../components/MainCandidatesTable";
import {Columns} from "../utils/Column";

/**
 * Main Component containing the table where all the Candidates will be viewed and
 * processed.
 */
class CandidatesTable extends MainCandidatesTable {

    constructor(props) {
        // State does not contain candidate because they are kept in the global state
        super(props);

        this.columnsConfig.addColumn(Columns.createColumnWithOptions("id", "Actions", {
            dataFormat: this.actionsFormatter,
            expandable: false
        }));
    };

    getTableOptions() {
        let props = super.getTableOptions();

        props = {
            ...props,
            exportCSV: true,
            expandComponent: this.expandCandidateDetails,
            insertRow: true,
            expandableRow: () => {
                return true;
            }
        };

        props.options = {
            ...props.options,
            insertBtn: this.addCandidateButton,
            exportCSVBtn: this.CustomExportCSVButton,
            deleteBtn: this.importButton,
            searchField: this.CustomSearchField,
            expandBy: 'column'
        };


        return props;
    }

    /**
     * In the Actions tab of the table we have to wrap the candidate info and pass it down to
     * two other components: Edit and delete.
     * @param cell
     * @param row
     * @returns {XML}
     */
    actionsFormatter = (cell, row) => {
        return (<div style={{display: "inline"}} onClick={() => this.props.selectCandidate(row)}>
            <EditCandidate candidate={row}/>
            <ConfirmationDialog/>
        </div>);
    };

    componentDidMount() {
        this.props.loadCandidates();
    };

    addCandidateButton = () => {
        return (
            <a href="addCandidate" className="btn-lg candidateCustomButton" role="button" style={ {marginRight: 25}}>Add
                Candidate</a>
        );
    };

    importButton = () => {
        return (
            <a href="import" className="btn-lg candidateCustomButton" role="button">Import CSV</a>
        );
    };

    CustomSearchField = () => {
        return (
            <SearchField className="form-control" placeholder='Search ...'/>);

    };

    CustomExportCSVButton = (onClick) => {
        return (

            <a className="btn-lg candidateCustomButton" role="button" onClick={onClick} style={{marginRight: 25}}>Export
                CSV</a>

        );
    };
    /**
     * Called when a user clicks on a row. For each row, three tabs will be displayed one containing
     * the Skill, one containing the Education list and one Candidates Notes.
     * @param row containing candidate information. row is actually a candidate
     * @returns {JSX}
     */
    expandCandidateDetails = (row) => {
        return (
            <Tabs id="controlled-tab-example">
                <Tab eventKey={1} title="Skills"><SkillsList skillsUrl={row._links.candidateSkillsList.href}/></Tab>
                <Tab eventKey={2} title="Education"> <EducationList
                    educationLink={row._links.education.href}/></Tab>
                <Tab eventKey={3} title="Notes"><NotesList notesUrl={row._links.candidateNotesList.href}
                                                           candidate={row}/></Tab>
            </Tabs>
        )
    };
}

function mapStateToProps(state) {
    return {
        candidates: state.candidates
    };
}

// Anything returned from this function will end up as props
// on the ConfirmationDialog component
function mapDispatchToProps(dispatch) {
    // whenever deleteCandidate is called, the result should be passed
    // to all our reducers
    return bindActionCreators({selectCandidate: selectCandidate, loadCandidates: loadCandidates}, dispatch);
}


export default connect(mapStateToProps, mapDispatchToProps)(CandidatesTable);