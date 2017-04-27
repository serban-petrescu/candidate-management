import React, { Component } from 'react';
import logo from '../img/logo.svg';
import CandidatesTable from './CandidatesTable';
import TableExampleComplex from './CandidateTableMaterialUI';
import CandidateEditableTable from  './EditableTable'     ;
import {deepOrange500} from 'material-ui/styles/colors';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import './App.css';

const muiTheme = getMuiTheme({
  palette: {
    accent1Color: deepOrange500,
  },
});
class App extends Component {
    constructor(props, context) {
        super(props, context);

        this.handleRequestClose = this.handleRequestClose.bind(this);
        this.handleTouchTap = this.handleTouchTap.bind(this);

        this.state = {
          open: false,
        };
      }
    handleRequestClose() {
        this.setState({
          open: false,
        });
      }

      handleTouchTap() {
        this.setState({
          open: true,
        });
      }
  render() {
    return (
        <MuiThemeProvider muiTheme={muiTheme}>
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to Candidate-Management</h2>
        </div>
        <CandidatesTable/>
          <TableExampleComplex/>
          <CandidateEditableTable/>
      </div>
        </MuiThemeProvider>

    );
  }
}

export default App;
