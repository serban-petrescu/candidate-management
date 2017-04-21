import React, { Component } from 'react';
import injectTapEventPlugin from 'react-tap-event-plugin';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import logo from '../img/logo.svg';
import CandidatesTable from './CandidatesTable';

import './App.css';

injectTapEventPlugin();

class App extends Component {
  render() {
    return (
    <MuiThemeProvider>
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to Candidate-Management</h2>
        </div>
        <CandidatesTable/>
      </div>
    </MuiThemeProvider>
    );
  }
}

export default App;
