import React from 'react';
import {SearchField, BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import {Tab, Tabs} from 'react-bootstrap';
import {sortByLastName, sortByFirstName} from '../utils/sorting-comparators';
import EditCandidate from './EditCandidate';
import ConfirmationDialog from './ConfirmationDialog';
import '../less/candidateTable.less';
import "../less/roboto.less";
import SkillsList from '../components/SkillsList'
import {connect} from 'react-redux';
import {selectCandidate, loadCandidates} from '../actions/index';
import {bindActionCreators} from 'redux';
import EducationList from "../components/EducationList";
import NotesList from "../components/NotesList";


/**
 * Main Component containing the table where all the Candidates will be viewed and
 * processed.
 */
class CandidatesTable extends React.Component {

    constructor(props) {
        // State does not contain candidate because they are kept in the global state
        super(props);

        // We have 4 custom components
        // using the deleteBtn as an workaround for missing additionalButtons functionality
        // alternative :https://github.com/AllenFang/react-bootstrap-table/commit/7e9b799d9ea555c374023fff179040c2ced6d6c0
        this.options = {
            defaultSortName: 'lastName',
            defaultSortOrder: 'asc',
            insertBtn: this.addCandidateButton,
            paginationPosition: 'bottom',
            exportCSVBtn: this.CustomExportCSVButton,
            searchField: this.CustomSearchField,
            expandBy: 'column',
            deleteBtn: this.importButton,
        };

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

    }

    addCandidateButton = () => {
        return (
            <a href="addCandidate" className="btn-lg candidateCustomButton" role="button" style={ {marginRight: 25}}>Add Candidate</a>
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

            <a className="btn-lg candidateCustomButton" role="button" onClick={ onClick } style={ {marginRight: 25}}>Export CSV</a>

        );
    };
    /**
     * Called when a user clicks on a row. For each row, three tabs will be displayed one containing
     * the Skill, one containing the Education list and one Candidates Notes.
     * @param row containing candidate information. row is actually a candidate
     * @returns {JSX}
     */
    expandCandidateDetails = (row) => {
        let candidate = [];
        candidate.push(row);

        return (
            <Tabs id="controlled-tab-example">
                <Tab eventKey={1} title="Skills"><SkillsList skillsUrl={row._links.candidateSkillsList.href}/></Tab>
                <Tab eventKey={2} title="Education"> <EducationList educationLink={row._links.education.href}/></Tab>
                <Tab eventKey={3} title="Notes"><NotesList notesUrl={row._links.candidateNotesList.href} candidate={row}/></Tab>
            </Tabs>
        )
    };

    /**
     * Placeholder for each of the filters
     * @param placeHolder field name where filter input will be placed
     * @returns {{type: string, placeholder: *, delay: number}}
     */
    getFilter(placeHolder) {
        return {
            type: 'RegexFilter',
            placeholder: placeHolder,
            delay: 1000
        }
    }

    isExpandableRow = () => {
        return true;
    };



    /**
     * Bootstrap table instance. The table is built based on the data provided in data={this.props.candidates} and the header columns.
     *  Each Header Column has a dataField which coincides with the a field present in a row(candidate) dataField='id'. The table is automatically built
     *  based on these two pieces of information. The custom components are fed to the table through the options property.
     *  @link http://allenfang.github.io/react-bootstrap-table/example.html#custom
        * @returns {XML}
     */
    render() {

        return (
            <BootstrapTable tableBodyClass='candidateTableBodyClass' tableHeaderClass='candidateTableHeaderClass' bordered={false} hover={true} striped={true}
                            data={this.props.candidates } options={this.options} pagination
                            exportCSV={true} expandComponent={ this.expandCandidateDetails }
                            expandableRow={this.isExpandableRow} search insertRow deleteRow>

                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center',} }
                                   dataField='id' filter={this.getFilter('Candidate Id')} isKey={ true }>Candidate
                    ID</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center',} }
                                   dataField='firstName' filter={this.getFilter('First Name')} dataSort sortFunc={sortByFirstName}>First
                    Name</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center',} }
                                   dataField='lastName' filter={this.getFilter('Last Name')} dataSort sortFunc={sortByLastName}>Last Name</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center',} }
                                   dataField='email' filter={this.getFilter('Email')}>Email</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center',} }
                                   dataField='phone'>Phone</TableHeaderColumn>
                <TableHeaderColumn expandable={false} dataField='id' dataFormat={ this.actionsFormatter }
                                   tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} }
                                   thStyle={ {'textAlign': 'center',} }>
                    Actions
                </TableHeaderColumn>
            </BootstrapTable>
        );
    }
}
class BootstrapTableMSG extends BootstrapTable {
    constructor(props) {
        // State does not contain candidate because they are kept in the global state
        super(props);

        // We have 4 custom components
        this.options = {
            defaultSortName: 'lastName',
            defaultSortOrder: 'asc',
            insertBtn: this.addCandidateButton,
            paginationPosition: 'bottom',
            exportCSVBtn: this.CustomExportCSVButton,
            searchField: this.CustomSearchField,
            expandBy: 'column',
            deleteBtn: this.importButton,
        };

    }
    renderToolBar() {
        const { selectRow, insertRow, deleteRow, search, additionalButtons, children } = this.props;
        const enableShowOnlySelected = selectRow && selectRow.showOnlySelected;
        if (enableShowOnlySelected
            || insertRow){}
    }
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