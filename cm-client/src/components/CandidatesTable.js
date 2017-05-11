import React from 'react';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import {Tab, Tabs} from 'react-bootstrap';
import {fetchCandidates} from '../utils/api';
import {fetchSkillsForCandidate} from '../utils/api';


import './CandidatesTable.css';
import {fetchTagForCandidateSkill} from "../utils/api";


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
            <li>
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
            <ul>{this.state.tagLinks.map((data,index) => {
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

    handleSelect = (selectedTab) => {
        // The active tab must be set into the state so that
        // the Tabs component knows about the change and re-renders.
        this.setState({
            activeTab: selectedTab
        });
    };

    expandCandidateDetails = (row) => {
        let candidate = [];
        candidate.push(row);

        return (

            <Tabs onSelect={this.handleSelect} id="controlled-tab-example">
                <Tab eventKey={1} title="Skills"><SkillsList skillsUrl={row._links.candidateSkillsList.href}/></Tab>
                <Tab eventKey={2} title="Education">Event</Tab>
            </Tabs>
        )
    };

    isExpandableRow(row) {
        return true;
    }

    render() {

        return (
            <BootstrapTable exportCSV data={this.state.candidates } pagination insertRow columnFilter expandableRow={this.isExpandableRow}
                            expandComponent={ this.expandCandidateDetails }>
                <TableHeaderColumn hidden={true} dataField='id' isKey={ true }>Candidate ID</TableHeaderColumn>
                <TableHeaderColumn dataField='firstName'>First Name</TableHeaderColumn>
                <TableHeaderColumn dataField='lastName'>Last Name</TableHeaderColumn>
                <TableHeaderColumn dataField='email'>Email</TableHeaderColumn>
                <TableHeaderColumn dataField='phone'>Phone</TableHeaderColumn>
            </BootstrapTable>
        );
    }
}