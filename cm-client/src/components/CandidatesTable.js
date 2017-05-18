import React from 'react';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';

import {fetchCandidates} from '../utils/api';
import {sortByLastName, sortByFirstName} from '../utils/sorting-comparators';

import './CandidatesTable.css';

export default class BasicTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            candidates: null
        };


        this.options = {
            defaultSortName: 'lastName', //default sort column name
            defaultSortOrder: 'asc', // default sort order
            paginationPosition: 'both'
        }
    }

    componentDidMount() {
        fetchCandidates().then(candidates => {

            this.setState({
                candidates: candidates
            });
        });
    }

    getFilter(placeHolder) {
        return {
            type: 'RegexFilter',
            placeholder: placeHolder,
            delay: 1000
        }
    }

    render() {
        let candidates = this.state.candidates;


        return (
            <BootstrapTable data={ candidates } options={this.options} pagination search>
                <TableHeaderColumn dataField='id' filter={this.getFilter('Candidate Id')} isKey={ true }>Candidate ID</TableHeaderColumn>
                <TableHeaderColumn dataField='firstName' filter={this.getFilter('First Name')} dataSort sortFunc={sortByFirstName}>First Name</TableHeaderColumn>
                <TableHeaderColumn dataField='lastName' filter={this.getFilter('Last Name')} dataSort sortFunc={sortByLastName}>Last Name</TableHeaderColumn>
                <TableHeaderColumn dataField='email' filter={this.getFilter('Email')}>Email</TableHeaderColumn>
            </BootstrapTable>
        );
    }
}