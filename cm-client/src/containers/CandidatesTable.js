import React from 'react';
import {SearchField, BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import {Tab, Tabs} from 'react-bootstrap';
import {sortByLastName, sortByFirstName} from '../utils/sorting-comparators';
import EditCandidate from './EditCandidate';
import ConfirmationDialog from  './ConfirmationDialog';
import '../less/candidateTable.less';
import "../less/roboto.less";
import SkillsList from '../components/SkillsList'
import {connect} from 'react-redux';
import {selectCandidate, loadCandidates} from '../actions/index';
import {bindActionCreators} from 'redux';
import EducationList from "../components/EducationList";



class CandidatesTable extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            candidates: null,
            detailViewActiveTab: 2
        };


        this.options = {
            defaultSortName: 'lastName',
            defaultSortOrder: 'asc',
            insertBtn: this.addCandidateButton,
            paginationPosition: 'bottom',
            exportCSVBtn: this.CustomExportCSVButton,
            searchField: this.CustomSearchField,
            expandBy: 'column'
        };

    }

    actionsFormatter = (cell, row) => {
        return (<div style={{display: "inline"}} onClick={() => this.props.selectCandidate(row)}>
            <EditCandidate candidate={row}/>
            <ConfirmationDialog/>
        </div>);
    };

    componentDidMount() {
        this.setState({
            detailViewActiveTab: "1"
        });
        this.props.loadCandidates();

    }

    addCandidateButton = () => {
        return (
            <a href="addCandidate" className="btn-lg candidateCustomButton" role="button">Add Candidate</a>
        );
    };


    handleSelect = (selectedTab) => {
        this.setState({
            activeTab: selectedTab
        });
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

    expandCandidateDetails = (row) => {
        let candidate = [];
        candidate.push(row);

         return (

         <Tabs onSelect={this.handleSelect} id="controlled-tab-example">
         <Tab eventKey={1} title="Skills"><SkillsList skillsUrl={row._links.candidateSkillsList.href}/></Tab>
         <Tab eventKey={2} title="Education"> <EducationList educationLink={row._links.education.href}/></Tab>
         </Tabs>
         )
    };

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

    render() {

        return (
            <BootstrapTable tableBodyClass='candidateTableBodyClass' tableHeaderClass='candidateTableHeaderClass' bordered={false} hover={true} striped={true}
                            data={this.props.candidates } options={this.options} pagination
                            exportCSV={true} expandComponent={ this.expandCandidateDetails }
                            expandableRow={this.isExpandableRow} search insertRow>

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