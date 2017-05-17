import React from 'react';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';

import {fetchCandidates} from '../utils/api';


import './CandidatesTable.css';


export default class BasicTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            candidates: null
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
            <BootstrapTable data={ candidates } pagination columnFilter>
                <TableHeaderColumn dataField='id' isKey={ true }>Candidate ID</TableHeaderColumn>
                <TableHeaderColumn dataField='firstName'>First Name</TableHeaderColumn>
                <TableHeaderColumn dataField='lastName'>Last Name</TableHeaderColumn>
                <TableHeaderColumn dataField='email'>Email</TableHeaderColumn>
            </BootstrapTable>
        );
    }
}