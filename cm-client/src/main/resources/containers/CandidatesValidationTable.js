import React from 'react';
import {SearchField} from 'react-bootstrap-table';
import '../less/candidateTable.less';
import "../less/roboto.less";
import {connect} from 'react-redux';
import {validateCandidates, loadCandidates} from '../actions/index';
import {bindActionCreators} from 'redux';
import MainCandidatesTable from "../components/MainCandidatesTable";
import {Columns} from "../utils/Column";

/**
 * Main Component containing the table where all the Candidates will be viewed and
 * processed.
 */
class CandidatesValidationTable extends MainCandidatesTable {

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
            trStyle: this.trStyle
        };

        return props;
    };

    /**
     * In the Actions tab of the table we have to wrap the candidate info and pass it down to
     * two other components: Edit and delete.
     * @param cell
     * @param row
     * @returns {XML}
     */
    actionsFormatter = (cell, row) => {
        return (
            <div style={{display: "inline"}}>
                <button type="button"
                        className="btn-defaultCustom btn btn-default">
                    <span style={{color: "green"}} className="glyphicon glyphicon-ok"/>
                </button>
                <button type="button"
                        className="btn-defaultCustom btn btn-default">
                    <span style={{color: '#841439'}} className="glyphicon glyphicon-remove"/>
                </button>
            </div>
        );
    };

    componentDidMount() {
        this.props.loadCandidates();
    };

    CustomSearchField = () => {
        return (
            <SearchField className="form-control" placeholder='Search ...'/>);

    };

    trStyle(rowData, rIndex) {
        return rIndex % (Math.round(Math.random() * 3)) === 0 ? {'border-style': 'solid', 'border-color': '#841439'} : '';
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
    return bindActionCreators({
        validateCandidates: validateCandidates,
        loadCandidates: loadCandidates
    }, dispatch);
}


export default connect(mapStateToProps, mapDispatchToProps)(CandidatesValidationTable);