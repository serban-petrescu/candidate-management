import React from "react";
import {SearchField, ButtonGroup} from "react-bootstrap-table";
import {Tabs, Tab} from 'react-bootstrap';
import {connect} from "react-redux";
import {validateCandidates,
    loadNotValidatedCandidates,
    deleteCandidates
    } from "../actions/CandidateValidationActions";
import {selectCandidate} from '../actions/CandidateActions';
import EditCandidate from './EditCandidate';
import {bindActionCreators} from "redux";
import MainCandidatesTable from "../components/MainCandidatesTable";
import {Columns} from "../utils/Column";
import ValidationDialog from './ValidationDialog';
import ConfirmationDialog from './ConfirmationDialog';
import {showNotification} from '../utils/ApiNotification';
import EducationList from "../components/EducationList";
import NotesList from "../components/NotesList";
import SkillsList from '../components/SkillsList';

class CandidatesValidationTable extends MainCandidatesTable {

    constructor(props) {
        // State does not contain candidate because they are kept in the global state
        super(props);
        this.state = {selected: []};
        this.handleCheckbox = this.handleCheckbox.bind(this);

        this.columnsConfig.addColumn(Columns.createColumnWithOptions("id", "Select", {
            dataFormat: this.selectFormatter.bind(this),
            width: '100px',
            expandable: false
        }), 0);

        this.columnsConfig.addColumn(Columns.createColumnWithOptions("id", "Actions", {
            dataFormat: this.actionsFormatter.bind(this),
            expandable: false
        }));
    }

    handleCheckbox(id) {
        const newSelected = Object.assign([], this.state.selected);
        newSelected[id] = !this.state.selected[id];
        this.setState({
            selected: newSelected
        });
    }

    getTableOptions() {
        let props = super.getTableOptions();

        props = {
            ...props,
            trStyle: this.trStyle,
            expandComponent: this.expandNVCandidate,
            insertRow: true,
            expandableRow: () => {
                return true;
            }
        };

        props.options = {
            ...props.options,
            btnGroup: this.createCustomButtonGroup.bind(this),
            expandBy: 'column'
        };

        return props;
    }

    validateSelectedCandidates = () => {
        let result = this.props.validateCandidates(Object.keys(this.state.selected));
        result.then(() => {
            let HTTP_STATUS_OK = 200;
            showNotification(result, HTTP_STATUS_OK, "validateSelected");
            this.setState({
                selected: []
            });
            this.loadData();
        });
    };

    rejectSelectedCandidates = () =>
    {
        let result = this.props.deleteCandidates(Object.keys(this.state.selected));
        result.then(() => {
            let HTTP_STATUS_OK = 200;
            showNotification(result, HTTP_STATUS_OK, "deleteSelected");
            this.setState({
                selected: []
            });
            this.loadData();
        });
    };

    selectFormatter = (cell, row) => {
        return (
            <div>
                <input id="checkbox_select_candidate" type="checkbox" checked={this.state.selected[row.id] === true}
                       onChange={() => this.handleCheckbox(row.id)}/>
            </div>
        );
    };

    /**
     * In the Actions tab of the table we have to wrap the candidate info and pass it down to
     * two other components: Edit and delete.
     * @param cell
     * @param row
     * @returns {XML}
     */
    actionsFormatter = (cell, row) => {
        return (<div style={{display: "inline"}} onClick={() => this.props.selectCandidate(row)}>
            <EditCandidate candidate={row} onEdit={() => this.loadData()}/>
            <ValidationDialog onValidate={() => this.loadData() }/>
            <ConfirmationDialog onRemove={() => this.loadData() }/>
        </div>);
    };

    componentDidMount()
    {
        this.loadData();
    }

    CustomSearchField = () => {
        return (
            <SearchField className="form-control" placeholder='Search ...'/>
        );
    };

    createCustomButtonGroup = props => {
        return (
            <ButtonGroup>
                <a className='btn-lg candidateCustomButton' role="button" onClick={this.validateSelectedCandidates}
                    style={ {marginRight: 25}}>
                    Validate
                </a>
                <a className='btn-lg candidateCustomButton' role="button" onClick={this.rejectSelectedCandidates}>
                    Remove
                </a>
            </ButtonGroup>
        );
    };

    /**
    * Called when a user clicks on a row. For each row, three tabs will be displayed one containing
    * the Skill, one containing the Education list and one Candidates Notes.
    * @param row containing candidate information. row is actually a candidate
    * @returns {JSX}
    */
    expandNVCandidate = (row) => {
        return (
            <Tabs id="NVcandidateDetails">
                <Tab eventKey={1} title="Skills"><SkillsList skillsUrl={row._links.candidateSkillsList.href}/></Tab>
                <Tab eventKey={2} title="Education"> <EducationList educationLink={row._links.education.href}/></Tab>
                <Tab eventKey={3} title="Notes"><NotesList notesUrl={row._links.candidateNotesList.href}
                                                       candidate={row}/></Tab>
            </Tabs>
        );
    };

    loadData = () => {
        this.props.loadCandidates();
    }

}

function mapStateToProps(state) {
    return {
        candidates: state.candidatesNYValidated
    };
}

// Anything returned from this function will end up as props
// on the ConfirmationDialog component
function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        selectCandidate,
        validateCandidates,
        deleteCandidates,
        loadCandidates: loadNotValidatedCandidates
    }, dispatch);
}


export default connect(mapStateToProps, mapDispatchToProps)(CandidatesValidationTable);