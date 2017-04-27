import React from 'react';
import {BootstrapTable, TableHeaderColumn, InsertButton} from 'react-bootstrap-table';

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

    handleInsertButtonClick = (onClick) => {
        // Custom your onClick event here,
        // it's not necessary to implement this function if you have no any process before onClick
        console.log('This is my custom function for InserButton click event');
        onClick();
    }

    createCustomInsertButton = (onClick) => {
        return (
            <InsertButton
                btnText='CustomInsertText'
                btnContextual='btn-warning'
                className='my-custom-class'
                btnGlyphicon='glyphicon-edit'
                onClick={ () => this.handleInsertButtonClick(onClick) }/>
        );
        // If you want have more power to custom the child of InsertButton,
        // you can do it like following
        // return (
        //   <InsertButton
        //     btnContextual='btn-warning'
        //     className='my-custom-class'
        //     onClick={ () => this.handleInsertButtonClick(onClick) }>
        //     { ... }
        //   </InsertButton>
        // );
    }



    isExpandableRow(row) {
        return true;
    }

    expandCandidateDetails(row) {
        return (
            <div>
                <h3>{row.firstName}</h3>
                <h4>{row.studyYear}</h4>
            </div>
        )
    }


    render() {

        let candidates = this.state.candidates;

        const options = {
            insertBtn: this.createCustomInsertButton
        };

        return (
            <BootstrapTable data={ candidates } options={options} pagination insertRow columnFilter expandableRow={this.isExpandableRow}
                            expandComponent={ this.expandCandidateDetails}>
                <TableHeaderColumn dataField='id' isKey={ true }>Candidate ID</TableHeaderColumn>
                <TableHeaderColumn dataField='firstName'>First Name</TableHeaderColumn>
                <TableHeaderColumn dataField='lastName'>Last Name</TableHeaderColumn>
                <TableHeaderColumn dataField='email'>Email</TableHeaderColumn>
            </BootstrapTable>
        );
    }
}