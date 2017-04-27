import React from 'react';
import {Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn} from 'material-ui/Table';

import {fetchCandidates} from '../utils/api';


const CandidateRow = (props) => {
    let candidate = props.candidate;

    return (
        <TableRow>
            <TableRowColumn>
                {candidate.id}
            </TableRowColumn>
            <TableRowColumn>
                {candidate.firstName}
            </TableRowColumn>
            <TableRowColumn>
                {candidate.lastName}
            </TableRowColumn>
        </TableRow>
    )
};


function createHeaderColumns() {
    let columnNames = ['ID', 'FirstName', 'LastName'];
    return columnNames.map( (name, index) => <TableHeaderColumn key={index}>{name}</TableHeaderColumn>);
}

function createTableRows(candidates) {
    return candidates != null ? candidates.map( candidate => <CandidateRow key={candidate.id} candidate={candidate}/>) : null;
}




class CandidatesTableMUI extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            candidates: null
        };
    }

    componentDidMount(){
        fetchCandidates().then( candidates => {

            this.setState({
               candidates: candidates
            });
        });
    }

    render() {

        const headerColumns = createHeaderColumns();
        const tableRows = createTableRows(this.state.candidates);

        return (
            <Table>
                <TableHeader>
                    <TableRow>
                        {headerColumns}
                    </TableRow>
                </TableHeader>

                <TableBody>
                    {tableRows}
                </TableBody>
            </Table>
        );
    }

}

export default CandidatesTableMUI;