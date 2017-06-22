import React from 'react';
import {SearchField, BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import {Tab, Tabs} from 'react-bootstrap';
import {fetchCandidates} from '../utils/api';
import {fetchSkillsForCandidate} from '../utils/api';
import {sortByLastName, sortByFirstName} from '../utils/sorting-comparators';
import EditCandidate from './EditCandidate';
import {fetchEducationForCandidate, fetchTagForCandidateSkill} from "../utils/api";
import ConfirmationDialog from  './ConfirmationDialog';
import '../less/candidateTable.less';
import "../less/roboto.less";

import { connect } from 'react-redux';
import { selectCandidate, loadCandidates} from '../actions/index';

import { bindActionCreators } from 'redux';

class ModifyCandidate extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            candidate: this.props.candidate
        };
    }

    render() {
        return (
            <ul className="list-group">
                <li className="list-group-item">
                    To be added
                </li>
            </ul>
        )
    }
}
class EducationList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            educationData: {}
        };
    }

    componentDidMount() {
        fetchEducationForCandidate(this.props.educationLink).then(response => {
            this.setState({
                educationType: response.educationType,
                provider: response.provider,
                description: response.description
            });
        });
    }

    render() {

        return (
            <ul className="list-group">
                <li className="list-group-item">
                    {this.state.educationType}
                </li>

                <li className="list-group-item">
                    {this.state.provider}
                </li>

                <li className="list-group-item">
                    {this.state.description}
                </li>
            </ul>
        )
    }
}
class SkillItem extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            description: "",
            tagType: "",
            rating: props.rating
        };
    }

    componentDidMount() {
        fetchTagForCandidateSkill(this.props.tagLink).then(data => {
            this.setState({
                description: data.description,
                tagType: data.tagType,
            });
        });
    }

    render() {

        return (
            <li className="list-group-item">
                {this.state.tagType} : {this.state.description} : {this.state.rating}
            </li>
        )
    }
}
class SkillsList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tagLinks: []
        };
    }

    componentDidMount() {
        fetchSkillsForCandidate(this.props.skillsUrl).then(data => {
            this.setState({
                tagLinks: data
            });
        });
    }

    render() {

        return (
            <ul className="list-group">{this.state.tagLinks.map((data, index) => {
                return ( < SkillItem key={index} tagLink={data.tagLink} rating={data.rating}/>);
            })}</ul>
        )
    }
}


class BasicTable extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            candidates: null,
            detailViewActiveTab: 2
        };


        this.options = {
            defaultSortName: 'lastName', //default sort column name
            defaultSortOrder: 'asc', // default sort order
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
        // The active tab must be set into the state so that
        // the Tabs component knows about the change and re-renders.
        this.setState({
            activeTab: selectedTab
        });
    };
    CustomSearchField = (props) => {
        return (
            <SearchField className="form-control" placeholder='Search ...'/>);

    };

    CustomExportCSVButton = (onClick) => {
        return (

            <a className="btn-lg candidateCustomButton" role="button" onClick={ onClick }  style={ {marginRight: 25}}>Export CSV</a>

        );
    };

    expandCandidateDetails = (row) => {
        let candidate = [];
        candidate.push(row);

      /*  return (

            <Tabs onSelect={this.handleSelect} id="controlled-tab-example">
                <Tab eventKey={1} title="Skills"><SkillsList skillsUrl={row._links.candidateSkillsList.href}/></Tab>
                <Tab eventKey={2} title="Education"> <EducationList educationLink={row._links.education.href}/></Tab>
                <Tab eventKey={3} title="Modify"> <ModifyCandidate refreshCandidateTable={this.handlechange} candidate={row}/></Tab>
            </Tabs>
        )*/
    };


    getFilter(placeHolder) {
        return {
            type: 'RegexFilter',
            placeholder: placeHolder,
            delay: 1000
        }
    }



    isExpandableRow = (row) => {
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
                <TableHeaderColumn expandable={false}  dataField='id' dataFormat={ this.actionsFormatter } tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} }
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
    return bindActionCreators({ selectCandidate: selectCandidate, loadCandidates: loadCandidates}, dispatch);
}


export default connect(mapStateToProps, mapDispatchToProps)(BasicTable);