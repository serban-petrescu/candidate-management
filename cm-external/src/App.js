import React, { Component } from 'react';
import './App.css';
import Recaptcha from "./Components/Recaptcha";

class App extends Component {
  render() {
    return (
      <div className="App">
          <Recaptcha/>
      </div>
    );
  }
}

export default App;
