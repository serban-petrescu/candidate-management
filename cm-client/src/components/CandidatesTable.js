import React from 'react';
import {ExportCSVButton,BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import {Tab, Tabs} from 'react-bootstrap';
import {fetchCandidates} from '../utils/api';
import {fetchSkillsForCandidate} from '../utils/api';
import {sortByLastName, sortByFirstName} from '../utils/sorting-comparators';
import EditCandidate from '../components/EditCandidate';
import './CandidatesTable.css';
import {fetchEducationForCandidate, fetchTagForCandidateSkill} from "../utils/api";
import ConfirmationDialog from  '../components/ConfirmationDialog'
import "roboto-fontface/css/roboto/roboto-fontface.css"
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
                    <EditCandidate refreshCandidateTable={this.props.refreshCandidateTable} candidate={this.state.candidate}/>
                </li>
                <li className="list-group-item">
                    <ConfirmationDialog refreshCandidateTable={this.props.refreshCandidateTable} candidateId={this.state.candidate.id}/>
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

export default class BasicTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            candidates: null,
            detailViewActiveTab: 2
        };


        this.options = {
            defaultSortName: 'lastName', //default sort column name
            defaultSortOrder: 'asc', // default sort order
            paginationPosition: 'bottom',
            exportCSVBtn: this.CustomExportCSVButton

        }  ;
        //use bellow if you don't use arrow function
        // this.expandCandidateDetails = this.expandCandidateDetails.bind(this);
    }

    componentDidMount() {
        fetchCandidates().then(candidates => {

            this.setState({
                candidates: candidates,
                detailViewActiveTab: "1"
            });
        });
    }

    handlechange = ()=> {
        fetchCandidates().then(candidates => {

            this.setState({
                candidates: candidates,
                detailViewActiveTab: "1"
            });
        });
    };

    handleSelect = (selectedTab) => {
        // The active tab must be set into the state so that
        // the Tabs component knows about the change and re-renders.
        this.setState({
            activeTab: selectedTab
        });
    };

    CustomExportCSVButton = (onClick) => {
      return (
        <button className="btn-lg" style={ { color: 'white' ,backgroundColor: '#841439'} } onClick={ onClick }>Export CSV</button>
      );
    };

    expandCandidateDetails = (row) => {
        let candidate = [];
        candidate.push(row);

        return (

            <Tabs onSelect={this.handleSelect} id="controlled-tab-example">
                <Tab eventKey={1} title="Skills"><SkillsList skillsUrl={row._links.candidateSkillsList.href}/></Tab>
                <Tab eventKey={2} title="Education"> <EducationList educationLink={row._links.education.href}/></Tab>
                <Tab eventKey={3} title="Modify"> <ModifyCandidate refreshCandidateTable={this.handlechange} candidate={row}/></Tab>
            </Tabs>
        )
    };

    isExpandableRow(row) {
        return true;
    }

    getFilter(placeHolder) {
        return {
            type: 'RegexFilter',
            placeholder: placeHolder,
            delay: 1000
        }
    }

    headerStyle = {
        fontSize: 22,
        backgroundColor: "#841439",
        color: 'white' ,
        borderRadius: 5
    };

    bodyStyle = {
        fontSize: 16,
        fontFamily: "Roboto",
        borderRadius: 5,
        backgroundColor: '#f9f9f9'
    };


    render() {
        return (
            <BootstrapTable bodyStyle={this.bodyStyle} bordered={false}  hover={true} striped={true} headerStyle={this.headerStyle}
                            data={this.state.candidates } options={this.options} pagination
                            exportCSV={true} expandComponent={ this.expandCandidateDetails }
                            expandableRow={this.isExpandableRow} search>

                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center', } }
                                   dataField='id' filter={this.getFilter('Candidate Id')} isKey={ true }>Candidate
                    ID</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center', } }
                                   dataField='firstName' filter={this.getFilter('First Name')} dataSort sortFunc={sortByFirstName}>First
                    Name</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center', } }
                                   dataField='lastName' filter={this.getFilter('Last Name')} dataSort sortFunc={sortByLastName}>Last Name</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center', } }
                                   dataField='email' filter={this.getFilter('Email')}>Email</TableHeaderColumn>
                <TableHeaderColumn tdStyle={ {'textAlign': 'center', 'fontWeight': 'lighter'} } thStyle={ {'textAlign': 'center', } }
                                   dataField='phone'>Phone</TableHeaderColumn>
            </BootstrapTable>
        );
    }
}
