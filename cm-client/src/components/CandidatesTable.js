import React from 'react';
import {BootstrapTable, TableHeaderColumn, InsertButton} from 'react-bootstrap-table';
import { Tab, Tabs } from 'react-bootstrap';
import {fetchCandidates} from '../utils/api';


import './CandidatesTable.css';


export default class BasicTable extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            candidates: null,
            detailViewActiveTab:2
        };
        //use bellow if you don't use arrow function
       // this.expandCandidateDetails = this.expandCandidateDetails.bind(this);
    }

    componentDidMount() {
        fetchCandidates().then(candidates => {

            this.setState({
                candidates: candidates,
                detailViewActiveTab: "dsadasda"
            });
        });
    }
    handleSelect = (selectedTab) => {
        // The active tab must be set into the state so that
        // the Tabs component knows about the change and re-renders.
        this.setState({
          activeTab: selectedTab
        });
      }

    handleInsertButtonClick = (onClick) => {
        // Custom your onClick event here,
        // it's not necessary to implement this function if you have no any process before onClick
        console.log('This is my custom function for InserButton click event');
        onClick();
    }

    expandCandidateDetails = (row) => {
         let candidate = [];
          candidate.push(row);
          return (
              <Tabs activeKey={this.state.activeTab} onSelect={this.handleSelect} id="controlled-tab-example">
                      <Tab eventKey={1} title="Tab 1">Tab 1 content</Tab>
                      <Tab eventKey={2} title="Tab 2">Tab 2 content</Tab>
                      <Tab eventKey={3} title="Tab 3" >Tab 3 content</Tab>
              </Tabs>
          )
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



    render() {

        let candidates = this.state.candidates;


        return (
            <BootstrapTable  exportCSV data={ candidates }  pagination insertRow columnFilter expandableRow={this.isExpandableRow}
                            expandComponent={ this.expandCandidateDetails }>
                <TableHeaderColumn hidden={true} dataField='id' isKey={ true }>Candidate ID</TableHeaderColumn>

                <TableHeaderColumn dataField='firstName'>First Name</TableHeaderColumn>
                <TableHeaderColumn dataField='lastName'>Last Name</TableHeaderColumn>
                <TableHeaderColumn dataField='email'>Email</TableHeaderColumn>
            </BootstrapTable>
        );
    }
}