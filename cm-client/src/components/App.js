import React, { Component } from 'react';
import logo from '../img/logo.svg';
import CandidatesTable from './CandidatesTable';

import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to Candidate-Management</h2>
        </div>
        <CandidatesTable/>
      </div>
    );
  }
}

export default App;
