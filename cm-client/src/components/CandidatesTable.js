import React from 'react';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';

import {fetchCandidates} from '../utils/api';
import {sortByLastName} from '../utils/sorting-comparators';

import './CandidatesTable.css';





export default class BasicTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            candidates: null
        };


        this.options = {
            defaultSortName: 'lastName', //default sort column name
            defaultSortOrder: 'asc' // default sort order
        }
    }

    componentDidMount() {
        fetchCandidates().then(candidates => {

            this.setState({
                candidates: candidates
            });
        });
    }



    render() {

        let candidates = this.state.candidates;


        return (
            <BootstrapTable data={ candidates } options={this.options} pagination columnFilter>
                <TableHeaderColumn dataField='id' isKey={ true }>Candidate ID</TableHeaderColumn>
                <TableHeaderColumn dataField='firstName' dataSort={true}>First Name</TableHeaderColumn>
                <TableHeaderColumn dataField='lastName' dataSort sortFunc={sortByLastName}>Last Name</TableHeaderColumn>
                <TableHeaderColumn dataField='email'>Email</TableHeaderColumn>
            </BootstrapTable>
        );
    }
}